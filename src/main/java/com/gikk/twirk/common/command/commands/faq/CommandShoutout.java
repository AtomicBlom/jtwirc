package com.gikk.twirk.common.command.commands.faq;

import com.gikk.twirk.common.command.CommandBase;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;
import com.gikk.twirk.utils.MessageSending;
import todo.ChirpBot;

public class CommandShoutout extends CommandBase
{

    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        String streamer = message.getContent().substring(command.length() + 1);
        ChirpBot.shoutoutList.clear();
        ChirpBot.shoutoutList.put(System.currentTimeMillis(), streamer);
        MessageSending.sendNormalMessage(String.format("%s is awesome, you should give them a follow. http://www.twitch.tv/%s", streamer, streamer));
        ChirpBot.saveAllTheThings();
    }
}
