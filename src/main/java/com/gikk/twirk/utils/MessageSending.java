package com.gikk.twirk.utils;


import todo.ChirpBot;

public class MessageSending
{

    public static void sendNormalMessage(String message)
    {
        if (!ChirpBot.DEBUG)
        {
            ChirpBot.bots.get(ChirpBot.BOT_COMMANDS).channelMessage(message);
        }
    }

    public static void sendWhisper(String user, String message)
    {
        if (!ChirpBot.DEBUG)
        {
            ChirpBot.bots.get(ChirpBot.BOT_WHISPER).channelMessage("/w " + user + " " + message);
        }
    }

}
