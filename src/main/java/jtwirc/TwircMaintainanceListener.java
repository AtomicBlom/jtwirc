package jtwirc;

import jtwirc.events.TwircListenerBaseImpl;
import jtwirc.types.mode.ModeEvent;

/**
 * Class for taking care of basic tasks that our bot should do. However, writing all
 * of these methods directly in the {@link Twirc} class would get messy. Instead, these simple
 * methods are moved to this separate class.
 */
class TwircMaintainanceListener extends TwircListenerBaseImpl
{
    private final Twirc instance;

    TwircMaintainanceListener(Twirc Twirc)
    {
        this.instance = Twirc;
    }

    @Override
    public void onAnything(String line)
    {
        if (instance.verboseMode)
        {
            System.out.println("IN  " + line);
        }
    }

    @Override
    public void onJoin(String joinedNick)
    {
        if (!instance.online.add(joinedNick))
        {
            System.out.println("\tUser " + joinedNick + " was already listed as online....");
        }
    }

    @Override
    public void onPart(String partedNick)
    {
        if (!instance.online.remove(partedNick))
        {
            System.out.println("\tUser " + partedNick + " was not listed as online....");
        }
    }

    @Override
    public void onMode(ModeEvent mode)
    {
        if (mode.getEvent() == ModeEvent.MODE_EVENT.GAINED_MOD)
        {
            instance.moderators.add(mode.getUser());
        }
        else
        {
            instance.moderators.remove(mode.getUser());
        }
    }

    @Override
    public void onDisconnect()
    {
        instance.online.clear();
        instance.moderators.clear();
    }

}
