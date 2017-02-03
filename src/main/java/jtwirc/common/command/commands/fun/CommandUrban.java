package jtwirc.common.command.commands.fun;

import jtwirc.common.command.CommandBase;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.JSONParser;
import jtwirc.utils.MessageSending;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CommandUrban extends CommandBase
{
    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        if (user.isMod() || user.isBroadcaster())
        {
            JSONObject json = null;
            try
            {
                json = new JSONObject(JSONParser.readUrl("http://api.urbandictionary.com/v0/define?term=" + args[1]));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            try
            {
                JSONArray jsonArray = json.getJSONArray("list");
                JSONObject test = jsonArray.getJSONObject(0);
                MessageSending.sendNormalMessage("Definition of " + args[1] + " :");
                MessageSending.sendNormalMessage("" + test.getString("definition").replaceAll("\n", ""));
            }
            catch (JSONException e)
            {
                MessageSending.sendNormalMessage("Definition not found, please try again.");
            }
        }
    }
}
