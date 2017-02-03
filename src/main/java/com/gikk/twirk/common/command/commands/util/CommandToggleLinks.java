package com.gikk.twirk.common.command.commands.util;

import com.gikk.twirk.common.command.CommandBase;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;
import com.gikk.twirk.utils.Defaults;
import com.gikk.twirk.utils.MessageSending;
import todo.ChirpBot;

public class CommandToggleLinks extends CommandBase
{
    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        if (user.isMod() || user.isBroadcaster())
        {
            if (Defaults.linkPurge)
            {
                Defaults.linkPurge = false;
                MessageSending.sendWhisper(user.getName(), "Purging of links has been toggled off.");
                ChirpBot.extra.setProperty("linkToggle", "false");
            }
            else
            {
                Defaults.linkPurge = true;
                MessageSending.sendWhisper(user.getName(), "Purging of links has been toggled on.");
                ChirpBot.extra.setProperty("linkToggle", "true");
            }
        }
    }
}
