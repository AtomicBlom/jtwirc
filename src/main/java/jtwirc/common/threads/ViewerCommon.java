package jtwirc.common.threads;

import jtwirc.TwircBot;
import jtwirc.utils.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewerCommon
{
    public static ArrayList<String> viewers = new ArrayList<>();

    public static Thread updateViewers = new Thread("ModCommon")
    {
        @Override
        public void run()
        {
            //noinspection InfiniteLoopStatement
            while (true)
            {
                try
                {
                    updateViewers();
                    sleep(180000);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    TwircBot.log.error(e.getMessage());
                }
            }
        }
    };

    public static void updateViewers()
    {
        try
        {
            JSONObject json = new JSONObject(JSONParser.readUrl("http://tmi.twitch.tv/group/user/" + TwircBot.config.getProperty("autoJoinChannel") + "/chatters"));
            JSONArray view = json.getJSONObject("chatters").getJSONArray("viewers");
            viewers.clear();
            for (int j = 0; j < view.length(); j++)
            {
                viewers.add(view.getString(j));
            }
            JSONArray mods = json.getJSONObject("chatters").getJSONArray("moderators");
            for (int j = 0; j < mods.length(); j++)
            {
                viewers.add(mods.getString(j));
            }
        }
        catch (Exception e)
        {
            TwircBot.log.error("Can't reach Twitch.");
            TwircBot.log.error(e.getMessage());
        }
    }
}
