package jtwirc;

import jtwirc.events.TwircListener;
import jtwirc.types.action.ActionBuilder;
import jtwirc.types.action.ActionEvent;
import jtwirc.types.clearChat.ClearChatBuilder;
import jtwirc.types.clearChat.ClearChatEvent;
import jtwirc.types.globaluserstate.GlobalUserStateBuilder;
import jtwirc.types.globaluserstate.GlobalUserStateEvent;
import jtwirc.types.hostTarget.HostTargetBuilder;
import jtwirc.types.hostTarget.HostTargetEvent;
import jtwirc.types.mode.ModeBuilder;
import jtwirc.types.mode.ModeEvent;
import jtwirc.types.notice.NoticeBuilder;
import jtwirc.types.notice.NoticeEvent;
import jtwirc.types.roomstate.RoomstateBuilder;
import jtwirc.types.roomstate.RoomstateEvent;
import jtwirc.types.subscriberEvent.SubscriberEvent;
import jtwirc.types.subscriberEvent.SubscriberEventBuilder;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.twitchMessage.TwitchMessageBuilder;
import jtwirc.types.usernotice.UserNoticeEvent;
import jtwirc.types.usernotice.UsernoticeBuilder;
import jtwirc.types.users.TwitchUser;
import jtwirc.types.users.TwitchUserBuilder;
import jtwirc.types.users.UserStateEvent;
import jtwirc.types.users.UserstateBuilder;

import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.*;

/**
 * Class for communicating with the TwitchIrc chat.<br>
 * To create instances of Twirc, see {@link TwircBuilder}.<br><br>
 * <p>
 * TwitchIrc behaves a bit different from regular IRC, in that it uses only a few types of message types
 * and it has a few message types of its own. This class is intended to cover all of Twitch's special
 * features, and thus making it easier to communicate with Twitch chat programmatically. <br><br>
 * <p>
 * To connect to Twitch chat, you need to generate a Twitch account and you need to generate an IRC oAuth
 * token for that account. To generate a oAuth token, visit <a href="https://twitchapps.com/tmi/">https://twitchapps.com/tmi/</a><br><br>
 * <p>
 * For documentation about how Twitch communicates via IRC,
 * see <a href="https://github.com/justintv/Twitch-API/blob/master/IRC.md">
 * https://github.com/justintv/Twitch-API/blob/master/IRC.md </a>
 */
public class Twirc
{
    final boolean verboseMode;
    final Set<String> moderators = Collections.synchronizedSet(new HashSet<String>());
    final Set<String> online = Collections.synchronizedSet(new HashSet<String>());
    //***********************************************************************************************
    //											VARIABLES
    //***********************************************************************************************
    private final BotType type;
    private final String nick;
    private final String pass;
    private final String server;
    private final String channel;
    private final int port;
    private final boolean useSSL;
    private final OutputQueue queue;
    private final ArrayList<TwircListener> listeners = new ArrayList<>();
    private final ClearChatBuilder clearChatBuilder;
    private final HostTargetBuilder hostTargetBuilder;
    private final ModeBuilder modeBuilder;
    private final NoticeBuilder noticeBuilder;
    private final RoomstateBuilder roomstateBuilder;
    private final TwitchMessageBuilder twitchMessageBuilder;
    private final TwitchUserBuilder twitchUserBuilder;
    private final UserstateBuilder userstateBuilder;
    private final SubscriberEventBuilder subscriberBuilder;
    private final UsernoticeBuilder usernoticeBuilder;
    private final GlobalUserStateBuilder globalUserStateBuilder;
    private final ActionBuilder actionBuilder;
    private OutputThread outThread;
    private InputThread inThread;
    private boolean resourcesCreated = false;
    private boolean isConnected = false;
    private boolean isDisposed = false;
    private Socket socket = null;
    private BufferedWriter writer = null;
    private BufferedReader reader = null;

