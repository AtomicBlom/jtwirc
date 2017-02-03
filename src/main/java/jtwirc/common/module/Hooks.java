package jtwirc.common.module;

import jtwirc.Twirc;
import jtwirc.common.command.commands.faq.CommandPoints;
import jtwirc.common.threads.ModCommon;
import jtwirc.common.threads.ScheduleCommon;
import jtwirc.common.threads.ViewerCommon;
import jtwirc.events.TwircListenerBaseImpl;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.Defaults;
import jtwirc.utils.MessageSending;

public class Hooks extends TwircListenerBaseImpl
{

    private Twirc.BotType type;

    public Hooks(Twirc.BotType type)
    {
        this.type = type;
    }

    @Override
    public void onConnect()
    {
        if (type == Twirc.BotType.COMMANDS)
        {
            System.out.println("TwircBot started and joined " + Defaults.getStreamer() + "'s channel.\nPlease enjoy TwircBot");
            MessageSending.sendNormalMessage("TwircBot started. Please enjoy TwircBot");
        }
    }

    @Override
    public void onPrivMsg(TwitchUser user, TwitchMessage message)
    {
        if (type == Twirc.BotType.COMMANDS)
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
