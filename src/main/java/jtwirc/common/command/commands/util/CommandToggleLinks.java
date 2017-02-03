package jtwirc.common.command.commands.util;

import jtwirc.common.command.CommandBase;
import jtwirc.todo.ChirpBot;
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
                ChirpBot.extra.setProperty("linkToggle", "false");
            }
            else
            {
                Defaults.linkPurge = true;
                MessageSending.sendWhisper(user.getName(), "Purging of links has been toggled on.");
                ChirpBot.extra.setProperty("linkToggle", "true");
            }
        }
    }
}
