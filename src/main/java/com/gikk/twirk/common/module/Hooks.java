package com.gikk.twirk.common.module;

import com.gikk.twirk.Twirk;
import com.gikk.twirk.common.command.commands.faq.CommandPoints;
import com.gikk.twirk.common.threads.ModCommon;
import com.gikk.twirk.common.threads.ScheduleCommon;
import com.gikk.twirk.common.threads.ViewerCommon;
import com.gikk.twirk.events.TwirkListenerBaseImpl;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;
import com.gikk.twirk.utils.Defaults;
import com.gikk.twirk.utils.MessageSending;

public class Hooks extends TwirkListenerBaseImpl
{

    private Twirk.BotType type;

    public Hooks(Twirk.BotType type)
    {
        this.type = type;
    }

    @Override
    public void onConnect()
    {
        if (type == Twirk.BotType.COMMANDS)
        {
            System.out.println("ChirpBot started and joined " + Defaults.getStreamer() + "'s channel.\nPlease enjoy ChirpBot");
            MessageSending.sendNormalMessage("ChirpBot started. Please enjoy ChirpBot");
        }
    }

    @Override
    public void onPrivMsg(TwitchUser user, TwitchMessage message)
    {
        if (type == Twirk.BotType.COMMANDS)
        {
            if (ViewerCommon.updateViewers.getState().equals(Thread.State.NEW))
            {
                ViewerCommon.updateViewers.start();
            }
            if (CommandPoints.points.getState().equals(Thread.State.NEW))
            {
                CommandPoints.points.start();
            }
            if (ModCommon.updateMods.getState().equals(Thread.State.NEW))
            {
                ModCommon.updateMods.start();
            }
            if (ScheduleCommon.scheduleMessages.getState().equals(Thread.State.NEW))
            {
                ScheduleCommon.scheduleMessages.start();
            }
        }
    }
}
