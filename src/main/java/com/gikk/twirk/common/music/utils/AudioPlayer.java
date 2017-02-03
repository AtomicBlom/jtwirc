package com.gikk.twirk.common.music.utils;

import net.beadsproject.beads.data.SampleManager;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.SamplePlayer;
import todo.ChirpBot;

import java.io.File;

public class AudioPlayer
{

    public void playAudioFile(File audio)
    {

        SamplePlayer player = new SamplePlayer(ChirpBot.audioContext, SampleManager.sample(audio.getPath()));
        System.out.println("[ChirpBot] Playing audio file " + audio.getPath());
        Gain g = new Gain(ChirpBot.audioContext, Integer.valueOf(ChirpBot.config.getProperty("inouts")), Float.valueOf(ChirpBot.config.getProperty("gain")));
        g.addInput(player);
        ChirpBot.audioContext.out.addInput(g);
        ChirpBot.audioContext.start();
        AudioTimerThread thread = new AudioTimerThread();
        thread.iloveyou(System.currentTimeMillis());
        thread.start();
    }
}
