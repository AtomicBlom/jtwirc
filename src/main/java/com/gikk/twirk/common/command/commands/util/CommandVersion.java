package com.gikk.twirk.common.command.commands.util;

import com.gikk.twirk.common.command.CommandBase;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;
import com.gikk.twirk.utils.Defaults;
import com.gikk.twirk.utils.MessageSending;

public class CommandVersion extends CommandBase
{

    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        if (!Defaults.whisperToggle)
        {
            if (Defaults.isVip)
            {
                MessageSending.sendNormalMessage("Current version: " + Defaults.VERSION + " VIP");
            }
            else
            {
                MessageSending.sendNormalMessage("Current version: " + Defaults.VERSION + " Free");
            }
        }
        else
        {
            if (Defaults.isVip)
            {
                MessageSending.sendWhisper(user.getName().toLowerCase(), "Current version: " + Defaults.VERSION + " VIP");
            }
            else
            {
                MessageSending.sendWhisper(user.getName().toLowerCase(), "Current version: " + Defaults.VERSION + " Free");
            }
        }
    }
}
