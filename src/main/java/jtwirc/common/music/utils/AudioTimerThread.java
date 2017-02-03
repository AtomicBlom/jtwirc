package jtwirc.common.music.utils;

import jtwirc.TwircBot;

import java.util.concurrent.TimeUnit;

public class AudioTimerThread extends Thread
{

    private long time;


    @Override
    public void run()
    {
        if (TimeUnit.SECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS) >= 7)
        {
            TwircBot.audioContext.reset();
            stop();
        }
    }

    public void iloveyou(long time)
    {
        this.time = time;
    }
}
