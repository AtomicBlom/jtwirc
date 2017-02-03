package jtwirc.common.command.commands.faq;

import jtwirc.TwircBot;
import jtwirc.common.command.CommandBase;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.JSONParser;
import jtwirc.utils.MessageSending;
import org.json.JSONException;
import org.json.JSONObject;

public class CommandStatus extends CommandBase
{
    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        JSONObject json = null;
        try
        {
            json = new JSONObject(JSONParser.readUrl("https://api.twitch.tv/kraken/channels/" + TwircBot.config.getProperty("autoJoinChannel")));
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
