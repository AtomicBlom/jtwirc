package jtwirc.common.listeners;

import jtwirc.Twirk;
import jtwirc.events.TwirkListenerBaseImpl;

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
