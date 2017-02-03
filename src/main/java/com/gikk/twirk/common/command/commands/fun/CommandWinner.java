package com.gikk.twirk.common.command.commands.fun;

import com.gikk.twirk.common.command.CommandBase;
import com.gikk.twirk.common.threads.ViewerCommon;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;
import com.gikk.twirk.utils.Defaults;
import com.gikk.twirk.utils.JSONParser;
import com.gikk.twirk.utils.MessageSending;
import org.json.JSONException;
import org.json.JSONObject;
import todo.ChirpBot;

import java.util.Random;

public class CommandWinner extends CommandBase
{

    private Random rand = new Random();

    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        if (user.isMod() || user.isBroadcaster())
        {
            if (args.length <= 1)
            {
                MessageSending.sendNormalMessage("And the winner is... " + getWinner(false) + "!!!! Congratulations!!!!");
            }
            if (args.length == 2)
            {
                if (args[1].toLowerCase().equals("-f"))
                {
                    MessageSending.sendNormalMessage("And the winner is... " + getWinner(true) + "!!!! Congratulations!!!!");
                }
            }
        }
    }

    private String getWinner(boolean follows)
    {
        String winner = "";
        int win;
        ViewerCommon.updateViewers();
        if (!follows)
        {
            boolean breaker = true;
            while (breaker)
            {
                win = rand.nextInt(ViewerCommon.viewers.size());
                winner = ViewerCommon.viewers.get(win);
                breaker = winner.equals(ChirpBot.config.getProperty("autoJoinChannel").toLowerCase()) || winner.equals(Defaults.getBotName().toLowerCase());
            }
            return winner;
        }
        else
        {
            boolean breaker = true;
            while (breaker)
            {
                win = rand.nextInt(ViewerCommon.viewers.size());
                winner = ViewerCommon.viewers.get(win);
                if (winner.equals(ChirpBot.config.getProperty("autoJoinChannel").toLowerCase()) || winner.equals(Defaults.getBotName().toLowerCase()))
                {
                    breaker = true;
                }
                else
                {
                    JSONObject json = null;
                    try
                    {
                        json = new JSONObject(JSONParser.readUrl("https://api.twitch.tv/kraken/users/" + winner + "/follows/channels/" + ChirpBot.config.getProperty("autoJoinChannel")));
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    try
                    {
                        if (json != null && json.get("status").toString().contains("404"))
                        {
                            breaker = false;
                        }
                    }
                    catch (JSONException e)
                    {
                        return winner;
                    }
                }

            }
            return winner;
        }
    }
}
