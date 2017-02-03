package com.gikk.twirk;

import com.gikk.twirk.Twirk.BotType;
import com.gikk.twirk.types.action.ActionBuilder;
import com.gikk.twirk.types.action.DefaultActionBuilder;
import com.gikk.twirk.types.clearChat.ClearChatBuilder;
import com.gikk.twirk.types.clearChat.DefaultClearChatBuilder;
import com.gikk.twirk.types.globaluserstate.DefaultGlobalStateBuilder;
import com.gikk.twirk.types.globaluserstate.GlobalUserStateBuilder;
import com.gikk.twirk.types.hostTarget.DefaultHostTargetBuilder;
import com.gikk.twirk.types.hostTarget.HostTargetBuilder;
import com.gikk.twirk.types.mode.DefaultModeBuilder;
import com.gikk.twirk.types.mode.ModeBuilder;
import com.gikk.twirk.types.notice.DefaultNoticeBuilder;
import com.gikk.twirk.types.notice.NoticeBuilder;
import com.gikk.twirk.types.roomstate.DefaultRoomstateBuilder;
import com.gikk.twirk.types.roomstate.RoomstateBuilder;
import com.gikk.twirk.types.subscriberEvent.DefaultSubscriberEventBuilder;
import com.gikk.twirk.types.subscriberEvent.SubscriberEventBuilder;
import com.gikk.twirk.types.twitchMessage.DefaultTwitchMessageBuilder;
import com.gikk.twirk.types.twitchMessage.TwitchMessageBuilder;
import com.gikk.twirk.types.usernotice.DefaultUsernoticeBuilder;
import com.gikk.twirk.types.usernotice.UsernoticeBuilder;
import com.gikk.twirk.types.users.DefaultTwitchUserBuilder;
import com.gikk.twirk.types.users.DefaultUserstateBuilder;
import com.gikk.twirk.types.users.TwitchUserBuilder;
import com.gikk.twirk.types.users.UserstateBuilder;

/**
 * Class for creating instances of {@link Twirk}.<br>
 * To build an instance of {@link Twirk}, the user has to supply the bot's nick and
 * oAuth token. To generate a oAuth token, visit <a href="https://twitchapps.com/tmi/">https://twitchapps.com/tmi/</a><br><br>
 * <p>
 * If you want to change any setting except the required once, use one of the
 * setter methods related to this object. When all settings are performed, use the
 * {@link #build()} method.
 */
public class TwirkBuilder
{
    //***********************************************************
    // 				VARIABLES
    //***********************************************************
    boolean verboseMode = false;

    String server = "irc.chat.twitch.tv";
    int port = 80;
    boolean useSSL = false;

    BotType type;

    String nick = "";
    String oauth = "";
    String channel = "";

    private ClearChatBuilder clearChatBuilder;
    private HostTargetBuilder hostTargetBuilder;
    private ModeBuilder modeBuilder;
    private NoticeBuilder noticeBuilder;
    private RoomstateBuilder roomstateBuilder;
    private TwitchMessageBuilder twitchMessageBuilder;
    private TwitchUserBuilder twitchUserBuilder;
    private UserstateBuilder userstateBuilder;
    private SubscriberEventBuilder subEventBuilder;
    private UsernoticeBuilder usernoticeBuilder;
    private GlobalUserStateBuilder globalUserStateBuilder;
    private ActionBuilder actionBuilder;

    //***********************************************************
    // 				CONSTRUCTOR
    //***********************************************************

    /**
     * Creates a new {@link TwirkBuilder}. To build an instance of {@link Twirk}, we must have
     * the bot's account nick, and the bot's IRC oAuth token (which can be retrieved from Twitch).
     * See {@link TwirkBuilder}
     *
     * @param channel The channel the bot should join
     * @param nick    The bot's account name (on Twitch)
     * @param oauth   The bot's IRC oAuth token (on Twitch)
     * @param type    bot type
     */
    public TwirkBuilder(String channel, String nick, String oauth, BotType type)
    {
        this.channel = channel;
        this.nick = nick;
        this.oauth = oauth;
        this.type = type;
    }

    //***********************************************************
    // 				PUBLIC
    //***********************************************************

    /**
     * Sets the server which {@link Twirk} will try to connect to Twitch via
     *
     * @param server The server adress
     * @return this
     */
    public TwirkBuilder setServer(String server)
    {
        this.server = server;
        return this;
    }

    /**
     * Sets the port which the {@link Twirk} object will try to connect to Twitch via
     *
     * @param port The port number
     * @return this
     */
    public TwirkBuilder setPort(int port)
    {
        this.port = port;
        return this;
    }

    /**
     * Decides whether we should use a SSL connection or not. Default is {@code true}. If you do
     * change this, remember to change the port too, since Twitch listens for SSL and non-SSL traffic
     * on different ports.
     * See <a href="https://github.com/justintv/Twitch-API/blob/master/IRC.md">
     * https://github.com/justintv/Twitch-API/blob/master/IRC.md </a>
     *
     * @param ssl Whether we should use SSL connection or not.
     * @return this
     */
    public TwirkBuilder setSSL(boolean ssl)
    {
        this.useSSL = ssl;
        return this;
    }

