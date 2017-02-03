package com.gikk.twirk.commands;

import com.gikk.twirk.Twirk;
import jtwirc.enums.USER_TYPE;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;

import java.util.Calendar;
import java.util.Date;

public class PrefixCommandExample extends CommandExampleBase
{
    private final static String patternA = "!timezone";
    private final static String patternB = "!time";

    private final Twirk twirk;

    public PrefixCommandExample(Twirk twirk)
    {
        super(CommandType.PREFIX_COMMAND);
        this.twirk = twirk;
    }

    @Override
    protected String getCommandWords()
    {
        return patternA + "|" + patternB;
    }

    @Override
    protected USER_TYPE getMinUserPrevilidge()
    {
        return USER_TYPE.DEFAULT;
    }

    @Override
    protected void performCommand(String command, TwitchUser sender, TwitchMessage message)
    {
        if (command.equals(patternA))
        {
            twirk.channelMessage(sender.getName() + ": Local time zone is " + Calendar.getInstance().getTimeZone().getDisplayName());
        }
        else if (command.equals(patternB))
        {
            twirk.channelMessage(sender.getName() + ": Local time is " + new Date().toString());
        }

    }

}
