package jtwirc.common.command.commands.util;

import jtwirc.common.command.CommandBase;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.Defaults;
import jtwirc.utils.JSONParser;
import jtwirc.utils.MessageSending;
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
