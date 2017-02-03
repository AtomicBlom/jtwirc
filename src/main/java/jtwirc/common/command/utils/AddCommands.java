package jtwirc.common.command.utils;

import jtwirc.common.command.CommandBase;
import jtwirc.todo.ChirpBot;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.MessageSending;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("ConstantConditions")
public class AddCommands extends CommandBase
{

    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        if (user.isMod() || user.isBroadcaster())
        {
            if (args[1].contains("-ul="))
            {
                List<String> response = new ArrayList<>();
                response.addAll(Arrays.asList(args).subList(3, args.length));
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
                if (args[1].equalsIgnoreCase("-ul=mod"))
                {

                    addCommand(args[2], responseComplete, "mod", user);
                }
                else if (args[1].equalsIgnoreCase("-ul=reg"))
                {
                    addCommand(args[2], responseComplete, "reg", user);
                }
                else
                {
                    addCommand(args[2], responseComplete, user);
                }
            }
            else
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
                addCommand(args[1], responseComplete, user);
            }
        }
        else
        {
            MessageSending.sendWhisper(user.getName(), "You don't have permission to add a command");
        }
    }

    private void addCommand(String command, String response, TwitchUser user)
    {
        if (!ChirpBot.commandList.containsKey(command))
        {
            ChirpBot.commandList.put(command, response);
            MessageSending.sendWhisper(user.getName(), "Command added");
            ChirpBot.log.info(command + " added");
            ChirpBot.saveAllTheThings();
        }
        else
        {
            MessageSending.sendWhisper(user.getName(), "Command already exists");
        }
    }

    private void addCommand(String command, String response, String permission, TwitchUser user)
    {
        if (!ChirpBot.commandList.containsKey(command))
        {
            ChirpBot.commandList.put(command, response);
            ChirpBot.commandpermList.put(command, permission);
            MessageSending.sendWhisper(user.getName(), "Command added");
            ChirpBot.log.info(command + " added");
            ChirpBot.saveAllTheThings();
        }
        else
        {
            MessageSending.sendWhisper(user.getName(), "Command already exists");
        }
    }

}
