package jtwirc.utils.json;

import jtwirc.TwircBot;
import jtwirc.utils.Defaults;
import jtwirc.utils.jsonclasses.Schedule;
import jtwirc.utils.jsonclasses.YoutubeVideo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Load
{
    public static void alerts()
    {
        try
        {
            FileReader reader = new FileReader("config/alerts.json");
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(reader);

            JSONArray alerts = (JSONArray) object.get("Alerts");

            for (Object alert : alerts)
            {
                JSONObject inner = (JSONObject) alert;
                TwircBot.shoutoutList.put((Long) inner.get("timestamp"), (String) inner.get("channel"));
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            File file = new File("config/alerts.json");
            try
            {
                file.createNewFile();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public static void chatbubble()
    {
        try
        {
            FileReader reader = new FileReader("config/cb.json");
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(reader);

            JSONArray alerts = (JSONArray) object.get("Alerts");

            for (Object alert : alerts)
            {
                JSONObject inner = (JSONObject) alert;
                TwircBot.chatBubbleList.put("timestamp", inner.get("timestamp"));
                TwircBot.chatBubbleList.put("cbname", inner.get("cbname"));
                TwircBot.chatBubbleList.put("cbwho", inner.get("cbwho"));
                TwircBot.chatBubbleList.put("cbmessage", inner.get("cbmessage"));
                TwircBot.chatBubbleList.put("cbtrue", inner.get("cbtrue"));
                TwircBot.chatBubbleList.put("cbtrollsound", inner.get("cbtrollsound"));
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            File file = new File("config/cb.json");
            try
            {
                file.createNewFile();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public static void mod()
    {
        try
        {
            FileReader reader = new FileReader("config/mod.json");
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(reader);

            JSONArray alerts = (JSONArray) object.get("Alerts");

            for (Object alert : alerts)
            {
                JSONObject inner = (JSONObject) alert;
                TwircBot.modHiList.put((Long) inner.get("timestamp"), (String) inner.get("modname"));
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            File file = new File("config/mod.json");
            try
            {
                file.createNewFile();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public static void welcome()
    {
        try
        {
            FileReader reader = new FileReader("config/welcome.json");
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(reader);

            JSONArray alerts = (JSONArray) object.get("Alerts");

            for (Object alert : alerts)
            {
                JSONObject inner = (JSONObject) alert;
                TwircBot.welcomeList.put((Long) inner.get("timestamp"), (String) inner.get("channel"));
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            File file = new File("config/welcome.json");
            try
            {
                file.createNewFile();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public static void quoteList()
    {
        try
        {
            FileReader reader = new FileReader("config/quotes.json");
            JSONParser parser = new JSONParser();
            JSONObject object1 = (JSONObject) parser.parse(reader);

            JSONArray quotes = (JSONArray) object1.get("Quotes");

            for (Object quote : quotes)
            {
                JSONObject inner = (JSONObject) quote;
                TwircBot.quoteList.put((Long) inner.get("number"), (String) inner.get("quote"));
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Quote list couldn't be found.");
            File file = new File("config/quotes.json");
            try
            {
                file.createNewFile();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
            TwircBot.log.error(e.getMessage());
        }
        catch (ParseException | IOException e)
        {
            TwircBot.log.error(e.getMessage());

        }
    }

    public static void blackList()
    {
        try
        {
            FileReader reader = new FileReader("config/blacklist.json");
            JSONParser parser = new JSONParser();
            JSONObject object1 = (JSONObject) parser.parse(reader);

            JSONArray quotes = (JSONArray) object1.get("Blacklist");

            for (Object quote : quotes)
            {
                JSONObject inner = (JSONObject) quote;
                TwircBot.blackList.add((String) inner.get("user"));
            }
        }
        catch (FileNotFoundException e)
        {
            File file = new File("config/blacklist.json");
            try
            {
                file.createNewFile();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
            System.out.println("Blacklist couldn't be found.");
            TwircBot.log.error(e.getMessage());
        }
        catch (ParseException | IOException e)
        {
            TwircBot.log.error(e.getMessage());

        }
    }

    public static void noteList()
    {
        try
        {
            FileReader reader = new FileReader("config/note.json");
            JSONParser parser = new JSONParser();
            JSONObject object1 = (JSONObject) parser.parse(reader);

            JSONArray quotes = (JSONArray) object1.get("Notes");

            for (Object quote : quotes)
            {
                JSONObject inner = (JSONObject) quote;
                TwircBot.noteList.put((Long) inner.get("number"), (String) inner.get("note"));
            }
        }
        catch (FileNotFoundException e)
        {
            File file = new File("config/note.json");
            try
            {
                file.createNewFile();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
            System.out.println("Note list couldn't be found.");
            TwircBot.log.error(e.getMessage());
        }
        catch (ParseException | IOException e)
        {
            TwircBot.log.error(e.getMessage());

        }
    }

    public static void ranksList()
    {
        try
        {
            FileReader reader = new FileReader("config/rankList.json");
            JSONParser parser = new JSONParser();
            JSONObject object1 = (JSONObject) parser.parse(reader);

            JSONArray users = (JSONArray) object1.get("Ranks");

            for (Object user : users)
            {
                JSONObject inner = (JSONObject) user;
                TwircBot.rankList.put((String) inner.get("name"), (Long) inner.get("amount"));
            }
        }
        catch (FileNotFoundException e)
        {
            File file = new File("config/rankList.json");
            try
            {
                file.createNewFile();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
            System.out.println("Rank list couldn't be found.");
            TwircBot.log.error(e.getMessage());
        }
        catch (ParseException | IOException e)
        {
            TwircBot.log.error(e.getMessage());
        }
    }

    public static void rankList()
    {
        try
        {
            FileReader reader = new FileReader("config/ranks.json");
            JSONParser parser = new JSONParser();
            JSONObject object1 = (JSONObject) parser.parse(reader);
            JSONArray ranks = (JSONArray) object1.get("Ranks");

            for (Object rank : ranks)
            {
                JSONObject inner = (JSONObject) rank;
                TwircBot.rankUserList.put((String) inner.get("user"), (String) inner.get("rank"));
            }
        }
        catch (FileNotFoundException e)
        {
            File file = new File("config/ranks.json");
            try
            {
                file.createNewFile();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
            System.out.println("Ranks couldn't be found.");
            TwircBot.log.error(e.getMessage());
        }
        catch (ParseException | IOException e)
        {
            TwircBot.log.error(e.getMessage());
        }
    }

    public static void scheduleList()
    {
        try
        {
            FileReader reader = new FileReader("config/scheduleList.json");
            JSONParser parser = new JSONParser();
            JSONObject object1 = (JSONObject) parser.parse(reader);
            JSONArray messages = (JSONArray) object1.get("Schedule");

            for (Object schedule : messages)
            {
                JSONObject inner = (JSONObject) schedule;
                TwircBot.scheduledList.put((Long) inner.get("number"), new Schedule((Long) inner.get("number"), inner.get("message").toString(), (boolean) inner.get("toggle")));
            }
        }
        catch (FileNotFoundException e)
        {
            File file = new File("config/scheduleList.json");
            try
            {
                file.createNewFile();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
            System.out.println("Schedule couldn't be found.");
            TwircBot.log.error(e.getMessage());
        }
        catch (ParseException | IOException e)
        {
            TwircBot.log.error(e.getMessage());
        }
    }

    public static void userList()
    {
        try
        {
            FileReader reader = new FileReader("config/points.json");
            JSONParser parser = new JSONParser();
            JSONObject object1 = (JSONObject) parser.parse(reader);

            JSONArray users = (JSONArray) object1.get("Users");

            for (Object user : users)
            {
                JSONObject inner = (JSONObject) user;
                TwircBot.userList.put((String) inner.get("user"), (Long) inner.get("amount"));
            }
        }
        catch (FileNotFoundException e)
        {
            File file = new File("config/points.json");
            try
            {
                file.createNewFile();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
            System.out.println("Points couldn't be found.");
            TwircBot.log.error(e.getMessage());
        }
        catch (ParseException | IOException e)
        {
            TwircBot.log.error(e.getMessage());
        }
    }

    public static void permList()
    {
        try
        {
            FileReader reader = new FileReader("config/permissions.json");
            JSONParser parser = new JSONParser();
            JSONObject object1 = (JSONObject) parser.parse(reader);
            JSONArray ranks = (JSONArray) object1.get("Permissions");

            for (Object rank : ranks)
            {
                JSONObject inner = (JSONObject) rank;
                TwircBot.permList.put((String) inner.get("name"), (String) inner.get("permission"));
            }
        }
        catch (FileNotFoundException e)
        {
            File file = new File("config/permissions.json");
            try
            {
                file.createNewFile();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
            System.out.println("Permissions couldn't be found.");
            TwircBot.log.error(e.getMessage());
        }
        catch (ParseException | IOException e)
        {
            TwircBot.log.error(e.getMessage());
        }
    }

    public static void commandList()
    {
        try
        {
            FileReader reader = new FileReader("config/commands.json");
            JSONParser parser = new JSONParser();
            JSONObject object1 = (JSONObject) parser.parse(reader);
            JSONArray ranks = (JSONArray) object1.get("Commands");

            for (Object rank : ranks)
            {
                JSONObject inner = (JSONObject) rank;
                String command = (String) inner.get("command");
                if (!command.equalsIgnoreCase("!ctt"))
                {
                    if (command.startsWith("!"))
                    {
                        TwircBot.commandList.put(command, (String) inner.get("response"));
                    }
                }
            }
        }
        catch (FileNotFoundException e)
        {
            File file = new File("config/commands.json");
            try
            {
                file.createNewFile();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
            System.out.println("Commands couldn't be found.");
            TwircBot.log.error(e.getMessage());
        }
        catch (ParseException | IOException e)
        {
            TwircBot.log.error(e.getMessage());
        }
    }

    public static void commandPermList()
    {
        try
        {
            FileReader reader = new FileReader("config/commandpermissions.json");
            JSONParser parser = new JSONParser();
            JSONObject object1 = (JSONObject) parser.parse(reader);
            JSONArray ranks = (JSONArray) object1.get("Commands");

            for (Object rank : ranks)
            {
                JSONObject inner = (JSONObject) rank;
                TwircBot.commandpermList.put((String) inner.get("command"), (String) inner.get("permission"));
            }
        }
        catch (FileNotFoundException e)
        {
            File file = new File("config/commmandpermissions.json");
            try
            {
                file.createNewFile();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
            System.out.println("Command Permissions couldn't be found.");
            TwircBot.log.error(e.getMessage());
        }
        catch (ParseException | IOException e)
        {
            TwircBot.log.error(e.getMessage());
        }
    }

    public static void raffleList()
    {
        try
        {
            FileReader reader = new FileReader("config/raffleTickets.json");
            JSONParser parser = new JSONParser();
            JSONObject object1 = (JSONObject) parser.parse(reader);
            JSONArray ranks = (JSONArray) object1.get("Raffle");

            for (Object rank : ranks)
            {
                JSONObject inner = (JSONObject) rank;
                TwircBot.raffleList.put((Long) inner.get("id"), (String) inner.get("user"));
            }
        }
        catch (FileNotFoundException e)
        {
            File file = new File("config/raffleTickets.json");
            try
            {
                file.createNewFile();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
            System.out.println("Raffle Tickets couldn't be found.");
            TwircBot.log.error(e.getMessage());
        }
        catch (ParseException | IOException e)
        {
            TwircBot.log.error(e.getMessage());
        }
    }

    public static void songRequestList()
    {
        try
        {
            FileReader reader = new FileReader("config/songRequestList.json");
            JSONParser parser = new JSONParser();
            JSONObject object1 = (JSONObject) parser.parse(reader);
            JSONArray ranks = (JSONArray) object1.get("Request");

            for (Object rank : ranks)
            {
                JSONObject inner = (JSONObject) rank;
                TwircBot.songRequestList.put(new YoutubeVideo((String) inner.get("link")), (String) inner.get("request"));
                Defaults.incSongRequestNumber();
            }
        }
        catch (FileNotFoundException e)
        {
            File file = new File("config/songRequestList.json");
            try
            {
                file.createNewFile();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
            System.out.println("Song Requests couldn't be found.");
            TwircBot.log.error(e.getMessage());
        }
        catch (ParseException | IOException e)
        {
            TwircBot.log.error(e.getMessage());
        }
    }

    public static void steamList()
    {
        try
        {
            org.json.JSONObject json = new org.json.JSONObject(jtwirc.utils.JSONParser.readUrl("http://api.steampowered.com/ISteamApps/GetAppList/v0001/"));
            org.json.JSONObject applist = json.getJSONObject("applist");
            org.json.JSONObject apps = applist.getJSONObject("apps");
            org.json.JSONArray app = apps.getJSONArray("app");
            for (int i = 0; i < app.length(); i++)
            {
                String name = String.valueOf(app.getJSONObject(i).get("name"));
                Integer appid = (Integer) app.getJSONObject(i).get("appid");
                TwircBot.steamList.put(name, appid);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("Done loading steam game list, amount of games loaded " + TwircBot.steamList.size());
    }

    public static void subMessages()
    {
        try
        {
            FileReader reader = new FileReader("config/subMessages.json");
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(reader);
            /*Defaults.newSub = String.valueOf(object.get("New"));
			Defaults.oldSub = String.valueOf(object.get("Old"));
			Defaults.freeSub = String.valueOf(object.get("Free"));*/
        }
        catch (FileNotFoundException e)
        {
            File file = new File("config/subMessages.json");
            try
            {
                file.createNewFile();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
            System.out.println("Sub messages couldn't be found.");
            TwircBot.log.error(e.getMessage());
        }
        catch (ParseException | IOException e)
        {
            TwircBot.log.error(e.getMessage());
        }
    }

    public static void patronSounds()
    {
        List<String> patrons = new ArrayList<>();
        List<String> patronSound = new ArrayList<>();
        List<String> patronRanks = new ArrayList<>();
        List<String> ranks = new ArrayList<>();

        for (Object property : TwircBot.musicProp.keySet())
        {
            String prop = (String) property;
            if (prop.startsWith("patronSound"))
            {
                patronSound.add(TwircBot.musicProp.getProperty(prop));
            }
            else if (prop.startsWith("patronRank"))
            {
                //Unused
                patronRanks.add(TwircBot.musicProp.getProperty(prop));
            }
            else if (prop.startsWith("patron"))
            {
                patrons.add(TwircBot.musicProp.getProperty(prop));
            }
            else if (prop.startsWith("rank"))
            {
                //Unused
                ranks.add(TwircBot.musicProp.getProperty(prop));
            }
        }

        for (int c = 0; c < patrons.size(); c++)
        {
            TwircBot.patronSounds.put(patrons.get(c), new File("sounds/" + patronSound.get(c)));
            TwircBot.commandList.put(patrons.get(c), "!" + patrons.get(c));
            TwircBot.commandpermList.put("!" + patrons.get(c), "mod");
        }
    }
}
