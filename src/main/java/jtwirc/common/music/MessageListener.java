package jtwirc.common.music;

import jtwirc.common.music.utils.AudioPlayer;
import jtwirc.events.TwirkListenerBaseImpl;
import jtwirc.todo.ChirpBot;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;

public class MessageListener extends TwirkListenerBaseImpl
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
                if (ChirpBot.patronSounds.containsKey(sender.getName()) && !ChirpBot.peopleWhoHaveSpoke.contains(sender.getName()))
                {
                    player.playAudioFile(ChirpBot.patronSounds.get(sender.getName()));
                    ChirpBot.peopleWhoHaveSpoke.add(sender.getName());
                }
            }
        }
    }

    @Override
    public void onConnect()
    {
        ChirpBot.bots.get(ChirpBot.BOT_COMMANDS).channelMessage(String.valueOf(ChirpBot.musicProp.get("intro")));
    }
}
