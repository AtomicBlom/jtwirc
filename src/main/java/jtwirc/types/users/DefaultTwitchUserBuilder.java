package jtwirc.types.users;

import jtwirc.types.AbstractTwitchUserFields;
import jtwirc.types.twitchMessage.TwitchMessage;

public class DefaultTwitchUserBuilder extends AbstractTwitchUserFields implements TwitchUserBuilder
{

    @Override
    public TwitchUser build(TwitchMessage message)
    {
        parseUserProperties(message);
        return new TwitchUserImpl(this);
    }
}
