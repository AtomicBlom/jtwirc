package jtwirc.common.music.utils;

import jtwirc.TwircBot;
import net.beadsproject.beads.data.SampleManager;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.SamplePlayer;

import java.io.File;

public class AudioPlayer
{

    public void playAudioFile(File audio)
    {

        SamplePlayer player = new SamplePlayer(TwircBot.audioContext, SampleManager.sample(audio.getPath()));
        System.out.println("[TwircBot] Playing audio file " + audio.getPath());
        Gain g = new Gain(TwircBot.audioContext, Integer.valueOf(TwircBot.config.getProperty("inouts")), Float.valueOf(TwircBot.config.getProperty("gain")));
        g.addInput(player);
        TwircBot.audioContext.out.addInput(g);
        TwircBot.audioContext.start();
        AudioTimerThread thread = new AudioTimerThread();
        thread.iloveyou(System.currentTimeMillis());
        thread.start();
    }
}
