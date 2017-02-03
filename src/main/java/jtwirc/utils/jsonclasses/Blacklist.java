package jtwirc.utils.jsonclasses;

import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;

public class Blacklist implements JSONStreamAware
{

    private String user;

    public Blacklist(String s)
    {
        this.user = s;
    }

    @Override
    public void writeJSONString(Writer writer) throws IOException
    {
        LinkedHashMap<String, String> obj = new LinkedHashMap<>();
        obj.put("user", user);
        JSONValue.writeJSONString(obj, writer);
    }
}
