package com.gikk.twirk.common.music.handlers;

import com.gikk.twirk.events.TwirkListenerBaseImpl;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;
import todo.ChirpBot;

public class CommandChecker extends TwirkListenerBaseImpl
{

    @Deprecated
    private static void nowPlaying()
    {
//        String song;
//        try {
//            File file = new File("B:\\WeAllPlayBot\\config\\nowplaying.txt");
//            InputStream fis = new FileInputStream(file);
//            InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
//            BufferedReader br = new BufferedReader(isr);
//            while ((song = br.readLine()) != null) {
//                MessageListener.sendMessage(song);
//            }
//            br.close();
//        } catch (IOException e) {
//            MessageListener.sendMessage("nowplaying.txt file not found in chirpbot-music folder.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static void addCommand(String command, String response)
    {
        ChirpBot.commandList.put(command, response);
    }

    @Override
    public void onPrivMsg(TwitchUser sender, TwitchMessage message)
    {
        String command = message.getContent().split(" ")[0];
        ChirpBot.commandList.keySet().stream().filter(string -> string.equalsIgnoreCase(command)).forEach(string -> {
            //For use later
        });

//        if (command.equalsIgnoreCase("!song")) {
//            nowPlaying();
//        }
    }
}
