package com.gikk.twirk.common.command.commands.faq;

import com.gikk.twirk.common.command.CommandBase;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;
import com.gikk.twirk.utils.JSONParser;
import com.gikk.twirk.utils.MessageSending;
import org.json.JSONException;
import org.json.JSONObject;
import todo.ChirpBot;

public class CommandStatus extends CommandBase
{
    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        JSONObject json = null;
        try
        {
            json = new JSONObject(JSONParser.readUrl("https://api.twitch.tv/kraken/channels/" + ChirpBot.config.getProperty("autoJoinChannel")));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            MessageSending.sendNormalMessage("playing " + json.get("game") + ": " + json.get("status"));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
