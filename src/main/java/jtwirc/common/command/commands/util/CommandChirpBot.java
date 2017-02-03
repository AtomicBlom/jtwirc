package jtwirc.common.command.commands.util;

import jtwirc.common.command.CommandBase;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.Defaults;
import jtwirc.utils.MessageSending;

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
