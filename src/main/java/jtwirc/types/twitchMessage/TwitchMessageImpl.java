package jtwirc.types.twitchMessage;

import jtwirc.types.emote.Emote;

import java.util.LinkedList;
import java.util.List;

class TwitchMessageImpl implements TwitchMessage
{
    //***********************************************************
    // 				VARIABLES
    //***********************************************************
    private final String line, tag, prefix, command, target, content;
    private final boolean containsBits;
    private final int totalBits, bits;
    private final LinkedList<Emote> emotes;

    //***********************************************************
    // 				CONSTRUCTOR
    //***********************************************************
    TwitchMessageImpl(DefaultTwitchMessageBuilder builder)
    {
        this.line = builder.line;
        this.tag = builder.tag;
        this.prefix = builder.prefix;
        this.command = builder.command;
        this.target = builder.target;
        this.content = builder.content;
        this.emotes = builder.emotes;
        this.containsBits = builder.containsBits;
        this.totalBits = builder.totalBits;
        this.bits = builder.bits;
    }

    //***********************************************************
    // 				PUBLIC
    //***********************************************************
    public String getRaw()
    {
        return line;
    }

    public String getTag()
    {
        return tag;
    }

    public String getPrefix()
    {
        return prefix;
    }

    public String getCommand()
    {
        return command;
    }

    public String getTarget()
    {
        return target;
    }

    public String getContent()
    {
        return content;
    }

    public boolean hasEmotes()
    {
        return emotes.size() != 0;
    }

    public List<Emote> getEmotes()
    {
        return emotes;
    }

    public String toString()
    {
        return line;
    }

    public boolean containBits()
    {
        return containsBits;
    }

    public int getTotalBits()
    {
        return totalBits;
    }

    public int getBitsThisCheer()
    {
        return bits;
    }
}
