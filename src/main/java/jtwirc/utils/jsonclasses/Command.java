package jtwirc.utils.jsonclasses;

import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;

public class Command implements JSONStreamAware
{

    private String command;
    private String response;

    public Command(String command, String response)
    {
        this.command = command;
        this.response = response;
    }

    @Override
    public void writeJSONString(Writer writer) throws IOException
    {
        LinkedHashMap<String, String> obj = new LinkedHashMap<>();
        obj.put("command", command);
        obj.put("response", response);
        JSONValue.writeJSONString(obj, writer);
    }
}
