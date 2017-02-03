package com.gikk.twirk.common.command.commands.util;

import com.gikk.twirk.common.command.CommandBase;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;
import com.gikk.twirk.utils.Defaults;
import com.gikk.twirk.utils.JSONParser;
import com.gikk.twirk.utils.MessageSending;
import org.json.JSONException;
import org.json.JSONObject;

public class CommandChatters extends CommandBase
{

    public CommandChatters()
    {
        super();
    }

    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        JSONObject json = null;
        try
        {
            json = new JSONObject(JSONParser.readUrl("http://tmi.twitch.tv/group/user/" + Defaults.getStreamer() + "/chatters"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if (Defaults.whisperToggle)
        {
            try
            {
                MessageSending.sendWhisper(user.getName().toLowerCase(), json.get("chatter_count") + " people are currently chatting!");
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            try
            {
                MessageSending.sendNormalMessage(json.get("chatter_count") + " people are currently chatting!");
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }
}
