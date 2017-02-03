package jtwirc.types.users;

import jtwirc.types.AbstractTwitchUserFields;
import jtwirc.types.twitchMessage.TwitchMessage;

public class DefaultUserstateBuilder extends AbstractTwitchUserFields implements UserstateBuilder
{

    @Override
    public UserStateEvent build(TwitchMessage message)
    {
        parseUserProperties(message);
        return new UserstateImpl(this);
    }
}
