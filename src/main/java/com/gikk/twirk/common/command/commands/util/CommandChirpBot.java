package com.gikk.twirk.common.command.commands.util;

import com.gikk.twirk.common.command.CommandBase;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;
import com.gikk.twirk.utils.Defaults;
import com.gikk.twirk.utils.MessageSending;

public class CommandChirpBot extends CommandBase
{

    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        if (!Defaults.whisperToggle)
        {
            if (!Defaults.isVip)
            {
                MessageSending.sendWhisper(user.getName().toLowerCase(), "This is ChirpBot. A Twitch bot specifically designed for WAPC!");
            }
            else
            {
                MessageSending.sendWhisper(user.getName().toLowerCase(), "This is ChirpBot. A Twitch bot specifically designed for WAPC!");
            }
        }
        else
        {
            if (!Defaults.isVip)
            {
                MessageSending.sendNormalMessage("This is ChirpBot. A Twitch bot specifically designed for WAPC!");
            }
            else
            {
                MessageSending.sendNormalMessage("This is ChirpBot. A Twitch bot specifically designed for WAPC!");
            }
        }
    }
}
