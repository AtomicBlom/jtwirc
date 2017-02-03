package com.gikk.twirk.common.command.commands.util;

import com.gikk.twirk.common.command.CommandBase;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;
import com.gikk.twirk.utils.Defaults;
import com.gikk.twirk.utils.MessageSending;
import todo.ChirpBot;

public class CommandToggleWhisper extends CommandBase
{
    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        if (user.isMod() || user.isBroadcaster())
        {
            if (Defaults.whisperToggle)
            {
                Defaults.whisperToggle = false;
                MessageSending.sendWhisper(user.getName().toLowerCase(), "Toggled command whispers off.");
                ChirpBot.extra.setProperty("whisperToggle", "false");
            }
            else
            {
                Defaults.whisperToggle = true;
                MessageSending.sendWhisper(user.getName().toLowerCase(), "Toggled command whispers on.");
                ChirpBot.extra.setProperty("whisperToggle", "true");
            }
        }
    }
}
