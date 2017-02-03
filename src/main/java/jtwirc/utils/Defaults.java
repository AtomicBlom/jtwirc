package jtwirc.utils;

import com.rosaloves.bitlyj.Url;
import jtwirc.TwircBot;

import static com.rosaloves.bitlyj.Bitly.as;
import static com.rosaloves.bitlyj.Bitly.shorten;

public class Defaults
{

    //TwircBot Stuff
	public static final String VERSION = "3.0";
    public static final String CHANGELOG = "N/A";

    //Toggles
    public static boolean debugToggled = false;
    public static boolean toggleStream = false;
    public static boolean isVip = true;

    public static boolean linkPurge = Boolean.getBoolean(TwircBot.extra.getProperty("linkToggle"));
    public static boolean capsPurge = Boolean.getBoolean(TwircBot.extra.getProperty("capsToggle"));
    public static boolean wotPurge = Boolean.getBoolean(TwircBot.extra.getProperty("wotToggle"));
    public static boolean schedule = Boolean.getBoolean(TwircBot.extra.getProperty("scheduleToggle"));
    public static boolean whisperToggle = Boolean.getBoolean(TwircBot.extra.getProperty("whisperToggle"));

    //Default values
    public static int lastStrawpoll = 0;
    public static int raffleCost = 100;
    public static int time = 5;
    public static Long totalPoints = (long) 0;

    //Sub messages
    public static String newSub = "/me Welcome to the WeAllSubCrew #user!";
    public static String freeSub = "/me Welcome to the WeAllSubCrew #user!";
    public static String oldSub = "/me Welcome back #user and thanks for #months months of support!";

    public static String cttText = TwircBot.extra.getProperty("ctt");
    public static boolean songRequestBoolean = true;
    private static int songRequestNumber = 1;

    public static void incSongRequestNumber()
    {
        songRequestNumber++;
    }

    public static int getSongRequestNumber()
    {
        return songRequestNumber;
    }

    public static String getBitlyLink()
    {
        String bitlyAPI = "R_6390cf5b180d4f419c7360c6578165cf"; //BitlyAPI here
        String bitlyUser = "o_stavlek28";
        Url url = as(bitlyUser, bitlyAPI).call(shorten("https://twitter.com/intent/tweet?text=" + cttText + "&source=o_stavlek28"));
        return url.getShortUrl();
    }

    public static int getRaffleCost()
    {
        return raffleCost;
    }

    public static void setRaffleCost(int set)
    {
        raffleCost = set;
    }

    public static String getPointName()
    {
        if (isVip)
        {
            return TwircBot.config.getProperty("points");
        }
        else
        {
            return "points";
        }
    }

    public static String getBotName()
    {
        if (isVip)
        {
            return TwircBot.config.getProperty("nick");
        }
        else
        {
            return "TwircBot";
        }
    }

    public static String getOAuth()
    {
        if (isVip)
        {
            return TwircBot.config.getProperty("oauth");
        }
        else
        {
            return "oauth:0i1ffuaghkxikm5aq6oupped4u0l8n";
        }
    }

    public static String getStreamer()
    {
        if (TwircBot.config.getProperty("autoJoinChannel") == null)
        {
            try
            {
                throw new Exception("Username can't be empty");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            return TwircBot.config.getProperty("autoJoinChannel").toLowerCase();
        }
        return TwircBot.config.getProperty("autoJoinChannel").toLowerCase();
    }

    public static void setLastStrawpoll(int i)
    {
        lastStrawpoll = i;
    }

    public static String fuseArray(String[] array, int start)
    {
        String fused = "";
		for (int c = start; c < array.length; c++)
		{
			fused += array[c] + " ";
		}

        return fused.trim();

    }

    public static String getServer()
    {
        return "irc.chat.twitch.tv";
    }
}
