package com.gikk.twirk.common.command.commands.util;

import com.gikk.twirk.common.command.CommandBase;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;
import com.gikk.twirk.utils.Defaults;
import com.gikk.twirk.utils.MessageSending;
import todo.ChirpBot;

public class CommandToggleCaps extends CommandBase
{
    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        if (user.isMod() || user.isBroadcaster())
        {
            if (Defaults.capsPurge)
            {
                Defaults.capsPurge = false;
                MessageSending.sendWhisper(user.getName(), "Purging of caps has been toggled off.");
                ChirpBot.extra.setProperty("capsToggle", "false");
            }
            else
            {
                Defaults.capsPurge = true;
                MessageSending.sendWhisper(user.getName(), "Purging of caps has been toggled on.");
                ChirpBot.extra.setProperty("capsToggle", "true");
            }
        }
    }
}
