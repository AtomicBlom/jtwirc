package jtwirc.common.module;

import jtwirc.Twirk;
import jtwirc.common.command.commands.faq.CommandPoints;
import jtwirc.common.threads.ModCommon;
import jtwirc.common.threads.ScheduleCommon;
import jtwirc.common.threads.ViewerCommon;
import jtwirc.events.TwirkListenerBaseImpl;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.Defaults;
import jtwirc.utils.MessageSending;

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
