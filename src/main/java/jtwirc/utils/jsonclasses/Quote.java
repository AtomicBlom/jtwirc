package jtwirc.utils.jsonclasses;

import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.LinkedHashMap;

public class Quote implements JSONStreamAware
{

    private Long number;
    private String quote;

    public Quote(Long number, String quote)
    {
        this.number = number;
        this.quote = quote;
    }

    @Override
    public void writeJSONString(Writer writer) throws IOException
    {
        LinkedHashMap<String, Serializable> obj = new LinkedHashMap<>();
        obj.put("number", number);
        obj.put("quote", quote);
        JSONValue.writeJSONString(obj, writer);
    }
}