package jtwirc.common.command.utils;

import jtwirc.TwircBot;
import jtwirc.common.command.CommandBase;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.MessageSending;

@SuppressWarnings("ConstantConditions")
public class DeleteCommands extends CommandBase
{

    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        if (user.isMod() || user.isBroadcaster())
        {
            if (TwircBot.commandList.containsKey(args[1]))
            {
                removeCommand(args[1], user);
            }
        }
        else
        {
            MessageSending.sendWhisper(user.getName(), "You don't have permission to delete a command");
        }
    }

    private void removeCommand(String command, TwitchUser user)
    {
        if (TwircBot.commandList.containsKey(command))
        {
            TwircBot.commandList.remove(command);
            if (TwircBot.commandpermList.containsKey(command))
            {
                TwircBot.commandpermList.remove(command);
            }
            MessageSending.sendWhisper(user.getName(), "Command has been removed");
            TwircBot.log.info(command + " removed");
            TwircBot.saveAllTheThings();
        }
        else
        {
            MessageSending.sendWhisper(user.getName(), "Command doesn't exist");
        }
    }
}
