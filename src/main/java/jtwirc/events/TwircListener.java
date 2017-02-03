package jtwirc.events;

import jtwirc.types.clearChat.ClearChatEvent;
import jtwirc.types.globaluserstate.GlobalUserStateEvent;
import jtwirc.types.hostTarget.HostTargetEvent;
import jtwirc.types.mode.ModeEvent;
import jtwirc.types.notice.NoticeEvent;
import jtwirc.types.roomstate.RoomstateEvent;
import jtwirc.types.subscriberEvent.SubscriberEvent;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.usernotice.UserNoticeEvent;
import jtwirc.types.users.TwitchUser;
import jtwirc.types.users.UserStateEvent;

import java.util.Collection;

public interface TwircListener
{

    /**
     * Fires for ever incoming message <b>except PING</b>.
     *
     * @param unformatedMessage The incoming message exactly as it looks from Twitch
     */
    void onAnything(String unformatedMessage);

    /**
     * Fires for incoming PRIVMSG to the channel the bot is joined in
     *
     * @param sender  The user who sent the message. Parsed from the incoming message's tag
     * @param message The message that was sent, with the tag removed
     */
    void onPrivMsg(TwitchUser sender, TwitchMessage message);

    /**
     * Fires for incoming WHISPERS directed at the bot
     *
     * @param sender  The user who sent the whisper. Parsed from the incoming message's tag
     * @param message The whisper that was sent, with the tag removed
     */
    void onWhisper(TwitchUser sender, TwitchMessage message);

    /**
     * Fires when the bot receives a JOIN from Twitch. Note that Twitch sometimes drops
     * PART messages, so we might receive a JOIN from a user who we never saw PART. Another
     * important thing to note is that for large channels (1k chatters +), Twitch only sends
     * JOINS/PARTS for moderators.<br><br>
     * <p>
     * Also worth noting is that we don't see any properties for the joining user, we only see his/her
     * Twitch user name in lower case
     *
     * @param joinedNick The joining users Twitch user name, in lower case
     */
    void onJoin(String joinedNick);

    /**
     * Fires when the bot receives a PART from Twitch. Note that Twitch sometimes drops
     * JOIN messages, so we might receive a PART from a user who we never saw JOIN. Another
     * important thing to note is that for large channels (1k chatters +), Twitch only sends
     * JOINS/PARTS for moderators.<br><br>
     * <p>
     * Also worth noting is that we don't see any properties for the parting user, we only see his/her
     * Twitch user name in lower case
     *
     * @param partedNick The parting users Twitch user name, in lower case
     */
    void onPart(String partedNick);

    /**
     * Fires when we've successfully connected to Twitch's server and joined the channel
     */
    void onConnect();

    /**
     * Fires when we've disconnected from Twitch's server. <br>
     * We can try to reconnect onDisconnect
     */
    void onDisconnect();

    /**
     * Fires whenever we receive a NOTICE from Twitch. See {@link NoticeEvent }<br>
     * NOTICE tells us about certain events, such as being Timed Out,
     *
     * @param notice The notice we received.
     */
    void onNotice(NoticeEvent notice);

    /**
     * Fires whenever we receive a HOSTTARGET from Twitch. See {@link HostTargetEvent }
     *
     * @param hostNotice The host notice we received.
     */
    void onHost(HostTargetEvent hostNotice);

    /**
     * Fires whenever we receive information about a subscriber event from Twitch. See {@link SubscriberEvent}
     *
     * @param subscriberEvent The event we received.
     */
    void onSubscriberEvent(SubscriberEvent subscriberEvent);

    /**
     * Fires whenever we receive a MODE from Twitch. See {@link ModeEvent}.<br>
     * A mode means that a user gained or lost moderator status. However, this
     * is unreliable, and you should consider looking at the {@link TwitchUser } you
     * receive in the {@link #onPrivMsg(TwitchUser, TwitchMessage)} instead. Twitch sends
     * mode notices every now and then, and does not reliably reflect a users current status
     *
     * @param mode The mode notice
     */
    void onMode(ModeEvent mode);

    /**
     * Fires whenever we receive a USERSTATE from Twitch. See {@link UserStateEvent }<br>
     * USERSTATE is sent whenever the bot sends a message to Twirc. You should <b>NEVER</b> respond
     * to a USERSTATE, as that will create a cycle that will get your bot banned for spamming Twitch's
     * server
     *
     * @param userstate The user state we received
     */
    void onUserState(UserStateEvent userstate);

    /**
     * Fires whenever we receive a GLOBALUSERSTATE from Twitch. See {@link GlobalUserStateEvent}
     * GLOBALUSERSTATE is sent on successful login, if the capabilities have been acknowledged before then.
     *
     * @param event The global user state we received
     */
    void onGlobalUserstate(GlobalUserStateEvent event);

    /**
     * Fires whenever we receive a ROOMSTATE from Twitch. See {@link RoomstateEvent }<br>
     * ROOMSTATE is sent when joining a channel and every time one of the chat room settings,
     * like slow mode, change. <br>
     * The message on join contains all room settings. <br>
     * Changes only contain the relevant tag.
     *
     * @param roomstate The room state we received
     */
    void onRoomstate(RoomstateEvent roomstate);

    /**
     * Fires when we receive a CLEARCHAT from Twitch. See {@link ClearChatEvent }<br>
     * CLEARCHAT comes in two modes: <ul>
     * <li>USER - This clears everything a certain user has written in chat
     * <li>TOTAL - This clear everything in chat
     * </ul>
     *
     * @param clearChat The clear chat notice we received
     */
    void onClearChat(ClearChatEvent clearChat);

    /**
     * Fires when we've successfully joined a channel and retrieved the list of
     * all users that were online. <br><br>
     * The <code>Collection</code> that is passed with this method contains
     * the names of all the users that we received by the channels names-list. All these names are in lower case.<br>
     * Note that this list is read-only. Trying to make changes to it will result in a <code>UnsupportedOperationException</code><br><br>
     * Future changes to whom are online will be noticed via {@link #onJoin(String)} and {@link #onPart(String)} events
     *
     * @param namesList The unmodifiable collection of all users that Twitch told us were online in this channel.
     */
    void onNamesList(Collection<String> namesList);

    /**
     * Fires when we receive a USERNOTICE from Twitch. See {@link UserNoticeEvent }<br>
     * A Usernotice tells us about a re-subscription event, either to our channel or to the channel
     * we are hosting.
     *
     * @param usernotice The Usernotice we received
     */
    void onUsernotice(UserNoticeEvent usernotice);


    /**
     * Fires when we received a message we could not categorize. This might happen
     * if Twitch changes something suddenly, so we cannot parse the incomming message.
     *
     * @param unformatedMessage The incoming message exactly as it looks from Twitch
     */
    void onUnknown(String unformatedMessage);

    /**
     * Fires when someone does a /me action
     *
     * @param sender  The user who sent the message. Parsed from the incoming message's tag
     * @param message The message that was sent, with the tag removed
     */
    void onAction(TwitchUser sender, TwitchMessage message);
}
