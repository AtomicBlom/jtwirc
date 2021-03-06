package jtwirc;

import com.mb3364.twitch.api.Twitch;
import jtwirc.annotation.AnnotationSweeper;
import jtwirc.common.listeners.InfoListener;
import jtwirc.common.listeners.MessageListener;
import jtwirc.common.listeners.UnknownListener;
import jtwirc.common.listeners.WhisperListener;
import jtwirc.common.module.Alerts;
import jtwirc.common.module.Commands;
import jtwirc.common.module.Hooks;
import jtwirc.common.module.HooksWhisperer;
import jtwirc.utils.Defaults;
import jtwirc.utils.json.Load;
import jtwirc.utils.json.Save;
import jtwirc.utils.jsonclasses.Schedule;
import jtwirc.utils.jsonclasses.YoutubeVideo;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.core.AudioIO;
import net.beadsproject.beads.core.io.JavaSoundAudioIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * Code generously donated by https://github.com/TheCricket
 * This needs to be grokked and then dealt with.
 */
public class TwircBot
{
    public static final Logger log = LoggerFactory.getLogger(TwircBot.class.getName());
    public static final int BOT_COMMANDS = 0;
    public static final int BOT_WHISPER = 1;
    public static final int BOT_MUSIC = 2;
    public static final Twitch twitch = new Twitch();
    public static final boolean DEBUG = false;
    //Commands
    public static Properties extra = new Properties();
    public static Properties config = new Properties();
    public static HashMap<String, Long> userList = new HashMap<>();
    public static HashMap<String, String> rankUserList = new HashMap<>();
    public static HashMap<String, Long> rankList = new HashMap<>();
    public static HashMap<Long, String> quoteList = new HashMap<>();
    public static HashMap<Long, String> noteList = new HashMap<>();
    public static HashMap<String, String> permList = new HashMap<>();
    public static HashMap<String, String> commandList = new HashMap<>();
    public static HashMap<String, String> commandpermList = new HashMap<>();
    public static HashMap<Long, String> raffleList = new HashMap<>();
    public static ArrayList<String> blackList = new ArrayList<>();
    public static ArrayList<String> permitted = new ArrayList<>();
    public static HashMap<String, Integer> steamList = new HashMap<>();
    public static HashMap<Long, Schedule> scheduledList = new HashMap<>();
    public static HashMap<String, Long> strikeList = new HashMap<>();
    public static HashMap<YoutubeVideo, String> songRequestList = new HashMap<>();
    //Alerts
    public static HashMap<Long, String> welcomeList = new HashMap<>();
    public static HashMap<Long, String> shoutoutList = new HashMap<>();
    public static HashMap<String, Object> chatBubbleList = new HashMap<>();
    public static HashMap<Long, String> modHiList = new HashMap<>();
    //Music
    public static Properties musicProp = new Properties();
    public static Map<String, File> patronSounds = new HashMap<>();
    public static List<String> peopleWhoHaveSpoke = new ArrayList<>();
    public static AudioIO audioIO;
    public static AudioContext audioContext;
    //Bots
    public static Twirc bot;
    public static Twirc whisper;
    public static List<Twirc> bots = new ArrayList<>();
    public static AnnotationSweeper sweeper = new AnnotationSweeper();

    public static void main(String[] args) throws Exception
    {
        setupProperties();
        setupConfigs();

        audioIO = new JavaSoundAudioIO();
        audioContext = new AudioContext(audioIO);

        bot = new TwircBuilder(Defaults.getStreamer().startsWith("#") ? Defaults.getStreamer() : "#" + Defaults.getStreamer(), Defaults.getBotName(), Defaults.getOAuth(), Twirc.BotType.COMMANDS).build();
        whisper = new TwircBuilder(Defaults.getBotName().startsWith("#") ? Defaults.getBotName() : "#" + Defaults.getBotName(), Defaults.getBotName(), Defaults.getOAuth(), Twirc.BotType.WHISPER).build();
        Twirc music = new TwircBuilder(Defaults.getStreamer().startsWith("#") ? Defaults.getStreamer() : "#" + Defaults.getStreamer(), "TwircBot", "INSERT OAUTH", Twirc.BotType.MUSIC).build();

        bots.add(bot);
        bots.add(whisper);
        bots.add(music);

        for (Class clazz : sweeper.sweep(AnnotationSweeper.AnnotationType.UNFINISHED))
        {
            System.out.println(String.format("There is an Unfinished Class at %s that needs your attention", clazz.getName()));
        }

        bots();
    }

