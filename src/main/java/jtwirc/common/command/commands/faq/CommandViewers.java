package jtwirc.common.command.commands.faq;

import jtwirc.common.command.CommandBase;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.Defaults;
import jtwirc.utils.JSONParser;
import jtwirc.utils.MessageSending;
import org.json.JSONArray;
import org.json.JSONObject;

public class CommandViewers extends CommandBase
{

    public CommandViewers()
    {
        super();
    }

    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);

        try
        {
            JSONObject json = new JSONObject(JSONParser.readUrl("https://api.twitch.tv/kraken/streams?channel=" + Defaults.getStreamer()));
            JSONArray array = json.getJSONArray("streams");
            String viewers = String.valueOf(array.getJSONObject(0).get("viewers"));
            if (Defaults.whisperToggle)
            {
                MessageSending.sendWhisper(user.getName().toLowerCase(), viewers + " people are currently watching!");
            }
            else
            {
                MessageSending.sendNormalMessage(viewers + " people are currently watching!");
            }
        }
        catch (Exception e)
        {
            MessageSending.sendNormalMessage("This stream is currently offline.");
        }
    }
}
