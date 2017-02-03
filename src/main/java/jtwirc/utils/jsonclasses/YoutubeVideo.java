package jtwirc.utils.jsonclasses;

import jtwirc.utils.Defaults;
import jtwirc.utils.JSONParser;
import jtwirc.utils.YoutubeParser;
import org.json.JSONArray;
import org.json.JSONObject;

public class YoutubeVideo
{

    private String link; //https://www.youtube.com/watch?v=id
    private String title;
    private String uploader;
    private String duration;
    private int number;

    public YoutubeVideo(String link)
    {
        String id;
        if (YoutubeParser.getVideoId(link) == null)
        {
            id = link;
        }
        else
        {
            id = YoutubeParser.getVideoId(link);
        }

        this.link = "https://www.youtube.com/watch?v=" + id;
        try
        {
            JSONObject json = new JSONObject(JSONParser.readUrl("https://www.googleapis.com/youtube/v3/videos?id=" + id + "&key=<KEYHERE>&part=snippet,contentDetails&fields=items(snippet/title,snippet/channelTitle,contentDetails/duration)"));
            JSONArray array = json.getJSONArray("items");
            JSONObject snippet = array.getJSONObject(0).getJSONObject("snippet");
            JSONObject content = array.getJSONObject(0).getJSONObject("contentDetails");
            String title = String.valueOf(snippet.get("title"));
            String channelTitle = String.valueOf(snippet.get("channelTitle"));
            String duration = String.valueOf(content.get("duration"));

            this.title = title;
            this.uploader = channelTitle;
            this.duration = youtubeString(duration);
            this.number = Defaults.getSongRequestNumber();

        }
        catch (Exception ignored)
        {
        }
    }

    private static String youtubeString(String duration)
    {
        String[] dur = {"0", "0", "0"}/**ChannelMethods.formatToHMS(duration)*/;
        if (dur[0].equals(""))
        {
            return dur[1] + ":" + dur[2];
        }
        else
        {
            return dur[0] + ":" + dur[1] + ":" + dur[2];
        }

    }

    public String getDuration()
    {
        return duration;
    }

    public String getLink()
    {
        return link;
    }

    public String getTitle()
    {
        return title;
    }

    public String getUploader()
    {
        return uploader;
    }

    public int getNumber()
    {
        return number;
    }
}
