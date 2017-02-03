package jtwirc.common.listeners;

import jtwirc.Twirc;
import jtwirc.events.TwircListenerBaseImpl;
import jtwirc.types.clearChat.ClearChatEvent;
import jtwirc.types.hostTarget.HostTargetEvent;
import jtwirc.types.mode.ModeEvent;
import jtwirc.types.notice.NoticeEvent;
import jtwirc.types.roomstate.RoomstateEvent;
import jtwirc.types.users.UserStateEvent;

import java.util.Collection;

public class InfoListener extends TwircListenerBaseImpl
{

    private Twirc.BotType type;

    public InfoListener(Twirc.BotType type)
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
