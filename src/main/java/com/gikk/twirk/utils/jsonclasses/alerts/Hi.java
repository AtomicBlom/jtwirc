package com.gikk.twirk.utils.jsonclasses.alerts;

import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.LinkedHashMap;

public class Hi implements JSONStreamAware
{

    private Long timestamp;
    private String channelName;

    public Hi(Long timestamp, String channelName)
    {
        this.timestamp = timestamp;
        this.channelName = channelName;
    }

    @Override
    public void writeJSONString(Writer writer) throws IOException
    {
        LinkedHashMap<String, Serializable> obj = new LinkedHashMap<>();
        obj.put("timestamp", timestamp);
        obj.put("channel", channelName);
        JSONValue.writeJSONString(obj, writer);
    }
}