    /**
     * Sets the {@link Twirk} object to VerboseMode<br>
     * In VerboseMode, every message that is received by {@link Twirk} will be printed to console. Default value is {@code false}
     *
     * @param verboseMode {@code true} is you want {@link Twirk} in VerboseMode
     * @return this
     */
    public TwirkBuilder setVerboseMode(boolean verboseMode)
    {
        this.verboseMode = verboseMode;
        return this;
    }

    /**
     * Retrieves the assigned {@link ClearChatBuilder}, or the default one, if none is assigned.
     *
     * @return This builders current {@link ClearChatBuilder}
     */
    ClearChatBuilder getClearChatBuilder()
    {
        return clearChatBuilder != null ? clearChatBuilder : new DefaultClearChatBuilder();
    }

    /**
     * Sets the {@link ClearChatBuilder}. Useful if you want to use your custom implementations of a {@link ClearChatBuilder}. If
     * no {@link ClearChatBuilder} is assigned, the created {@link Twirk} object will use the default implementation.
     *
     * @param clearChatBuilder The {@link UserstateBuilder} you want the {@link Twirk} object to use
     * @return this
     */
    public TwirkBuilder setClearChatBuilder(ClearChatBuilder clearChatBuilder)
    {
        this.clearChatBuilder = clearChatBuilder;
        return this;
    }

    /**
     * Retrieves the assigned {@link HostTargetBuilder}, or the default one, if none is assigned.
     *
     * @return This builders current {@link HostTargetBuilder}
     */
    HostTargetBuilder getHostTargetBuilder()
    {
        return hostTargetBuilder != null ? hostTargetBuilder : new DefaultHostTargetBuilder();
    }

    /**
     * Sets the HostTargetBuilder. Useful if you want to use your custom implementations of a HostTargetBuilder. If
     * no HostTargetBuilder is assigned, the created {@link Twirk} object will use the default implementation.
     *
     * @param hostTargetBuilder The {@link HostTargetBuilder} you want the {@link Twirk} object to use
     * @return this
     */
    public TwirkBuilder setHostTargetBuilder(HostTargetBuilder hostTargetBuilder)
    {
        this.hostTargetBuilder = hostTargetBuilder;
        return this;
    }

    /**
     * Retrieves the assigned {@link ModeBuilder}, or the default one, if none is assigned.
     *
     * @return This builders current {@link ModeBuilder}
     */
    ModeBuilder getModeBuilder()
    {
        return modeBuilder != null ? modeBuilder : new DefaultModeBuilder();
    }

    /**
     * Sets the {@link ModeBuilder}. Useful if you want to use your custom implementations of a {@link ModeBuilder}. If
     * no {@link ModeBuilder} is assigned, the created {@link Twirk} object will use the default implementation.
     *
     * @param modeBuilder The {@link ModeBuilder} you want the {@link Twirk} object to use
     * @return this
     */
    public TwirkBuilder setModeBuilder(ModeBuilder modeBuilder)
    {
        this.modeBuilder = modeBuilder;
        return this;
    }

    /**
     * Retrieves the assigned {@link NoticeBuilder}, or the default one, if none is assigned.
     *
     * @return This builders current {@link NoticeBuilder}
     */
    NoticeBuilder getNoticeBuilder()
    {
        return noticeBuilder != null ? noticeBuilder : new DefaultNoticeBuilder();
    }

    /**
     * Sets the {@link NoticeBuilder}. Useful if you want to use your custom implementations of a {@link NoticeBuilder}. If
     * no {@link NoticeBuilder} is assigned, the created {@link Twirk} object will use the default implementation.
     *
     * @param noticeBuilder The {@link NoticeBuilder} you want the {@link Twirk} object to use
     * @return this
     */
    public TwirkBuilder setNoticeBuilder(NoticeBuilder noticeBuilder)
    {
        this.noticeBuilder = noticeBuilder;
        return this;
    }

    /**
     * Retrieves the assigned {@link RoomstateBuilder}, or the default one, if none is assigned.
     *
     * @return This builders current {@link RoomstateBuilder}
     */
    RoomstateBuilder getRoomstateBuilder()
    {
        return roomstateBuilder != null ? roomstateBuilder : new DefaultRoomstateBuilder();
    }

    /**
     * Sets the {@link RoomstateBuilder}. Useful if you want to use your custom implementations of a {@link RoomstateBuilder}. If
     * no {@link RoomstateBuilder} is assigned, the created {@link Twirk} object will use the default implementation.
     *
     * @param roomstateBuilder The {@link RoomstateBuilder} you want the {@link Twirk} object to use
     * @return this
     */
    public TwirkBuilder setRoomstateBuilder(RoomstateBuilder roomstateBuilder)
    {
        this.roomstateBuilder = roomstateBuilder;
        return this;
    }

    /**
     * Retrieves the assigned {@link TwitchMessageBuilder}, or the default one, if none is assigned.
     *
     * @return This builders current {@link TwitchMessageBuilder}
     */
    TwitchMessageBuilder getTwitchMessageBuilder()
    {
        return twitchMessageBuilder != null ? twitchMessageBuilder : new DefaultTwitchMessageBuilder();
    }

