package com.gikk.twirk.common.command.commands.partner;

import com.gikk.twirk.common.command.CommandBase;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;
import com.gikk.twirk.utils.Defaults;
import com.gikk.twirk.utils.json.Save;

public class CommandReSub extends CommandBase
{

    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        if (user.isBroadcaster())
        {
            Defaults.oldSub = args[1];
            Save.subMessages();
        }
    }
}
