package jtwirc.utils.json;

import jtwirc.TwircBot;
import jtwirc.utils.Defaults;
import jtwirc.utils.jsonclasses.*;
import jtwirc.utils.jsonclasses.alerts.Alert;
import jtwirc.utils.jsonclasses.alerts.ChatBubble;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "ResultOfMethodCallIgnored"})
public class Save
{

    public static void alerts() throws IOException
    {
        JSONObject object = new JSONObject();
        JSONArray list = new JSONArray();
        Set<Map.Entry<Long, String>> mapSet = TwircBot.shoutoutList.entrySet();
        for (Map.Entry<Long, String> mapEntry : mapSet)
        {
            Long keyValue = mapEntry.getKey();
            String value = mapEntry.getValue();
            list.add(new Alert(keyValue, value));
        }

        object.put("Alerts", list);

        StringWriter out = new StringWriter();

        object.writeJSONString(out);
        try
        {
            File file = new File("config/alerts.json");
            if (file.delete())
            {
                file.createNewFile();
            }
        }
        catch (Exception e)
        {
            TwircBot.log.error(e.getMessage());
        }

        PrintWriter printWriter = new PrintWriter("config/alerts.json", "UTF-8");

        try
        {
            printWriter.write(out.toString());
        }
        finally
        {
            printWriter.flush();
            printWriter.close();

        }
    }

    public static void chatBubble() throws IOException
    {
        JSONObject object = new JSONObject();
        JSONArray list = new JSONArray();
        list.add(new ChatBubble(
                (Long) TwircBot.chatBubbleList.get("timestamp"),
                (String) TwircBot.chatBubbleList.get("cbname"),
                (String) TwircBot.chatBubbleList.get("cbwho"),
                (String) TwircBot.chatBubbleList.get("cbmessage"),
                (int) TwircBot.chatBubbleList.get("cbtrue"),
                (int) TwircBot.chatBubbleList.get("cbtrollsound")));

        object.put("Alerts", list);

        StringWriter out = new StringWriter();

        object.writeJSONString(out);
        try
        {
            File file = new File("config/cb.json");
            if (file.delete())
            {
                file.createNewFile();
            }
        }
        catch (Exception e)
        {
            TwircBot.log.error(e.getMessage());
        }

        PrintWriter printWriter = new PrintWriter("config/cb.json", "UTF-8");

        try
        {
            printWriter.write(out.toString());
        }
        finally
        {
            printWriter.flush();
            printWriter.close();

        }
    }

    public static void mod() throws IOException
    {
        JSONObject object = new JSONObject();
        JSONArray list = new JSONArray();
        Set<Map.Entry<Long, String>> mapSet = TwircBot.modHiList.entrySet();
        for (Map.Entry<Long, String> mapEntry : mapSet)
        {
            Long keyValue = mapEntry.getKey();
            String value = mapEntry.getValue();
            list.add(new Alert(keyValue, value));
        }

        object.put("Alerts", list);

        StringWriter out = new StringWriter();

        object.writeJSONString(out);
        try
        {
            File file = new File("config/mod.json");
            if (file.delete())
            {
                file.createNewFile();
            }
        }
        catch (Exception e)
        {
            TwircBot.log.error(e.getMessage());
        }

        PrintWriter printWriter = new PrintWriter("config/mod.json", "UTF-8");

        try
        {
            printWriter.write(out.toString());
        }
        finally
        {
            printWriter.flush();
            printWriter.close();

        }
    }

    public static void welcome() throws IOException
    {
        JSONObject object = new JSONObject();
        JSONArray list = new JSONArray();
        Set<Map.Entry<Long, String>> mapSet = TwircBot.welcomeList.entrySet();
        for (Map.Entry<Long, String> mapEntry : mapSet)
        {
            Long keyValue = mapEntry.getKey();
            String value = mapEntry.getValue();
            list.add(new Alert(keyValue, value));
        }

        object.put("Alerts", list);

        StringWriter out = new StringWriter();

        object.writeJSONString(out);
        try
        {
            File file = new File("config/welcome.json");
            if (file.delete())
            {
                file.createNewFile();
            }
        }
        catch (Exception e)
        {
            TwircBot.log.error(e.getMessage());
        }

        PrintWriter printWriter = new PrintWriter("config/welcome.json", "UTF-8");

        try
        {
            printWriter.write(out.toString());
        }
        finally
        {
            printWriter.flush();
            printWriter.close();

        }
    }

