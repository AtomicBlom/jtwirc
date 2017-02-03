package jtwirc.common.command.commands.util;

import jtwirc.common.command.CommandBase;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.Defaults;
import jtwirc.utils.MessageSending;

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