    //***********************************************************************************************
    //											CONSTRUCTOR
    //***********************************************************************************************
    Twirc(TwircBuilder builder)
    {
        this.type = builder.type;
        this.nick = builder.nick;
        this.pass = builder.oauth;
        this.server = builder.server;
        this.channel = builder.channel;
        this.port = builder.port;
        this.verboseMode = builder.verboseMode;
        this.useSSL = builder.useSSL;

        this.clearChatBuilder = builder.getClearChatBuilder();
        this.hostTargetBuilder = builder.getHostTargetBuilder();
        this.modeBuilder = builder.getModeBuilder();
        this.noticeBuilder = builder.getNoticeBuilder();
        this.roomstateBuilder = builder.getRoomstateBuilder();
        this.twitchUserBuilder = builder.getTwitchUserBuilder();
        this.userstateBuilder = builder.getUserstateBuilder();
        this.twitchMessageBuilder = builder.getTwitchMessageBuilder();
        this.subscriberBuilder = builder.getSubscriberEventBuilder();
        this.usernoticeBuilder = builder.getUsernoticeBuilder();
        this.globalUserStateBuilder = builder.getGlobalUserStateBuilder();
        this.actionBuilder = builder.getActionBuilder();

        this.queue = new OutputQueue();

        addIRCListener(new TwircMaintainanceListener(this));
    }

    public BotType getType()
    {
        return type;
    }

    /**
     * Sends a message directly to the server. The message will not be formated in
     * any way. <br>
     * This method should be used very sparsely, as it sidesteps the messageing
     * delay and can get your bot Irc-banned on Twitch's side (might happen if the bot sends
     * more than 20 messages in 30 seconds).
     *
     * @param message The message that should be sent
     */
    public void serverMessage(String message)
    {
        outThread.quickSend(message);
    }

    //***********************************************************************************************
    //											PUBLIC
    //***********************************************************************************************

    /**
     * Enqueues a message at the end of the message queue. The message will be
     * sent to the channel when all messages enqueued before it has been sent.
     *
     * @param message The message that should be sent
     */
    public void channelMessage(String message)
    {
        queue.add("PRIVMSG " + channel + " :" + message);
    }

    /**
     * Enqueues a message at the front of the message queue. The message will be sent as soon as possible.
     *
     * @param message The message that should be sent
     */
    public void priorityChannelMessage(String message)
    {
        queue.addFirst("PRIVMSG " + channel + " :" + message);
    }

    /**
     * Check if this Twirc instance is currently connected to Twitch. If we are not, and we are not
     * {@link #isDisposed()}, then we may try to reconenct. See {@link #connect()}
     *
     * @return <code>True</code> if we are connected
     */
    boolean isConnected()
    {
        return isConnected;
    }

    /**
     * Check if this Twirc instance has beed disposed. If it has, no further
     * connect attempts will succeed.
     *
     * @return <code>True</code> if it is disposed
     */
    private boolean isDisposed()
    {
        return isDisposed;
    }

    /**
     * Fetches a set of all the users that are <b>currently</b> online in the joined channel. Note that this set is
     * <b>copy</b> of the underlying set of online users. Thus, changes to the original set will not be visible
     * in the returned set, and changes to the returned set will not affect the original set.<br><br>
     * <p>
     * For getting all users online as soon as we connect, and the server has told us who are online,
     * see {@link TwircListener#onNamesList(java.util.Collection)}
     * <p>
     * Also worth noting is that the set only contains the users names in lower case letters.
     *
     * @return A copy of the Set of online users.
     */
    public Set<String> getUsersOnline()
    {
        Set<String> out = new HashSet<>();
        synchronized (online)
        {
            for (String s : online)
            {
                out.add(s);
            }
        }
        return out;
    }

    /**
     * Fetches a set of all the moderators that are <b>currently</b> online in the joined channel. Note that this set is
     * <b>copy</b> of the underlying set of online moderators. Thus, changes to the original set will not be visible
     * in the returned set, and changes to the returned set will not affect the original set.<br><br>
     * <p>
     * Also worth noting is that the set only contains the moderators names in lower case letters.
     *
     * @return A copy of the Set of online moderators.
     */
    public Set<String> getModsOnline()
    {
        Set<String> out = new HashSet<>();
        synchronized (moderators)
        {
            for (String s : moderators)
            {
                out.add(s);
            }
        }
        return out;
    }

