package com.gikk.twirk.common.command.commands.util;

import com.gikk.twirk.common.command.CommandBase;
import com.gikk.twirk.common.listeners.MessageListener;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;
import com.gikk.twirk.utils.Defaults;
import com.gikk.twirk.utils.MessageSending;
import todo.ChirpBot;

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
            ChirpBot.shoutoutList.clear();
            ChirpBot.welcomeList.clear();
            ChirpBot.chatBubbleList.clear();
            MessageListener.peopleWhoTalked.clear();
            ChirpBot.saveAllTheThings();
        }
    }
}
