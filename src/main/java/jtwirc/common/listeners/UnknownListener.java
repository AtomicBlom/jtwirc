package jtwirc.common.listeners;

import jtwirc.Twirc;
import jtwirc.events.TwircListenerBaseImpl;

public class UnknownListener extends TwircListenerBaseImpl
{

    private Twirc.BotType type;

    public UnknownListener(Twirc.BotType type)
    {
        this.type = type;
    }

    @Override
    public void onUnknown(String line)
    {
        super.onUnknown(line);
    }
}
