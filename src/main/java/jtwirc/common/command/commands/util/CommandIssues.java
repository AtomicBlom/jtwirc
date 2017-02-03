package jtwirc.common.command.commands.util;

import jtwirc.common.command.CommandBase;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.MessageSending;

public class CommandIssues extends CommandBase
{
    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        MessageSending.sendNormalMessage("Any and all issues with the bot, or suggestions, please fill a ticket in here: https://github.com/TheCricket/TwircBot-Issues/issues");
    }
}
