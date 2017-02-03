package com.gikk.twirk.utils.jsonclasses;

import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;

public class Note implements JSONStreamAware
{

    private Long number;
    private String note;

    public Note(Long number, String note)
    {
        this.number = number;
        this.note = note;
    }

    @Override
    public void writeJSONString(Writer writer) throws IOException
    {
        LinkedHashMap<String, java.io.Serializable> obj = new LinkedHashMap<>();
        obj.put("number", number);
        obj.put("note", note);
        JSONValue.writeJSONString(obj, writer);
    }
}