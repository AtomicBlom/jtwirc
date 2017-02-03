package com.gikk.twirk.types.users;

import com.gikk.twirk.types.AbstractTwitchUserFields;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;

public class DefaultUserstateBuilder extends AbstractTwitchUserFields implements UserstateBuilder
{

    @Override
    public UserStateEvent build(TwitchMessage message)
    {
        parseUserProperties(message);
        return new UserstateImpl(this);
    }
}
