package jtwirc.types.action;

import jtwirc.enums.USER_TYPE;
import jtwirc.types.emote.Emote;

import java.util.List;

class ActionImpl implements ActionEvent
{

    private final String rawLine;
    private final String displayName;
    private final boolean isMod;
    private final boolean isSub;
    private final boolean isTurbo;
    private final boolean isStaff;
    private final boolean isBroadcaster;
    private final boolean isGlobalMod;
    private final boolean isAdmin;
    private final int color;
    private final int userID;
    private final USER_TYPE userType;
    private final String[] badges;
    private final String loginName;
    private final List<Emote> emotes;
    private final boolean hasEmotes;

    ActionImpl(DefaultActionBuilder builder)
    {
        this.rawLine = builder.rawLine;
        this.displayName = builder.displayName;
        this.isMod = builder.isMod;
        this.isSub = builder.isSub;
        this.isTurbo = builder.isTurbo;
        this.isStaff = builder.isStaff;
        this.isBroadcaster = builder.isBroadcaster;
        this.isGlobalMod = builder.isGlobalMod;
        this.isAdmin = builder.isAdmin;
        this.color = builder.color;
        this.userID = builder.userID;
        this.userType = builder.userType;
        this.badges = builder.badges;
        this.loginName = builder.loginName;
        this.emotes = builder.emotes;
        this.hasEmotes = builder.hasEmotes;
    }

    @Override
    public String getRaw()
    {
        return rawLine;
    }

    @Override
    public String getName()
    {
        return displayName;
    }

    @Override
    public boolean isMod()
    {
        return isMod;
    }

    @Override
    public boolean isTurbo()
    {
        return isTurbo;
    }

    @Override
    public boolean isSub()
    {
        return isSub;
    }

    @Override
    public boolean isBroadcaster()
    {
        return isBroadcaster;
    }

    @Override
    public boolean isAdmin()
    {
        return isAdmin;
    }

    @Override
    public boolean isGlobalMod()
    {
        return isGlobalMod;
    }

    @Override
    public boolean isStaff()
    {
        return isStaff;
    }

    @Override
    public USER_TYPE getUserType()
    {
        return userType;
    }

    @Override
    public int getColor()
    {
        return color;
    }

    @Override
    public String[] getBadges()
    {
        return badges;
    }

    @Override
    public int getUserID()
    {
        return userID;
    }

    @Override
    public String getLogin()
    {
        return loginName;
    }

    @Override
    public boolean hasEmotes()
    {
        return hasEmotes;
    }

    @Override
    public List<Emote> getEmotes()
    {
        return emotes;
    }
}