    /**
     * Fetches the nick of the bot, which it will use to connect to an IRC server
     *
     * @return The bot's nick
     */
    public String getNick()
    {
        return nick;
    }

    /**
     * Adds a specific listener to the list of active listeners
     *
     * @param listener Listener to be added
     */
    public void addIRCListener(TwircListener listener)
    {
        synchronized (listeners)
        {
            this.listeners.add(listener);
        }
    }

    /**
     * Removes a specific listener from the list of active listeners
     *
     * @param listener Listener to be removed
     * @return <code>true</code> if the listener was removed
     */
    public boolean removeIRCListener(TwircListener listener)
    {
        synchronized (listeners)
        {
            return this.listeners.remove(listener);
        }
    }

    /**
     * Connects to the Twitch server and joins the channel which was designated in the TwircBuilder.
     *
     * @return {@code true} if connection was successful
     * @throws IOException          In case the BufferedReader or BufferedWriter throws an error during connection. Might be due to timeout, socket closing or something else
     * @throws InterruptedException In case the Twirc process is interrupted while connecting
     */
    public synchronized boolean connect() throws IOException, InterruptedException
    {
        if (isDisposed)
        {
            System.err.println("\tError. Cannot connect. This Twirc instance has been disposed.");
            return false;
        }
        if (isConnected)
        {
            System.err.println("\tError. Cannot connect. Already connected to Twitch server");
            return false;
        }

        if (!resourcesCreated)
        {
            createResources(); //Creates a socket and our input/output threads
        }

        int oldTimeout = socket.getSoTimeout();
        socket.setSoTimeout(10 * 1000); //Set a timeout for connection to 10 seconds, during connection
        isConnected = doConnect();
        socket.setSoTimeout(oldTimeout); //Return timeout to what it was before connecting

        if (isConnected)
        {
            //Start the output thread
            outThread.start();

            //Add capacities to the bot and wait for them to take effect
            addCapacities();
            Thread.sleep(1000);

            //Start the input thread
            inThread.start();

            //Join the channel
            serverMessage("JOIN " + channel);

            for (TwircListener listener : listeners)
            {
                listener.onConnect();
            }

            return true;
        }
        return false;
    }

    /**
     * Closes the connection to the IrcServer, leaves all channels, terminates the input- and output thread and
     * frees all resources. <br><br>
     * <p>
     * It is safe to call this method even if connections are already closed.<br><br>
     * <p>
     * This method is different from {@link #close()} in that it calls the {@link TwircListener#onDisconnect()} method
     * of all the listeners. A listener may thus attempt to reconnect
     */
    synchronized void disconnect()
    {
        //Since several sources can call this method on program shutdown, we avoid entering it again if
        //we've already disconnected
        if (!isConnected || isDisposed)
        {
            return;
        }

        isConnected = false;

        System.out.println("\n\tDisconnecting from Twitch chat...");
        releaseResources();
        System.out.println("\tDisconnected from Twitch chat\n");

        for (TwircListener l : listeners)
        {
            l.onDisconnect();
        }
    }

    /**
     * Closes the connection to the IrcServer, leaves all channels, terminates the input- and output thread and
     * frees all resources. <br><br>
     * <p>
     * It is safe to call this method even if connections are already closed.<br><br>
     * <p>
     * This method is different from {@link Twirc#disconnect()} in that it <b>does not</b> call the {@link TwircListener#onDisconnect()} method
     * of any of the listeners. Thus, this method is intended to be called if you want to make sure no reconnect attempts are performed.
     */
    public synchronized void close()
    {
        if (isDisposed)
        {
            return;
        }

        isConnected = false;
        isDisposed = true;

        System.out.println("\n\tDisposing of IRC...");
        releaseResources();
        System.out.println("\tDisposing of IRC completed\n");
    }

