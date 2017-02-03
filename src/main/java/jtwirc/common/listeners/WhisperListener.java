package jtwirc.common.listeners;

import jtwirc.Twirk;
import jtwirc.events.TwirkListenerBaseImpl;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;

public class WhisperListener extends TwirkListenerBaseImpl
{

    private Twirk.BotType type;

    public WhisperListener(Twirk.BotType type)
    {
        this.type = type;
    }

    @Override
    public void onWhisper(TwitchUser sender, TwitchMessage message)
    {

    }
}
