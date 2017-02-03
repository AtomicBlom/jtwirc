package jtwirc.common.command.commands.util;

import jtwirc.common.command.CommandBase;
import jtwirc.todo.ChirpBot;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.Defaults;
import jtwirc.utils.MessageSending;

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