    private static void setupProperties()
    {
        File file = new File("config/extraConfigs.properties");
        try
        {
            if (file.exists())
            {
                FileInputStream fileIn = new FileInputStream(file);
                extra.load(fileIn);
                fileIn.close();
            }
            else
            {
                //noinspection ResultOfMethodCallIgnored
                file.mkdirs();
                FileOutputStream fileOut = new FileOutputStream(file);
                extra.setProperty("linkToggle", "true");
                extra.setProperty("capsToggle", "true");
                extra.setProperty("wotToggle", "true");
                extra.setProperty("whisperToggle", "false");
                extra.setProperty("scheduleToggle", "false");
                extra.setProperty("ctt", "");
                extra.store(fileOut, "Extra Configs");
                fileOut.close();
                setupProperties();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void setupConfigs() throws Exception
    {
        File file = new File("config/serverConfig.properties");
        try
        {
            if (file.exists())
            {
                FileInputStream fileIn = new FileInputStream(file);
                config.load(fileIn);
                fileIn.close();
            }
            else
            {
                //noinspection ResultOfMethodCallIgnored
                file.mkdirs();
                FileOutputStream fileOut = new FileOutputStream(file);
                config.setProperty("points", "IFVIPPUTPOINTNAMEHERE");
                config.setProperty("bitmessage0", "Used for bits 0-100");
                config.setProperty("bitmessage1", "Used for bits 101-1000");
                config.setProperty("bitmessage2", "Used for bits 1001-5000");
                config.setProperty("bitmessage3", "Used for bits 5001-10000");
                config.setProperty("nick", "BOTNAMEHERE");
                config.setProperty("oauth", "OAUTHHERE");
                config.setProperty("autoJoinChannel", "YOURSTREAMCHANNELNAMEHERE");
                config.setProperty("chatServer", "true");
                config.store(fileOut, "Server Configs");
                fileOut.close();
                System.out.print("Please fill in the serverConfig.properties before pressing enter.");
                //noinspection ResultOfMethodCallIgnored
                System.in.read();
                System.out.println("Reading serverConfig.properties.");
                setupConfigs();
                setupProperties();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        File music = new File("config/chirpbot.properties");
        try
        {
            if (music.exists())
            {
                FileInputStream fileIn = new FileInputStream(music);
                musicProp.load(fileIn);
                fileIn.close();
            }
            else
            {
                FileWriter writer = new FileWriter(music);
                writer.write(
                        "#--- TwircBot Properties ---\n" +
                                "#You can set a custom bot entry message or make it blank to disable it\n" +
                                "intro=TwircBot started. Lets play some tunes!\n" +
                                "#--- Patrons ---\n" +
                                "#Patrons are added by doing patron(number) you can add as many patrons as you want!\n" +
                                "patron0=cricketnu\n" +
                                "\n" +
                                "#--- Sound Files ---\n" +
                                "#Sounds are assigned via patronSound(patronNumber) where the patron number is the same as the ones above\n" +
                                "#These include the file extension. You can test multiple file extensions\n" +
                                "#The default directory of B:\\\\Cast Media\\\\Sound Clips\\\\ is included so no need to add that :D\n" +
                                "patronSound0=cricket.mp3\n" +
                                "\n" +
                                "#--- Ranks (Unused Don't worry about it) ---\n" +
                                "#There are 3 ranks for you to name and assign to your patrons. Simply put rank 0, 1, and 2\n" +
                                "rank0=Low Tier\n" +
                                "rank1=Mid Tier\n" +
                                "rank2=Top Tier\n" +
                                "\n" +
                                "#--- Patron Ranks (Unused Don't worry about it) ---\n" +
                                "#Assign your ranks to your patrons here\n" +
                                "#So patron0 gets patronRank0 and so on and so forth\n" +
                                "#These names must be the exact same as the ranks above\n" +
                                "patronRank0=Low Tier\n" +
                                "#--- Gain Controls ---\n" +
                                "#You're the audio expert so you know how gain works more than I\n" +
                                "#Please keep the formatting. (Int for the first, Float for the second)\n" +
                                "#This shouldn't need to be changed\n" +
                                "inouts=2\n" +
                                "gain=2.0F");
                writer.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        loadAllTheThings();
        Save.dataList();
    }

    public static void saveAllTheThings()
    {
        Save.properties();
        try
        {
            Save.userList();
            Save.quoteList();
            Save.rankList();
            Save.ranksList();
            Save.permsList();
            Save.commandList();
            Save.commandPermList();
            Save.raffleList();
            Save.dataList();
            Save.subMessages();
            Save.noteList();
            Save.blackList();
            Save.scheduleList();
            Save.songRequestList();

            //Alerts
            Save.alerts();
            Save.chatBubble();
            Save.mod();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void loadAllTheThings()
    {
        Load.ranksList();
        Load.rankList();
        Load.quoteList();
        Load.userList();
        Load.permList();
        Load.commandList();
        Load.commandPermList();
        Load.raffleList();
        Load.noteList();
        Load.blackList();
        Load.steamList();
        Load.scheduleList();
        Load.subMessages();
        Load.songRequestList();
        Load.patronSounds();

        //Alerts
        Load.alerts();
        Load.chatbubble();
        Load.mod();
    }

    private static void bots()
    {

        bots.stream().filter(bot -> bot.getType() != Twirc.BotType.MUSIC).forEach(bot -> {
            bot.addIRCListener(new InfoListener(bot.getType()));
            bot.addIRCListener(new MessageListener(bot.getType()));
            bot.addIRCListener(new UnknownListener(bot.getType()));
            bot.addIRCListener(new Commands(bot.getType()));
            bot.addIRCListener(new Hooks(bot.getType()));
            bot.addIRCListener(new Alerts(bot.getType()));
        });

        whisper.addIRCListener(new WhisperListener(whisper.getType()));
        whisper.addIRCListener(new HooksWhisperer(whisper.getType()));

        bots.forEach(bot -> {
            try
            {
                bot.connect();
            }
            catch (IOException | InterruptedException e)
            {
                e.printStackTrace();
            }
        });
    }
}