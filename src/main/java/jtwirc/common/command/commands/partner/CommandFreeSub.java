package jtwirc.common.command.commands.partner;

import jtwirc.common.command.CommandBase;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.Defaults;
import jtwirc.utils.json.Save;

public class CommandFreeSub extends CommandBase
{
    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        if (user.isBroadcaster())
        {
            Defaults.newSub = args[1];
            Save.subMessages();
        }
    }
}
