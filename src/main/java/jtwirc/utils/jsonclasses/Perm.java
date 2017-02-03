package jtwirc.utils.jsonclasses;

import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;

public class Perm implements JSONStreamAware
{

    private String name;
    private String permission;

    public Perm(String name, String permission)
    {
        this.name = name;
        this.permission = permission;
    }

    @Override
    public void writeJSONString(Writer writer) throws IOException
    {
        LinkedHashMap<String, String> obj = new LinkedHashMap<>();
        obj.put("name", name);
        obj.put("permission", permission);
        JSONValue.writeJSONString(obj, writer);
    }
}
