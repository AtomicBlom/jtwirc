package jtwirc.common.command.utils;

import jtwirc.common.command.CommandBase;
import jtwirc.todo.ChirpBot;
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
            if (ChirpBot.commandList.containsKey(args[1]))
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
        if (ChirpBot.commandList.containsKey(command))
        {
            ChirpBot.commandList.remove(command);
            if (ChirpBot.commandpermList.containsKey(command))
            {
                ChirpBot.commandpermList.remove(command);
            }
            MessageSending.sendWhisper(user.getName(), "Command has been removed");
            ChirpBot.log.info(command + " removed");
            ChirpBot.saveAllTheThings();
        }
        else
        {
            MessageSending.sendWhisper(user.getName(), "Command doesn't exist");
        }
    }
}
