package jtwirc.common.music;

import jtwirc.TwircBot;
import jtwirc.common.music.utils.AudioPlayer;
import jtwirc.events.TwircListenerBaseImpl;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;

public class MessageListener extends TwircListenerBaseImpl
{

    private AudioPlayer player = new AudioPlayer();

    @Override
    public void onPrivMsg(TwitchUser sender, TwitchMessage message)
    {
        super.onPrivMsg(sender, message);
        String[] args = message.getContent().split(" ");
        if (args.length == 2)
        {
            if (args[0].equalsIgnoreCase("!p") && args[1].equalsIgnoreCase("s"))
            {
                if (TwircBot.patronSounds.containsKey(sender.getName()) && !TwircBot.peopleWhoHaveSpoke.contains(sender.getName()))
                {
                    player.playAudioFile(TwircBot.patronSounds.get(sender.getName()));
                    TwircBot.peopleWhoHaveSpoke.add(sender.getName());
                }
            }
        }
    }

    @Override
    public void onConnect()
    {
        TwircBot.bots.get(TwircBot.BOT_COMMANDS).channelMessage(String.valueOf(TwircBot.musicProp.get("intro")));
    }
}
