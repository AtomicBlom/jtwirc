package jtwirc.common.command.utils;

import jtwirc.TwircBot;
import jtwirc.common.command.CommandBase;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.MessageSending;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("ConstantConditions")
public class EditCommands extends CommandBase
{

    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);

        if (user.isMod() || user.isBroadcaster())
        {
            if (TwircBot.commandList.containsKey(args[1]))
            {
                List<String> response = new ArrayList<>();
                response.addAll(Arrays.asList(args).subList(2, args.length));
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < response.size(); i++)
                {
                    result.append(response.get(i));
                    if (i != response.size() - 1)
                    {
                        result.append(" ");
                    }
                }
                String responseComplete = result.toString();
                editCommand(args[1], responseComplete, user);
            }
        }
        else
        {
            MessageSending.sendWhisper(user.getName(), "You don't have permission to edit a command");
        }
    }

    private void editCommand(String command, String responseComplete, TwitchUser user)
    {
        if (TwircBot.commandList.containsKey(command))
        {
            TwircBot.commandList.put(command, responseComplete);
            if (TwircBot.commandpermList.containsKey(command))
            {
                TwircBot.commandpermList.put(command, responseComplete);
            }
            MessageSending.sendWhisper(user.getName(), "Command has been edited");
            TwircBot.log.info(command + " edited");
            TwircBot.saveAllTheThings();
        }
        else
        {
            MessageSending.sendWhisper(user.getName(), "Command doesn't exist");
        }
    }
}
