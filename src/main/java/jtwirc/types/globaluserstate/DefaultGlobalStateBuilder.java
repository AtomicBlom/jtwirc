package jtwirc.types.globaluserstate;

import jtwirc.enums.USER_TYPE;
import jtwirc.types.AbstractTwitchUserFields;
import jtwirc.types.emote.Emote;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.utils.ParsingUtil;

import java.util.List;

import static jtwirc.enums.USER_TYPE.*;

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
