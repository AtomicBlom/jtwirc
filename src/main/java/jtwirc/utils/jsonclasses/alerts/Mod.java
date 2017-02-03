package jtwirc.utils.jsonclasses.alerts;

import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.LinkedHashMap;

public class Mod implements JSONStreamAware
{
    private Long timestamp;
    private String mod;

    public Mod(Long timestamp, String mod)
    {
        this.timestamp = timestamp;
        this.mod = mod;
    }

    @Override
    public void writeJSONString(Writer writer) throws IOException
    {
        LinkedHashMap<String, Serializable> obj = new LinkedHashMap<>();
        obj.put("timestamp", timestamp);
        obj.put("modname", mod);
        JSONValue.writeJSONString(obj, writer);
    }
}
