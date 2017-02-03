package com.gikk.twirk.common.command.commands.util;

import com.gikk.twirk.common.command.CommandBase;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;
import com.gikk.twirk.utils.MessageSending;
import com.gikk.twirk.utils.json.Save;
import todo.ChirpBot;

public class CommandBlacklist extends CommandBase
{

    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        if (user.isMod() || user.isBroadcaster())
        {
            if (!ChirpBot.blackList.contains(args[1]))
            {
                ChirpBot.blackList.add(args[1]);
                MessageSending.sendNormalMessage(args[1] + " has been added to the blacklist.");
                Save.blackList();
            }
            else
            {
                MessageSending.sendNormalMessage(args[1] + " is already on the blacklist.");
            }
        }
    }
}
