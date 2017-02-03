package com.gikk.twirk.common.listeners;

import com.gikk.twirk.Twirk;
import com.gikk.twirk.events.TwirkListenerBaseImpl;
import com.gikk.twirk.types.clearChat.ClearChatEvent;
import com.gikk.twirk.types.hostTarget.HostTargetEvent;
import com.gikk.twirk.types.mode.ModeEvent;
import com.gikk.twirk.types.notice.NoticeEvent;
import com.gikk.twirk.types.roomstate.RoomstateEvent;
import com.gikk.twirk.types.users.UserStateEvent;

import java.util.Collection;

public class InfoListener extends TwirkListenerBaseImpl
{

    private Twirk.BotType type;

    public InfoListener(Twirk.BotType type)
    {
        this.type = type;
    }

    @Override
    public void onJoin(String joinedNick)
    {
        super.onJoin(joinedNick);
    }

    @Override
    public void onPart(String partedNick)
    {
        super.onPart(partedNick);
    }

    @Override
    public void onConnect()
    {
        super.onConnect();
    }

    @Override
    public void onDisconnect()
    {
        super.onDisconnect();
    }

    @Override
    public void onRoomstate(RoomstateEvent roomstate)
    {
        super.onRoomstate(roomstate);
    }

    @Override
    public void onClearChat(ClearChatEvent clearChat)
    {
        super.onClearChat(clearChat);
    }

    @Override
    public void onNamesList(Collection<String> namesList)
    {
        super.onNamesList(namesList);
    }

    @Override
    public void onHost(HostTargetEvent hostNotice)
    {
        super.onHost(hostNotice);
    }

    @Override
    public void onNotice(NoticeEvent notice)
    {
        super.onNotice(notice);
    }

    @Override
    public void onMode(ModeEvent mode)
    {
        super.onMode(mode);
    }

    @Override
    public void onUserState(UserStateEvent userstate)
    {
        super.onUserState(userstate);
    }
}
