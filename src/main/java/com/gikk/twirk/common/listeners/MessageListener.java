package com.gikk.twirk.common.listeners;

import com.gikk.twirk.Twirk;
import com.gikk.twirk.events.TwirkListenerBaseImpl;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;
import com.gikk.twirk.utils.Constants;
import todo.ChirpBot;

import java.util.ArrayList;
import java.util.List;

public class MessageListener extends TwirkListenerBaseImpl
{

    public static List<String> peopleWhoTalked = new ArrayList<>();
    private Twirk.BotType type;

    public MessageListener(Twirk.BotType type)
    {
        this.type = type;
    }

    @Override
    public void onPrivMsg(TwitchUser sender, TwitchMessage message)
    {
        checkForHi(sender, message);
    }

    private void checkForHi(TwitchUser sender, TwitchMessage message)
    {
        if (!peopleWhoTalked.contains(sender.getName().toLowerCase()) && containsHello(message))
        {
            ChirpBot.welcomeList.clear();
            ChirpBot.welcomeList.put(System.currentTimeMillis(), sender.getName());
            ChirpBot.saveAllTheThings();
            peopleWhoTalked.add(sender.getName().toLowerCase());
        }
    }

    private boolean containsHello(TwitchMessage message)
    {
        final boolean[] flag = new boolean[1];
        Constants.welcomes.forEach(string -> flag[0] = message.getContent().toLowerCase().equalsIgnoreCase(string));
        return flag[0];
    }
}
