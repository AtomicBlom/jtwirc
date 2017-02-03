package jtwirc.common.command.commands.util;

import jtwirc.TwircBot;
import jtwirc.common.command.CommandBase;
import jtwirc.common.listeners.MessageListener;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.Defaults;
import jtwirc.utils.MessageSending;

public class CommandToggleStream extends CommandBase
{
    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        if (user.isMod() || user.isBroadcaster())
        {
            if (!Defaults.toggleStream)
            {
                MessageSending.sendWhisper(user.getName(), "Auto-giving away " + Defaults.getPointName() + " has been toggled on!");
                Defaults.totalPoints = (long) 0;
                Defaults.toggleStream = true;
            }
            else if (Defaults.toggleStream)
            {
                MessageSending.sendWhisper(user.getName(), "Auto-giving away " + Defaults.getPointName() + " has been toggled off!");
                MessageSending.sendNormalMessage("This time I gave away " + Defaults.totalPoints + " " + Defaults.getPointName() + " in total!");
                Defaults.toggleStream = false;
            }
            TwircBot.shoutoutList.clear();
            TwircBot.welcomeList.clear();
            TwircBot.chatBubbleList.clear();
            MessageListener.peopleWhoTalked.clear();
            TwircBot.saveAllTheThings();
        }
    }
}
