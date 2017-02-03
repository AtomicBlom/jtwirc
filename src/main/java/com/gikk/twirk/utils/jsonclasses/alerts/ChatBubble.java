package com.gikk.twirk.utils.jsonclasses.alerts;

import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.LinkedHashMap;

public class ChatBubble implements JSONStreamAware
{
    private Long timestamp;
    private String channelName;
    private String rajHermy;
    private String message;
    private int isChatBubble; //boolean
    private int isTrollSound; //boolean

    public ChatBubble(Long timestamp, String channelName, String rajHermy, String message, int isChatBubble, int isTrollSound)
    {
        this.timestamp = timestamp;
        this.channelName = channelName;
        this.rajHermy = rajHermy;
        this.message = message;
        this.isChatBubble = isChatBubble;
        this.isTrollSound = isTrollSound;
    }


    @Override
    public void writeJSONString(Writer writer) throws IOException
    {
        LinkedHashMap<String, Serializable> obj = new LinkedHashMap<>();
        obj.put("timestamp", timestamp);
        obj.put("cbname", channelName);
        obj.put("cbwho", rajHermy);
        obj.put("cbmessage", message);
        obj.put("cbtrue", isChatBubble);
        obj.put("cbtrollsound", isTrollSound);
        JSONValue.writeJSONString(obj, writer);
    }
}
