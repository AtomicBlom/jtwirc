package com.gikk.twirk.common.command.commands.util;

import com.gikk.twirk.common.command.CommandBase;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;
import com.gikk.twirk.utils.MessageSending;
import todo.ChirpBot;

import java.util.Timer;
import java.util.TimerTask;

public class CommandTimer extends CommandBase
{


    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        if (user.isMod() || user.isBroadcaster())
        {
            if (args.length != 4)
            {
                MessageSending.sendNormalMessage("Wrong Syntax user : !timer &lt;command1&gt; &lt;time in minutes&gt; &lt;command2&gt;");
            }
            if (args.length == 4)
            {
                if (ChirpBot.commandList.containsKey(args[1]) && ChirpBot.commandList.containsKey(args[3]))
                {
                    MessageSending.sendNormalMessage(ChirpBot.commandList.get(args[1]));
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask()
                    {
                        @Override
                        public void run()
                        {
                            MessageSending.sendNormalMessage(ChirpBot.commandList.get(args[3]));
                        }
                    }, Integer.parseInt(args[2]) * 60 * 1000);

                }
            }
        }
    }
}