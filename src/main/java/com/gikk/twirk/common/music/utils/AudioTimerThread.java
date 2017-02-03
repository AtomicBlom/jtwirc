package com.gikk.twirk.common.music.utils;

import todo.ChirpBot;

import java.util.concurrent.TimeUnit;

public class AudioTimerThread extends Thread
{

    private long time;


    @Override
    public void run()
    {
        if (TimeUnit.SECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS) >= 7)
        {
            ChirpBot.audioContext.reset();
            stop();
        }
    }

    public void iloveyou(long time)
    {
        this.time = time;
    }
}
