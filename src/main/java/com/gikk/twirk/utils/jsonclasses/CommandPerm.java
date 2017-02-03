package com.gikk.twirk.utils.jsonclasses;

import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;

public class CommandPerm implements JSONStreamAware
{

    private String command;
    private String permission;

    public CommandPerm(String command, String permission)
    {
        this.command = command;
        this.permission = permission;
    }

    @Override
    public void writeJSONString(Writer writer) throws IOException
    {
        LinkedHashMap<String, String> obj = new LinkedHashMap<>();
        obj.put("command", command);
        obj.put("permission", permission);
        JSONValue.writeJSONString(obj, writer);
    }
}
