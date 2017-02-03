package com.gikk.twirk.utils.jsonclasses;

import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;

public class Raffle implements JSONStreamAware
{
    private Long raffleID;
    private String user;

    public Raffle(Long raffleID, String user)
    {
        this.raffleID = raffleID;
        this.user = user;
    }

    @Override
    public void writeJSONString(Writer writer) throws IOException
    {
        LinkedHashMap<String, java.io.Serializable> obj = new LinkedHashMap<>();
        obj.put("id", raffleID);
        obj.put("user", user);
        JSONValue.writeJSONString(obj, writer);
    }
}
