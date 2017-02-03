package jtwirc.common.command.commands.util;

import jtwirc.common.command.CommandBase;
import jtwirc.todo.ChirpBot;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;

public class CommandSave extends CommandBase
{
    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        if (user.isMod() || user.isBroadcaster())
        {
            ChirpBot.saveAllTheThings();
        }
    }
}
