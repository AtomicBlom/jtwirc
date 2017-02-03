package com.gikk.twirk.types.globaluserstate;

import com.gikk.twirk.enums.USER_TYPE;
import com.gikk.twirk.types.AbstractTwitchUserFields;
import com.gikk.twirk.types.emote.Emote;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.utils.ParsingUtil;

import java.util.List;

import static com.gikk.twirk.enums.USER_TYPE.*;

@SuppressWarnings("WeakerAccess")
public class DefaultGlobalStateBuilder extends AbstractTwitchUserFields implements GlobalUserStateBuilder
{
    private final static String COLOR_IDENTIFIER = "color=";
    private final static String NAME_IDENTIFIER = "display-name=";
    private final static String USER_ID_IDENTIFIER = "user-id=";
    private final static String USER_TYPE_IDENTIFIER = "user-type=";

    int color;
    String loginName;
    List<Emote> emotes;
    boolean hasEmotes;
    int userID;
    USER_TYPE user_type;


    @Override
    public GlobalUserStateEvent build(TwitchMessage message)
    {
        parseUserProperties(message);

        String tag = message.getTag();
        this.hasEmotes = message.hasEmotes();
        this.emotes = message.getEmotes();
        this.color = Integer.valueOf(ParsingUtil.parseString(COLOR_IDENTIFIER, tag));
        this.loginName = ParsingUtil.parseString(NAME_IDENTIFIER, tag);
        this.userID = Integer.valueOf(ParsingUtil.parseString(USER_ID_IDENTIFIER, tag));
        switch (ParsingUtil.parseString(USER_TYPE_IDENTIFIER, tag))
        {
            case "admin":
                user_type = ADMIN;
            case "staff":
                user_type = STAFF;
            case "mod":
                user_type = MOD;
            case "global_mod":
                user_type = GLOBAL_MOD;
            case "broadcaster":
                user_type = OWNER;
            default:
                user_type = DEFAULT;
        }
        return new GlobalUserStateImpl(this);
    }
}
