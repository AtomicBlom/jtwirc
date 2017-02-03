package jtwirc.common.command.commands.util;

import jtwirc.common.command.CommandBase;
import jtwirc.todo.ChirpBot;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.Defaults;
import jtwirc.utils.MessageSending;

public class CommandToggleWOT extends CommandBase
{
    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        if (user.isMod() || user.isBroadcaster())
        {
            if (Defaults.wotPurge)
            {
                Defaults.wotPurge = false;
                MessageSending.sendWhisper(user.getName(), "Purging of walls of text has been toggled off.");
                ChirpBot.extra.setProperty("wotToggle", "false");
            }
            else
            {
                Defaults.wotPurge = true;
                MessageSending.sendWhisper(user.getName(), "Purging of walls of text has been toggled on.");
                ChirpBot.extra.setProperty("wotToggle", "true");
            }
        }
    }
}
