package jtwirc.common.listeners;

import jtwirc.Twirc;
import jtwirc.events.TwircListenerBaseImpl;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;

public class WhisperListener extends TwircListenerBaseImpl
{

    private Twirc.BotType type;

    public WhisperListener(Twirc.BotType type)
    {
        this.type = type;
    }

    @Override
    public void onWhisper(TwitchUser sender, TwitchMessage message)
    {

    }
}
