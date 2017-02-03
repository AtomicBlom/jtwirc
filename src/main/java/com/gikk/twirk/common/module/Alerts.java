package com.gikk.twirk.common.module;

import com.gikk.twirk.Twirk;
import com.gikk.twirk.events.TwirkListenerBaseImpl;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.usernotice.UserNoticeEvent;
import com.gikk.twirk.types.users.TwitchUser;
import com.gikk.twirk.utils.Defaults;
import com.gikk.twirk.utils.MessageSending;
import todo.ChirpBot;

public class Alerts extends TwirkListenerBaseImpl
{
	private Twirk.BotType type;

    public Alerts(Twirk.BotType type)
    {
        this.type = type;
    }

    @Override
    public void onUsernotice(UserNoticeEvent event)
    {
		if (type == Twirk.BotType.COMMANDS)
		{
			if (event.getMessage().toLowerCase().contains("subscribed"))
			{
				String username = event.getLogin();
				String[] usr = new String[2];
				usr[0] = username.substring(0, 1);
				usr[1] = username.substring(1);
				usr[0] = usr[0].toUpperCase();
				String name = usr[0] + usr[1];
				MessageSending.sendNormalMessage(Defaults.oldSub.replace("#user", name).replace("#months", String.valueOf(event.getMonths())));
			}
			else if (event.getLogin().equalsIgnoreCase("twitch"))
			{
				String total = event.getSystemMessage().split(" ")[0];
				MessageSending.sendNormalMessage(String.format("Thank you so much for you donation, The Twitch Community has raised %s for charity! You can learn more at link.twitch.tv/charity-cheers", total));
			}
		}
    }

    @Override
    public void onPrivMsg(TwitchUser user, TwitchMessage message)
    {
        if (user.getName().equalsIgnoreCase("twitchnotify") || user.getName().equalsIgnoreCase("witchnotify"))
        {
            if (!message.getContent().contains("just subscribed to"))
            {
                ChirpBot.log.info(message.getContent());
                if (message.getContent().contains("Twitch Prime!"))
                {
                    String[] messenger = message.getContent().split(" ");
                    String username = messenger[0].trim();
                    String[] usr = new String[2];
                    usr[0] = username.substring(0, 1);
                    usr[1] = username.substring(1);
                    usr[0] = usr[0].toUpperCase();
                    String name = usr[0] + usr[1];
                    String sentMessage = Defaults.freeSub.replace("#user", name);
                    MessageSending.sendNormalMessage(sentMessage);
                }
                else
                {
                    String[] messenger = message.getContent().split(" ");
                    String username = messenger[0].trim();
                    String[] usr = new String[2];
                    usr[0] = username.substring(0, 1);
                    usr[1] = username.substring(1);
                    usr[0] = usr[0].toUpperCase();
                    String name = usr[0] + usr[1];
                    String sentMessage = Defaults.newSub.replace("#user", name);
                    MessageSending.sendNormalMessage(sentMessage);
                }
            }
        }
    }
}