    //***********************************************************************************************
    //										PRIVATE and PACKAGE
    //***********************************************************************************************
    private void createResources() throws IOException
    {
        if (useSSL)
        {
            socket = SSLSocketFactory.getDefault().createSocket(server, port);
        }
        else
        {
            socket = new Socket(server, port);
        }

        socket.setSoTimeout(6 * 60 * 1000); //Set a timeout for connection to 6 minutes. Twitch's default timeout is 5 minutes

        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        this.outThread = new OutputThread(this, queue, reader, writer);
        this.inThread = new InputThread(this, reader, writer);

        resourcesCreated = true;
    }

    private void releaseResources()
    {
        resourcesCreated = false;

        outThread.end();
        inThread.end();

        try
        {
            socket.close();
        }
        catch (IOException ignored)
        {
        }

        try
        {
            reader.close();
        }
        catch (IOException ignored)
        {
        }

        try
        {
            writer.close();
        }
        catch (IOException ignored)
        {
        }
    }

    private boolean doConnect() throws IOException
    {
        // Log on to the server.
        writer.write("PASS " + pass + "\r\n");
        writer.write("NICK " + nick + "\r\n");
        writer.write("USER " + nick + "\r\n");
        writer.flush();


        // Read lines from the server until it tells us we have connected.
        String line;
        try
        {
            while ((line = reader.readLine()) != null)
            {
                System.out.println("IN  " + line);
                //When we get a message containing 004, we have successfully logged in
                if (line.contains("004"))
                {
                    return true;
                }
                else if (line.contains("Error logging in"))
                {
                    return false;
                }
            }
        }
        catch (SocketTimeoutException e)
        {
            System.err.println("\tConnection attempt to Twitch timed out.");
        }

        //We hit this return if the reader timed out, or we reached the end of stream
        return false;
    }

    /**
     * Gives us the appropriate Twitch capacities, such as seeing JOIN/PART messages,
     * to send Twitch commands (such as .timeout, .mod and so on) and to see
     * users tags (such as display color)
     */
    private void addCapacities()
    {
        serverMessage("CAP REQ :twitch.tv/membership");
        serverMessage("CAP REQ :twitch.tv/commands");
        serverMessage("CAP REQ :twitch.tv/tags");
    }

