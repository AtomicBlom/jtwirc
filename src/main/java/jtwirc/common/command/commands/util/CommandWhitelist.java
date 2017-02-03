package jtwirc.common.command.commands.util;

import jtwirc.common.command.CommandBase;
import jtwirc.todo.ChirpBot;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.MessageSending;
import jtwirc.utils.json.Save;

public class CommandWhitelist extends CommandBase
{

    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        if (user.isMod() || user.isBroadcaster())
        {
            if (ChirpBot.blackList.contains(args[1]))
            {
                ChirpBot.blackList.remove(args[1]);
                MessageSending.sendNormalMessage(args[1] + " has been removed from the blacklist.");
                Save.blackList();
            }
            else
            {
                MessageSending.sendNormalMessage(args[1] + " is not on the blacklist.");
            }
        }
    }
}
