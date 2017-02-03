package jtwirc.common.command.commands.partner;

import jtwirc.common.command.CommandBase;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.Defaults;
import jtwirc.utils.json.Save;

public class CommandReSub extends CommandBase
{

    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        if (user.isBroadcaster())
        {
            Defaults.oldSub = args[1];
            Save.subMessages();
        }
    }
}
