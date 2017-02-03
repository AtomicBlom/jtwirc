package jtwirc.common.command.commands.util;

import jtwirc.TwircBot;
import jtwirc.common.command.CommandBase;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.MessageSending;

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
                if (TwircBot.commandList.containsKey(args[1]) && TwircBot.commandList.containsKey(args[3]))
                {
                    MessageSending.sendNormalMessage(TwircBot.commandList.get(args[1]));
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask()
                    {
                        @Override
                        public void run()
                        {
                            MessageSending.sendNormalMessage(TwircBot.commandList.get(args[3]));
                        }
                    }, Integer.parseInt(args[2]) * 60 * 1000);

                }
            }
        }
    }
}