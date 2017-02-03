package com.gikk.twirk.events;

import com.gikk.twirk.types.clearChat.ClearChatEvent;
import com.gikk.twirk.types.globaluserstate.GlobalUserStateEvent;
import com.gikk.twirk.types.hostTarget.HostTargetEvent;
import com.gikk.twirk.types.mode.ModeEvent;
import com.gikk.twirk.types.notice.NoticeEvent;
import com.gikk.twirk.types.roomstate.RoomstateEvent;
import com.gikk.twirk.types.subscriberEvent.SubscriberEvent;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.usernotice.UserNoticeEvent;
import com.gikk.twirk.types.users.TwitchUser;
import com.gikk.twirk.types.users.UserStateEvent;

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
