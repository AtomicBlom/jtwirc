package com.gikk.twirk.common.command.commands.faq;

import com.gikk.twirk.common.command.CommandBase;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;
import com.gikk.twirk.utils.Defaults;
import com.gikk.twirk.utils.JSONParser;
import com.gikk.twirk.utils.MessageSending;
import com.gikk.twirk.utils.json.Load;
import org.json.JSONException;
import org.json.JSONObject;
import todo.ChirpBot;

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
            json = new JSONObject(JSONParser.readUrl("https://api.twitch.tv/kraken/channels/" + ChirpBot.config.getProperty("autoJoinChannel")));
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
            if (ChirpBot.steamList.containsKey(game))
            {
                Integer appid = ChirpBot.steamList.get(game);
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
