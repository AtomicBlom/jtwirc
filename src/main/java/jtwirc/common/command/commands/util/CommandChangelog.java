package jtwirc.common.command.commands.util;

import jtwirc.common.command.CommandBase;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.Defaults;
import jtwirc.utils.MessageSending;

public class CommandChangelog extends CommandBase
{

    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        MessageSending.sendNormalMessage(Defaults.CHANGELOG);
    }
}