    public static void rankList() throws IOException
    {
        JSONObject object = new JSONObject();
        JSONArray list = new JSONArray();
        Set<Map.Entry<String, Long>> mapSet = TwircBot.rankList.entrySet();
        for (Map.Entry<String, Long> mapEntry : mapSet)
        {
            String keyValue = mapEntry.getKey();
            Long value = mapEntry.getValue();
            list.add(new Ranks(keyValue, value));
        }

        object.put("Ranks", list);

        StringWriter out = new StringWriter();

        object.writeJSONString(out);
        try
        {
            File file = new File("config/rankList.json");
            if (file.delete())
            {
                file.createNewFile();
            }
        }
        catch (Exception e)
        {
            TwircBot.log.error(e.getMessage());
        }

        PrintWriter printWriter = new PrintWriter("config/rankList.json", "UTF-8");

        try
        {
            printWriter.write(out.toString());
        }
        finally
        {
            printWriter.flush();
            printWriter.close();
        }
    }

    public static void scheduleList() throws IOException
    {
        JSONObject object = new JSONObject();
        JSONArray list = new JSONArray();
        Set<Map.Entry<Long, Schedule>> mapSet = TwircBot.scheduledList.entrySet();
        for (Map.Entry<Long, Schedule> mapEntry : mapSet)
        {
            Schedule value = mapEntry.getValue();
            list.add(new Schedule(value.getNumber(), value.getMessage(), value.getToggle()));
        }

        object.put("Schedule", list);

        StringWriter out = new StringWriter();

        object.writeJSONString(out);
        try
        {
            File file = new File("config/scheduleList.json");
            if (file.delete())
            {
                file.createNewFile();
            }
        }
        catch (Exception e)
        {
            TwircBot.log.error(e.getMessage());
        }

        PrintWriter printWriter = new PrintWriter("config/scheduleList.json", "UTF-8");

        try
        {
            printWriter.write(out.toString());
        }
        finally
        {
            printWriter.flush();
            printWriter.close();
        }
    }

