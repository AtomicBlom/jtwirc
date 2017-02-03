package com.gikk.twirk.common.command.commands.util;

import com.gikk.twirk.Twirk;
import com.gikk.twirk.common.command.CommandBase;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;
import com.gikk.twirk.utils.MessageSending;
import todo.ChirpBot;

public class CommandExit extends CommandBase
{
    private static void exit()
    {
        new Thread("App-exit")
        {
            @Override
            public void run()
            {
                System.exit(0);
            }
        }.start();
    }

    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        if (user.isMod() || user.isBroadcaster())
        {
            MessageSending.sendNormalMessage("Shutting down the bot.");
            System.out.println("Shutting down.");
            ChirpBot.bots.forEach(Twirk::close);
            System.out.println("Shut down!");
            exit();
        }
    }
}
