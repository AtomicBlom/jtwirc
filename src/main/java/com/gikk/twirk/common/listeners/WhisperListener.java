package com.gikk.twirk.common.listeners;

import com.gikk.twirk.Twirk;
import com.gikk.twirk.events.TwirkListenerBaseImpl;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;

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
