package com.gikk.twirk.common.command.commands.faq;

import com.gikk.twirk.common.command.CommandBase;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;
import com.gikk.twirk.utils.Defaults;
import com.gikk.twirk.utils.MessageSending;

public class CommandTotal extends CommandBase
{
    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        MessageSending.sendNormalMessage("This stream I gave away a total of " + Defaults.totalPoints + " " + Defaults.getPointName());
    }
}
