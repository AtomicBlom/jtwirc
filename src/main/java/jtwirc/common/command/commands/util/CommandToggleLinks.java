package jtwirc.common.command.commands.util;

import jtwirc.TwircBot;
import jtwirc.common.command.CommandBase;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.Defaults;
import jtwirc.utils.MessageSending;

public class CommandToggleLinks extends CommandBase
{
    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        if (user.isMod() || user.isBroadcaster())
        {
            if (Defaults.linkPurge)
            {
                Defaults.linkPurge = false;
                MessageSending.sendWhisper(user.getName(), "Purging of links has been toggled off.");
                TwircBot.extra.setProperty("linkToggle", "false");
            }
            else
            {
                Defaults.linkPurge = true;
                MessageSending.sendWhisper(user.getName(), "Purging of links has been toggled on.");
                TwircBot.extra.setProperty("linkToggle", "true");
            }
        }
    }
}
