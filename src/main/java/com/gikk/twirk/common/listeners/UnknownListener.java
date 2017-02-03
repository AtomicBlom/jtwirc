package com.gikk.twirk.common.listeners;

import com.gikk.twirk.Twirk;
import com.gikk.twirk.events.TwirkListenerBaseImpl;

public class UnknownListener extends TwirkListenerBaseImpl
{

    private Twirk.BotType type;

    public UnknownListener(Twirk.BotType type)
    {
        this.type = type;
    }

    @Override
    public void onUnknown(String line)
    {
        super.onUnknown(line);
    }
}
