package jtwirc.utils;


import jtwirc.TwircBot;

public class MessageSending
{

    public static void sendNormalMessage(String message)
    {
        if (!TwircBot.DEBUG)
        {
            TwircBot.bots.get(TwircBot.BOT_COMMANDS).channelMessage(message);
        }
    }

    public static void sendWhisper(String user, String message)
    {
        if (!TwircBot.DEBUG)
        {
            TwircBot.bots.get(TwircBot.BOT_WHISPER).channelMessage("/w " + user + " " + message);
        }
    }

}
