package com.gikk.twirk.types.users;


import com.gikk.twirk.enums.USER_TYPE;

class TwitchUserImpl implements TwitchUser
{
    //***********************************************************
    // 				VARIABLES
    //***********************************************************
    private final String displayName;
    private final boolean isStaff;
    private final boolean isAdmin;
    private final boolean isGlobalMod;
    private final boolean isBroadcaster;
    private final boolean isMod;
    private final boolean isSub;
    private final boolean isTurbo;
    private final int color;
    private final int userID;
    private final USER_TYPE userType;
    private final String[] badges;

    //***********************************************************
    // 				CONSTRUCTOR
    //***********************************************************

    TwitchUserImpl(DefaultTwitchUserBuilder builder)
    {
        this.displayName = builder.displayName;
        this.isStaff = builder.isStaff;
        this.isAdmin = builder.isAdmin;
        this.isGlobalMod = builder.isGlobalMod;
        this.isBroadcaster = builder.isBroadcaster;
        this.isMod = builder.isMod;
        this.isSub = builder.isSub;
        this.isTurbo = builder.isTurbo;
        this.badges = builder.badges;
        this.userID = builder.userID;
        this.userType = builder.userType;
        this.color = builder.color;
    }

    //***********************************************************
    // 				PUBLIC
    //***********************************************************
    public String getName()
    {
        return displayName;
    }

    @Override
    public boolean isStaff()
    {
        return isStaff;
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
    public boolean isBroadcaster()
    {
        return isBroadcaster;
    }

    public boolean isMod()
    {
        return isMod;
    }

    public boolean isTurbo()
    {
        return isTurbo;
    }

    public boolean isSub()
    {
        return isSub;
    }

    public USER_TYPE getUserType()
    {
        return userType;
    }

    public int getColor()
    {
        return color;
    }

    public String[] getBadges()
    {
        return badges;
    }

    public int getUserID()
    {
        return userID;
    }
}
