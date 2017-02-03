package jtwirc.common.listeners;

import jtwirc.Twirc;
import jtwirc.TwircBot;
import jtwirc.events.TwircListenerBaseImpl;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MessageListener extends TwircListenerBaseImpl
{

    public static List<String> peopleWhoTalked = new ArrayList<>();
    private Twirc.BotType type;

    public MessageListener(Twirc.BotType type)
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
            TwircBot.welcomeList.clear();
            TwircBot.welcomeList.put(System.currentTimeMillis(), sender.getName());
            TwircBot.saveAllTheThings();
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
