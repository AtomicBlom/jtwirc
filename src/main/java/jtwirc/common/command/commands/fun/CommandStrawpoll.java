package jtwirc.common.command.commands.fun;

import jtwirc.common.command.CommandBase;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.Defaults;
import jtwirc.utils.HTTPPoster;
import jtwirc.utils.MessageSending;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Collections;

@SuppressWarnings("unchecked")
public class CommandStrawpoll extends CommandBase
{

    //!strawpoll create title;option,option,option

    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        if (user.isMod() || user.isBroadcaster())
        {
            if (args.length == 2)
            {
                if (args[1].equals("results"))
                {
                    try
                    {
                        JSONParser parser = new JSONParser();
                        Object obj = parser.parse(HTTPPoster.getRemoteContent("http://strawpoll.me/api/v2/polls/" + Defaults.lastStrawpoll));
                        JSONObject response = (JSONObject) obj;
                        JSONArray options = (JSONArray) response.get("options");
                        JSONArray votes = (JSONArray) response.get("votes");

                        String results = "";
                        for (int i = 0; i < options.size(); i++)
                        {
                            results += options.get(i) + ": " + votes.get(i) + "; ";
                        }
                        MessageSending.sendNormalMessage(results);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            if (args.length >= 3)
            {
                if (args[1].equals("results") && !args[2].isEmpty())
                {
                    try
                    {
                        JSONParser parser = new JSONParser();
                        Object obj = parser.parse(HTTPPoster.getRemoteContent("http://strawpoll.me/api/v2/polls/" + args[2]));
                        JSONObject response = (JSONObject) obj;
                        JSONArray options = (JSONArray) response.get("options");
                        JSONArray votes = (JSONArray) response.get("votes");

                        String results = "";
                        for (int i = 0; i < options.size(); i++)
                        {
                            results += options.get(i) + ": " + votes.get(i) + "; ";
                        }
                        MessageSending.sendNormalMessage(results);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                if (args[1].equals("create"))
                {
                    String newString = Defaults.fuseArray(args, 2);
                    String[] params = newString.split(";");
                    String[] options = params[1].split(",");
                    JSONObject jsonObject = new JSONObject();
                    JSONArray optionsArray = new JSONArray();

                    jsonObject.put("title", params[0]);
                    jsonObject.put("multi", false);
                    jsonObject.put("permissive", false);

                    Collections.addAll(optionsArray, options);
                    jsonObject.put("options", optionsArray);

                    String urlTestString = jsonObject.toString();

                    String ids = HTTPPoster.postRemoteDataStrawpoll(urlTestString);

                    String id = ids.substring(ids.lastIndexOf(":") + 1);

                    Defaults.setLastStrawpoll(Integer.parseInt(id));
                    MessageSending.sendNormalMessage("Strawpoll created vote here : http://www.strawpoll.me/" + id);
                }
            }
        }
    }
}
