package com.gikk.twirk.common.command.commands.faq;

import com.gikk.twirk.common.command.CommandBase;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;
import com.gikk.twirk.utils.JSONParser;
import com.gikk.twirk.utils.MessageSending;
import org.json.JSONException;
import org.json.JSONObject;

public class CommandCaster extends CommandBase
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
                json = new JSONObject(JSONParser.readUrl("https://api.twitch.tv/kraken/channels/" + args[1].toLowerCase()));
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
                String Status = null;
                try
                {
                    Status = json.get("status").toString();
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

                System.out.println(Status);

                MessageSending.sendNormalMessage("Go give http://twitch.tv/" + args[1] + "a follow, they last played " + Status + " (Playing " + game + ")");
            }
        }
    }
}
