package jtwirc.types.globaluserstate;

import jtwirc.enums.USER_TYPE;
import jtwirc.types.emote.Emote;

import java.util.List;

class GlobalUserStateImpl implements GlobalUserStateEvent
{

    private final String rawLine;
    private final String displayName;
    private final int color;
    private final int userID;
    private final USER_TYPE user_type;
    private final List<Emote> emotes;

    GlobalUserStateImpl(DefaultGlobalStateBuilder builder)
    {
        this.rawLine = builder.rawLine;
        this.displayName = builder.displayName;
        this.color = builder.color;
        this.userID = builder.userID;
        this.user_type = builder.user_type;
        this.emotes = builder.emotes;
    }

    @Override
    public String getRaw()
    {
        return rawLine;
    }

    @Override
    public int getColor()
    {
        return color;
    }

    @Override
    public String getDisplayName()
    {
        return displayName;
    }

    @Override
    public int getUserID()
    {
        return userID;
    }

    @Override
    public USER_TYPE getUserType()
    {
        return user_type;
    }

    @Override
    public List<Emote> getEmoteSets()
    {
        return emotes;
    }
}
