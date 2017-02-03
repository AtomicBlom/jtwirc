package jtwirc.common.command.commands.faq;

import jtwirc.common.command.CommandBase;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.Defaults;
import jtwirc.utils.MessageSending;

public class CommandTotal extends CommandBase
{
    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        MessageSending.sendNormalMessage("This stream I gave away a total of " + Defaults.totalPoints + " " + Defaults.getPointName());
    }
}