    void incommingMessage(String line)
    {
        //PING is a bit strange, so we need to handle it separately. And also, we want to respond to a ping
        //before we do anything else.
        if (line.contains("PING"))
        {

            // A PING contains the message "PING MESSAGE", and we want to reply with MESSAGE as well
            // Hence, we reply "PONG MESSAGE" . That's where the substring(5) comes from bellow, we strip
            //out everything but the message
            System.out.println("IN  " + line);
            serverMessage("PONG " + line.substring(5)); //Remove the "PING " part, and send the rest back
            return;
        }

        //Call all the appropriate listeners for the given message.
        synchronized (listeners)
        {
            //First, we call all onAnything messages
            for (TwircListener l : listeners)
            {
                l.onAnything(line);
            }

            TwitchMessage message = twitchMessageBuilder.build(line);

            //This message is a reply for a capacity request. Just ignore it
            if (message.getCommand().equals("JOIN"))
            {
                String userName = parseUsername(message.getPrefix());
                for (TwircListener l : listeners)
                {
                    l.onJoin(userName);
                }
            }
            else if (message.getCommand().equals("PART"))
            {
                String userName = parseUsername(message.getPrefix());
                for (TwircListener l : listeners)
                {
                    l.onPart(userName);
                }
            }
            else if (message.getCommand().equals("PRIVMSG"))
            {
                TwitchUser user = twitchUserBuilder.build(message);
                if (user.getName().equalsIgnoreCase("twitchnotify"))
                {
                    handleTwitchNotify(message);    //The user 'twitchnotify' is used by Twitch to send us messages
                }
                else
                {
                    for (TwircListener l : listeners)
                    {
                        l.onPrivMsg(user, message);
                    }
                }
            }
            else if (message.getCommand().equals("WHISPER"))
            {
                TwitchUser user = twitchUserBuilder.build(message);
                for (TwircListener l : listeners)
                {
                    l.onWhisper(user, message);
                }
            }
            else if (message.getCommand().equals("NOTICE"))
            {
                NoticeEvent notice = noticeBuilder.build(message);
                for (TwircListener l : listeners)
                {
                    l.onNotice(notice);
                }
            }
            else if (message.getCommand().equals("MODE"))
            {
                ModeEvent mode = modeBuilder.build(message);
                for (TwircListener l : listeners)
                {
                    l.onMode(mode);
                }
            }
            else if (message.getCommand().equals("USERSTATE"))
            {
                UserStateEvent userstate = userstateBuilder.build(message);
                for (TwircListener l : listeners)
                {
                    l.onUserState(userstate);
                }
            }
            else if (message.getCommand().equals("USERNOTICE"))
            {
                UserNoticeEvent usernotice = usernoticeBuilder.build(message);
                for (TwircListener l : listeners)
                {
                    l.onUsernotice(usernotice);
                }
            }
            else if (message.getCommand().equals("ROOMSTATE"))
            {
                RoomstateEvent roomstate = roomstateBuilder.build(message);
                for (TwircListener l : listeners)
                {
                    l.onRoomstate(roomstate);
                }
            }
            else if (message.getCommand().equals("ACTION"))
            {
                ActionEvent action = actionBuilder.build(message);
                TwitchUser user = twitchUserBuilder.build(message);
                for (TwircListener l : listeners)
                {
                    l.onAction(user, message);
                }
            }
            else if (message.getCommand().equals("CLEARCHAT"))
            {
                ClearChatEvent clearChat = clearChatBuilder.build(message);
                for (TwircListener l : listeners)
                {
                    l.onClearChat(clearChat);
                }
            }
            else if (message.getCommand().equals("HOSTTARGET"))
            {
                HostTargetEvent hostTarget = hostTargetBuilder.build(message);
                for (TwircListener l : listeners)
                {
                    l.onHost(hostTarget);
                }
            }
            else if (message.getCommand().equals("CAP"))
            {
                System.out.println("a CAP event??");
                //TwircBot.log.info("Oh shit a CAP event");
                //Twitch might in the future implement more of these...
            }
            else if (message.getCommand().equals("GLOBALUSERSTATE"))
            {
                GlobalUserStateEvent globalUserState = globalUserStateBuilder.build(message);
                for (TwircListener l : listeners)
                {
                    l.onGlobalUserstate(globalUserState);
                }
            }
            else if (message.getCommand().equals("[0-9]+"))
            {
                //Code 353 is USER LIST messages, which lists users online separated by a space
                if (message.getCommand().equals("353"))
                {
                    List<String> users = Arrays.asList(message.getContent().split(" "));
                    online.addAll(users);
                }
                else if (message.getCommand().equals("366"))
                {
                    Set<String> users = Collections.unmodifiableSet(online);
                    for (TwircListener l : listeners)
                    {
                        l.onNamesList(users);
                    }
                }
            }
            else
            {
                //If we've gotten all the way down here, we don't know this message's type
                for (TwircListener l : listeners)
                {
                    l.onUnknown(line);
                }
            }
        }
    }

    private void handleTwitchNotify(TwitchMessage message)
    {
        SubscriberEvent subEvent = subscriberBuilder.build(message);
        if (subEvent != null)
        {
            for (TwircListener l : listeners)
            {
                l.onSubscriberEvent(subEvent);
            }
        }
    }

    private String parseUsername(String prefix)
    {
        /* The user name is extracted from the message's prefix.
		 * JOIN or PART messages are formated like this:
		 *
		 * :twitch_username!twitch_username@twitch_username.tmi.twitch.tv JOIN #channel
		 */
        return prefix.substring(prefix.charAt(0) == ':' ? 1 : 0, prefix.indexOf('!'));
    }

    public enum BotType
    {
        COMMANDS, MUSIC, WHISPER
    }
}
