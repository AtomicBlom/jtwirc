package jtwirc.common.threads;

import jtwirc.TwircBot;
import jtwirc.annotation.Unfinished;
import jtwirc.utils.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

@Unfinished(value = "We can probably remove this")
@SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "InfiniteLoopStatement"})
public class ModCommon
{
    private static ArrayList<String> moderators = new ArrayList<>();

    public static Thread updateMods = new Thread("ModCommon")
    {
        @Override
        public void run()
        {
            while (true)
            {
                try
                {
                    updateModerators();
                    sleep(300000);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    TwircBot.log.error(e.getMessage());
                }
            }
        }
    };

    public static void updateModerators() throws Exception
    {
        try
        {
            moderators.clear();
            JSONObject json = new JSONObject(JSONParser.readUrl("http://tmi.twitch.tv/group/user/" + TwircBot.config.getProperty("autoJoinChannel") + "/chatters"));
            for (int i = 0; i < json.length(); i++)
            {
                JSONArray mods = json.getJSONObject("chatters").getJSONArray("moderators");
                for (int j = 0; j < mods.length(); j++)
                {
                    moderators.add(mods.getString(j));
                    if (!TwircBot.permList.containsKey(mods.getString(j)))
                    {
                        TwircBot.permList.put(mods.getString(j).toLowerCase(), "mod");
                        TwircBot.saveAllTheThings();
                    }
                }
            }
        }
        catch (Exception e)
        {
            TwircBot.log.error("Can't reach Twitch.");
            TwircBot.log.error(e.getMessage());
        }
    }
}
