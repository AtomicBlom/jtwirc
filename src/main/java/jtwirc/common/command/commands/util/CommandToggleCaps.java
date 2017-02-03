package jtwirc.common.command.commands.util;

import jtwirc.TwircBot;
import jtwirc.common.command.CommandBase;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.Defaults;
import jtwirc.utils.MessageSending;

public class CommandToggleCaps extends CommandBase
{
    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        if (user.isMod() || user.isBroadcaster())
        {
            if (Defaults.capsPurge)
            {
                Defaults.capsPurge = false;
                MessageSending.sendWhisper(user.getName(), "Purging of caps has been toggled off.");
                TwircBot.extra.setProperty("capsToggle", "false");
            }
            else
            {
                Defaults.capsPurge = true;
                MessageSending.sendWhisper(user.getName(), "Purging of caps has been toggled on.");
                TwircBot.extra.setProperty("capsToggle", "true");
            }
        }
    }
}
