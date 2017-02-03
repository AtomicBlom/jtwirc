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

/**
 * Convenience class.<br>
 * Instead if extending the TwirkListener interface, you can instead extend this class. That
 * way, you only need to implement those methods that you want to use. This class implements
 * all required methods, but leaves the method body blank. Thus, you don't need to call the
 * {@code .super()} methods at all.
 */
public abstract class TwirkListenerBaseImpl implements TwirkListener
{

    @Override
    public void onAnything(String line)
    {
    }

    @Override
    public void onPrivMsg(TwitchUser sender, TwitchMessage message)
    {
    }

    @Override
    public void onWhisper(TwitchUser sender, TwitchMessage message)
    {
    }

    @Override
    public void onAction(TwitchUser sender, TwitchMessage message)
    {
    }

    @Override
    public void onJoin(String joinedNick)
    {
    }

    @Override
    public void onPart(String partedNick)
    {
    }

    @Override
    public void onConnect()
    {
    }

    @Override
    public void onDisconnect()
    {
    }

    @Override
    public void onNotice(NoticeEvent notice)
    {
    }

    @Override
    public void onHost(HostTargetEvent hostNotice)
    {
    }

    @Override
    public void onMode(ModeEvent mode)
    {
    }

    @Override
    public void onSubscriberEvent(SubscriberEvent subscriberEvent)
    {
    }

    @Override
    public void onUserState(UserStateEvent userstate)
    {
    }

    @Override
    public void onRoomstate(RoomstateEvent roomstate)
    {
    }

    @Override
    public void onClearChat(ClearChatEvent clearChat)
    {
    }

    @Override
    public void onNamesList(Collection<String> namesList)
    {
    }

    @Override
    public void onUsernotice(UserNoticeEvent usernotice)
    {
    }

    @Override
    public void onGlobalUserstate(GlobalUserStateEvent event)
    {
    }

    @Override
    public void onUnknown(String line)
    {
    }
}