    public static void songRequestList()
    {
        JSONObject object = new JSONObject();
        JSONArray list = new JSONArray();
        Set<Map.Entry<YoutubeVideo, String>> mapSet = TwircBot.songRequestList.entrySet();
        for (Map.Entry<YoutubeVideo, String> mapEntry : mapSet)
        {
            YoutubeVideo keyValue = mapEntry.getKey();
            String value = mapEntry.getValue();
            list.add(new SongRequest(value, keyValue.getNumber(), keyValue.getLink(), keyValue.getTitle(), keyValue.getUploader(), keyValue.getDuration()));
        }

        object.put("Request", list);
        StringWriter out = new StringWriter();

        try
        {
            object.writeJSONString(out);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            File file = new File("config/songRequestList.json");
            if (file.delete())
            {
                file.createNewFile();
            }
        }
        catch (Exception e)
        {
            TwircBot.log.error(e.getMessage());
        }

        PrintWriter printWriter = null;
        try
        {
            printWriter = new PrintWriter("config/songRequestList.json", "UTF-8");
        }
        catch (FileNotFoundException | UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        assert printWriter != null;
        try
        {
            printWriter.write(out.toString());
        }
        finally
        {
            printWriter.flush();
            printWriter.close();
        }
    }

    public static void quoteList() throws IOException
    {
        JSONObject object = new JSONObject();
        JSONArray list = new JSONArray();
        Set<Map.Entry<Long, String>> mapSet = TwircBot.quoteList.entrySet();
        for (Map.Entry<Long, String> mapEntry : mapSet)
        {
            Long keyValue = mapEntry.getKey();
            String value = mapEntry.getValue();
            list.add(new Quote(keyValue, value));
        }

        object.put("Quotes", list);

        StringWriter out = new StringWriter();

        object.writeJSONString(out);
        try
        {
            File file = new File("config/quotes.json");
            if (file.delete())
            {
                file.createNewFile();
            }
        }
        catch (Exception e)
        {
            TwircBot.log.error(e.getMessage());
        }

        PrintWriter printWriter = new PrintWriter("config/quotes.json", "UTF-8");

        try
        {
            printWriter.write(out.toString());
        }
        finally
        {
            printWriter.flush();
            printWriter.close();

        }
    }

    public static void userList() throws IOException
    {
        JSONObject object = new JSONObject();
        JSONArray list = new JSONArray();
        Set<Map.Entry<String, Long>> mapSet = TwircBot.userList.entrySet();
        for (Map.Entry<String, Long> mapEntry : mapSet)
        {
            String keyValue = mapEntry.getKey();
            Long value = mapEntry.getValue();
            list.add(new User(keyValue, value));
        }

        object.put("Users", list);

        StringWriter out = new StringWriter();

        object.writeJSONString(out);
        try
        {
            File file = new File("config/points.json");
            if (file.delete())
            {
                file.createNewFile();
            }
        }
        catch (Exception e)
        {
            TwircBot.log.error(e.getMessage());
        }

        PrintWriter printWriter = new PrintWriter("config/points.json", "UTF-8");

        try
        {
            printWriter.write(out.toString());
        }
        finally
        {
            printWriter.flush();
            printWriter.close();
        }
    }

    public static void ranksList() throws IOException
    {
        JSONObject object = new JSONObject();
        JSONArray list = new JSONArray();
        Set<Map.Entry<String, String>> mapSet = TwircBot.rankUserList.entrySet();
        for (Map.Entry<String, String> mapEntry : mapSet)
        {
            String key = mapEntry.getKey();
            String value = mapEntry.getValue();
            list.add(new RankUser(key, value));
        }
        object.put("Ranks", list);
        StringWriter out = new StringWriter();
        object.writeJSONString(out);

        try
        {
            File file = new File("config/ranks.json");
            if (file.delete())
            {
                file.createNewFile();
            }
        }
        catch (Exception e)
        {
            TwircBot.log.error(e.getMessage());
        }

        PrintWriter printWriter = new PrintWriter("config/ranks.json", "UTF-8");

        try
        {
            printWriter.write(out.toString());
        }
        finally
        {
            printWriter.flush();
            printWriter.close();
        }
    }

    public static void permsList() throws IOException
    {
        JSONObject object = new JSONObject();
        JSONArray list = new JSONArray();
        Set<Map.Entry<String, String>> mapSet = TwircBot.permList.entrySet();
        for (Map.Entry<String, String> mapEntry : mapSet)
        {
            String key = mapEntry.getKey();
            String value = mapEntry.getValue();
            list.add(new Perm(key, value));
        }
        object.put("Permissions", list);
        StringWriter out = new StringWriter();
        object.writeJSONString(out);

        try
        {
            File file = new File("config/permissions.json");
            if (file.delete())
            {
                file.createNewFile();
            }
        }
        catch (Exception e)
        {
            TwircBot.log.error(e.getMessage());
        }

        PrintWriter printWriter = new PrintWriter("config/permissions.json", "UTF-8");

        try
        {
            printWriter.write(out.toString());
        }
        finally
        {
            printWriter.flush();
            printWriter.close();
        }
    }

    public static void commandList() throws IOException
    {
        JSONObject object = new JSONObject();
        JSONArray list = new JSONArray();
        Set<Map.Entry<String, String>> mapSet = TwircBot.commandList.entrySet();
        for (Map.Entry<String, String> mapEntry : mapSet)
        {
            String key = mapEntry.getKey();
            String value = mapEntry.getValue();
            list.add(new Command(key, value));
        }
        object.put("Commands", list);
        StringWriter out = new StringWriter();
        object.writeJSONString(out);

        try
        {
            File file = new File("config/commands.json");
            if (file.delete())
            {
                file.createNewFile();
            }
        }
        catch (Exception e)
        {
            TwircBot.log.error(e.getMessage());
        }

        PrintWriter printWriter = new PrintWriter("config/commands.json", "UTF-8");

        try
        {
            printWriter.write(out.toString());
        }
        finally
        {
            printWriter.flush();
            printWriter.close();

        }
    }


    public static void commandPermList() throws IOException
    {
        JSONObject object = new JSONObject();
        JSONArray list = new JSONArray();
        Set<Map.Entry<String, String>> mapSet = TwircBot.commandpermList.entrySet();
        for (Map.Entry<String, String> mapEntry : mapSet)
        {
            String key = mapEntry.getKey();
            String value = mapEntry.getValue();
            list.add(new CommandPerm(key, value));
        }
        object.put("Commands", list);
        StringWriter out = new StringWriter();
        object.writeJSONString(out);

        try
        {
            File file = new File("config/commandpermissions.json");
            if (file.delete())
            {
                file.createNewFile();
            }
        }
        catch (Exception e)
        {
            TwircBot.log.error(e.getMessage());
        }

        PrintWriter printWriter = new PrintWriter("config/commandpermissions.json", "UTF-8");

        try
        {
            printWriter.write(out.toString());
        }
        finally
        {
            printWriter.flush();
            printWriter.close();
        }
    }

    public static void dataList() throws IOException
    {
        JSONObject object = new JSONObject();

        object.put("Nick", Defaults.getBotName());
        object.put("Points", Defaults.getPointName());
        object.put("Raffle cost", Defaults.getRaffleCost());
        object.put("VIP", Defaults.isVip);
        object.put("Link", Defaults.linkPurge);
        object.put("WoT", Defaults.wotPurge);
        object.put("Caps", Defaults.capsPurge);
        object.put("CTT", Defaults.cttText);
        object.put("Whisper", Defaults.whisperToggle);

        StringWriter out = new StringWriter();
        object.writeJSONString(out);
        try
        {
            File file = new File("config/data.json");
            if (file.delete())
            {
                file.createNewFile();
            }
        }
        catch (Exception e)
        {
            TwircBot.log.error(e.getMessage());
        }

        PrintWriter printWriter = new PrintWriter("config/data.json", "UTF-8");

        try
        {
            printWriter.write(out.toString());
        }
        finally
        {
            printWriter.flush();
            printWriter.close();
        }
    }

    public static void blackList()
    {
        JSONObject object = new JSONObject();
        JSONArray list = new JSONArray();

        for (int i = 0; i < TwircBot.blackList.size(); i++)
        {
            list.add(new Blacklist(TwircBot.blackList.get(i)));
        }
        object.put("Blacklist", list);


        StringWriter out = new StringWriter();
        try
        {
            object.writeJSONString(out);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            File file = new File("config/blacklist.json");
            if (file.delete())
            {
                file.createNewFile();
            }
        }
        catch (Exception e)
        {
            TwircBot.log.error(e.getMessage());
        }

        PrintWriter printWriter = null;
        try
        {
            printWriter = new PrintWriter("config/blacklist.json", "UTF-8");
        }
        catch (FileNotFoundException | UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        assert printWriter != null;

        try
        {
            printWriter.write(out.toString());
        }
        finally
        {
            printWriter.flush();
            printWriter.close();
        }
    }

    public static void noteList() throws IOException
    {
        JSONObject object = new JSONObject();
        JSONArray list = new JSONArray();
        Set<Map.Entry<Long, String>> mapSet = TwircBot.noteList.entrySet();
        for (Map.Entry<Long, String> mapEntry : mapSet)
        {
            Long keyValue = mapEntry.getKey();
            String value = mapEntry.getValue();
            list.add(new Note(keyValue, value));
        }

        object.put("Notes", list);

        StringWriter out = new StringWriter();

        object.writeJSONString(out);
        try
        {
            File file = new File("config/note.json");
            if (file.delete())
            {
                file.createNewFile();
            }
        }
        catch (Exception e)
        {
            TwircBot.log.error(e.getMessage());
        }

        PrintWriter printWriter = new PrintWriter("config/note.json", "UTF-8");

        try
        {
            printWriter.write(out.toString());
        }
        finally
        {
            printWriter.flush();
            printWriter.close();

        }
    }


    public static void raffleList() throws IOException
    {
        JSONObject obj = new JSONObject();
        JSONArray array = new JSONArray();
        Set<Map.Entry<Long, String>> mapSet = TwircBot.raffleList.entrySet();
        for (Map.Entry<Long, String> mapEntry : mapSet)
        {
            Long key = mapEntry.getKey();
            String value = mapEntry.getValue();
            array.add(new Raffle(key, value));
        }
        obj.put("Raffle", array);
        StringWriter out = new StringWriter();
        obj.writeJSONString(out);

        try
        {
            File file = new File("config/raffleTickets.json");
            if (file.delete())
            {
                file.createNewFile();
            }
        }
        catch (Exception e)
        {
            TwircBot.log.error(e.getMessage());
        }

        PrintWriter printWriter = new PrintWriter("config/raffleTickets.json", "UTF-8");

        try
        {
            printWriter.write(out.toString());
        }
        finally
        {
            printWriter.flush();
            printWriter.close();
        }

    }

    public static void subMessages()
    {
        JSONObject object = new JSONObject();

        object.put("New", Defaults.newSub);
        object.put("Old", Defaults.oldSub);

        StringWriter out = new StringWriter();
        try
        {
            object.writeJSONString(out);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            File file = new File("config/subMessages.json");
            if (file.delete())
            {
                file.createNewFile();
            }
        }
        catch (Exception e)
        {
            TwircBot.log.error(e.getMessage());
        }

        PrintWriter printWriter = null;
        try
        {
            printWriter = new PrintWriter("config/subMessages.json", "UTF-8");
        }
        catch (FileNotFoundException | UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        assert printWriter != null;

        try
        {
            printWriter.write(out.toString());
        }
        finally
        {
            printWriter.flush();
            printWriter.close();
        }
    }

    public static void properties()
    {
        try
        {
            File file = new File("extraConfigs.properties");
            FileOutputStream fileOut = new FileOutputStream(file);
            TwircBot.extra.store(fileOut, "Extra Configs");
            fileOut.close();
        }
        catch (Exception e)
        {
            System.out.println("Couldn't save the extra config properties");
        }
    }
}
