package jtwirc.common.command.commands.faq;

import jtwirc.TwircBot;
import jtwirc.common.command.CommandBase;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.Defaults;
import jtwirc.utils.JSONParser;
import jtwirc.utils.MessageSending;
import jtwirc.utils.json.Load;
import org.json.JSONException;
import org.json.JSONObject;

public class CommandGame extends CommandBase
{
    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        Load.steamList();
        JSONObject json = null;
        try
        {
            json = new JSONObject(JSONParser.readUrl("https://api.twitch.tv/kraken/channels/" + TwircBot.config.getProperty("autoJoinChannel")));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if (json != null)
        {
            String game = null;
            try
            {
                game = json.get("game").toString();
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            if (TwircBot.steamList.containsKey(game))
            {
                Integer appid = TwircBot.steamList.get(game);
                String url = "http://store.steampowered.com/app/" + appid + "/";
                if (Defaults.whisperToggle)
                {
                    MessageSending.sendWhisper(user.getName().toLowerCase(), "I'm currently playing " + game + ". You can find more info about it right here : " + url);
                }
                else
                {
                    MessageSending.sendNormalMessage("I'm currently playing " + game + ". You can find more info about it right here : " + url);
                }
            }
            else
            {
                if (Defaults.whisperToggle)
                {
                    MessageSending.sendWhisper(user.getName().toLowerCase(), "I'm currently playing " + game + ".");
                }
                else
                {
                    MessageSending.sendNormalMessage("I'm currently playing " + game + ".");
                }
            }
        }
    }
}
