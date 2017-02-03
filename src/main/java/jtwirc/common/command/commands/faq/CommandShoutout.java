package jtwirc.common.command.commands.faq;

import jtwirc.TwircBot;
import jtwirc.common.command.CommandBase;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.MessageSending;

public class CommandShoutout extends CommandBase
{

    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        String streamer = message.getContent().substring(command.length() + 1);
        TwircBot.shoutoutList.clear();
        TwircBot.shoutoutList.put(System.currentTimeMillis(), streamer);
        MessageSending.sendNormalMessage(String.format("%s is awesome, you should give them a follow. http://www.twitch.tv/%s", streamer, streamer));
        TwircBot.saveAllTheThings();
    }
}