    /**
     * Sets the {@link TwitchMessageBuilder}. Useful if you want to use your custom implementations of a {@link TwitchMessageBuilder}. If
     * no {@link TwitchMessageBuilder} is assigned, the created {@link Twirk} object will use the default implementation.
     *
     * @param twitchMessageBuilder The {@link TwitchMessageBuilder} you want the {@link Twirk} object to use
     * @return this
     */
    public TwirkBuilder setTwitchMessageBuilder(TwitchMessageBuilder twitchMessageBuilder)
    {
        this.twitchMessageBuilder = twitchMessageBuilder;
        return this;
    }

    /**
     * Retrieves the assigned {@link TwitchUserBuilder}, or the default one, if none is assigned.
     *
     * @return This builders current {@link TwitchUserBuilder}
     */
    TwitchUserBuilder getTwitchUserBuilder()
    {
        return twitchUserBuilder != null ? twitchUserBuilder : new DefaultTwitchUserBuilder();
    }

    /**
     * Sets the {@link TwitchUserBuilder}. Useful if you want to use your custom implementations of a {@link TwitchUserBuilder}. If
     * no {@link TwitchUserBuilder} is assigned, the created {@link Twirk} object will use the default implementation.
     *
     * @param twitchUserBuilder The {@link TwitchUserBuilder} you want the {@link Twirk} object to use
     * @return this
     */
    public TwirkBuilder setTwitchUserBuilder(TwitchUserBuilder twitchUserBuilder)
    {
        this.twitchUserBuilder = twitchUserBuilder;
        return this;
    }

    /**
     * Retrieves the assigned {@link UserstateBuilder}, or the default one, if none is assigned.
     *
     * @return This builders current {@link UserstateBuilder}
     */
    UserstateBuilder getUserstateBuilder()
    {
        return userstateBuilder != null ? userstateBuilder : new DefaultUserstateBuilder();
    }

    /**
     * Sets the {@link UserstateBuilder}. Useful if you want to use your custom implementations of a {@link UserstateBuilder}. If
     * no {@link UserstateBuilder} is assigned, the created {@link Twirk} object will use the default implementation.
     *
     * @param userstateBuilder The {@link UserstateBuilder} you want the {@link Twirk} object to use
     * @return this
     */
    public TwirkBuilder setUserstateBuilder(UserstateBuilder userstateBuilder)
    {
        this.userstateBuilder = userstateBuilder;
        return this;
    }

    /**
     * Retrieves the assigned {@link SubscriberEventBuilder}, or the default one, if none is assigned.
     *
     * @return This builders current {@link SubscriberEventBuilder}
     */
    SubscriberEventBuilder getSubscriberEventBuilder()
    {
        return subEventBuilder != null ? subEventBuilder : new DefaultSubscriberEventBuilder();
    }

    /**
     * Sets the {@link SubscriberEventBuilder}. Useful if you want to use your custom implementations of a {@link SubscriberEventBuilder}. If
     * no {@link SubscriberEventBuilder} is assigned, the created {@link Twirk} object will use the default implementation.
     *
     * @param subEventBuilder The {@link SubscriberEventBuilder} you want the {@link Twirk} object to use
     * @return this
     */
    public TwirkBuilder setSubscriberEventBuilder(SubscriberEventBuilder subEventBuilder)
    {
        this.subEventBuilder = subEventBuilder;
        return this;
    }

    /**
     * Retrieves the assigned {@link UsernoticeBuilder}, or the default one, if none is assigned.
     *
     * @return This builders current {@link UsernoticeBuilder}
     */
    UsernoticeBuilder getUsernoticeBuilder()
    {
        return usernoticeBuilder != null ? usernoticeBuilder : new DefaultUsernoticeBuilder();
    }

    GlobalUserStateBuilder getGlobalUserStateBuilder()
    {
        return globalUserStateBuilder != null ? globalUserStateBuilder : new DefaultGlobalStateBuilder();
    }

    /**
     * Retrieves the assigned {@link ActionBuilder}, or the default one, if none is assigned.
     *
     * @return This builders current {@link ActionBuilder}
     */
    ActionBuilder getActionBuilder()
    {
        return actionBuilder != null ? actionBuilder : new DefaultActionBuilder();
    }

    /**
     * Sets the {@link ActionBuilder}. Useful if you want to use your custom implementations of a {@link ActionBuilder}. If
     * no {@link ActionBuilder} is assigned, the created {@link Twirk} object will use the default implementation.
     *
     * @param actionBuilder The {@link ActionBuilder} you want the {@link Twirk} object to use
     * @return this
     */
    public TwirkBuilder setActionBuilder(ActionBuilder actionBuilder)
    {
        this.actionBuilder = actionBuilder;
        return this;
    }

    /**
     * Creates a Twirk object, with the parameters assigned to this
     * builder.
     *
     * @return A configured Twirk object
     */
    public com.gikk.twirk.Twirk build()
    {
        return new com.gikk.twirk.Twirk(this);
    }

}
