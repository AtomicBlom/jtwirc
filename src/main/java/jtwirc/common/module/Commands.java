package jtwirc.common.module;

import jtwirc.Twirc;
import jtwirc.TwircBot;
import jtwirc.common.command.CommandBase;
import jtwirc.common.command.commands.faq.*;
import jtwirc.common.command.commands.fun.CommandQuote;
import jtwirc.common.command.commands.fun.CommandRaffle;
import jtwirc.common.command.commands.fun.CommandStrawpoll;
import jtwirc.common.command.commands.fun.CommandWinner;
import jtwirc.common.command.commands.partner.CommandFreeSub;
import jtwirc.common.command.commands.partner.CommandNewSub;
import jtwirc.common.command.commands.partner.CommandReSub;
import jtwirc.common.command.commands.util.*;
import jtwirc.common.command.utils.AddCommands;
import jtwirc.common.command.utils.DeleteCommands;
import jtwirc.common.command.utils.EditCommands;
import jtwirc.events.TwircListenerBaseImpl;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.Defaults;
import jtwirc.utils.MessageSending;

import java.util.HashMap;

public class Commands extends TwircListenerBaseImpl
{
    public HashMap<String, CommandBase> commands = new HashMap<>();

    private Twirc.BotType type;

    public Commands(Twirc.BotType type)
    {
        commands.clear();
        String points = Defaults.getPointName();
        commands.put("!" + points, new CommandPoints());
        //commands.put("rank", new CommandRanks());
        commands.put("!quote", new CommandQuote());
        commands.put("!addcommand", new AddCommands());
        commands.put("!delcommand", new DeleteCommands());
        commands.put("!editcommand", new EditCommands());
        commands.put("!timer", new CommandTimer());
        commands.put("!note", new CommandNote());
        commands.put("!winner", new CommandWinner());
        commands.put("!raffle", new CommandRaffle());
        commands.put("!strawpoll", new CommandStrawpoll());
        commands.put("!ctt", new CommandCTT());
        commands.put("!schedule", new CommandSchedule());
        commands.put("!viewers", new CommandViewers());
        commands.put("!chatters", new CommandChatters());
        commands.put("!blacklist", new CommandBlacklist());
        commands.put("!whitelist", new CommandWhitelist());
        commands.put("!chirpbot", new CommandChirpBot());
        commands.put("!changelog", new CommandChangelog());
        commands.put("!save", new CommandSave());
        commands.put("!so", new CommandShoutout());
        commands.put("!exit", new CommandExit());
        commands.put("!togglestream", new CommandToggleStream());
        commands.put("!issues", new CommandIssues());
        commands.put("!caster", new CommandCaster());
        commands.put("!status", new CommandStatus());
        commands.put("!title", new CommandStatus());
        commands.put("!uptime", new CommandUptime());
        commands.put("!total", new CommandTotal());
        commands.put("!game", new CommandGame());
        commands.put("!togglelinks", new CommandToggleLinks());
        commands.put("!togglecaps", new CommandToggleCaps());
        commands.put("!togglewot", new CommandToggleWOT());
        commands.put("!newsub", new CommandNewSub());
        commands.put("!oldsub", new CommandReSub());
        commands.put("!freesub", new CommandFreeSub());

        this.type = type;
    }

    private static String getResponseFromCommand(TwitchUser user, TwitchMessage message, String command)
    {
        String response = TwircBot.commandList.get(command);
        if (message.getContent().split(" ").length > 1)
        {
            response = response.replace("%user%", message.getContent().split(" ")[1]); //!so %user%
        }

        return response;
    }

    private static String parseCommandFromMSG(TwitchMessage message)
    {
        return message.getContent().split(" ")[0];
    }

    @Override
    public void onPrivMsg(TwitchUser user, TwitchMessage message)
    {
        super.onPrivMsg(user, message);
        if (TwircBot.commandList.containsKey(message.getContent().split(" ")[0]))
        {
            String userPerm = TwircBot.permList.get(user.getName().toLowerCase());
            String commandPerm = TwircBot.commandpermList.get(parseCommandFromMSG(message));
            if (commandPerm == null)
            {
                commandPerm = "null";
            }
            switch (commandPerm)
            {
                case "reg":
                    if (user.isMod() || user.isBroadcaster())
                    {
                        MessageSending.sendNormalMessage(getResponseFromCommand(user, message, parseCommandFromMSG(message)));
                    }
                    else
                    {
                        MessageSending.sendWhisper(user.getName().toLowerCase(), "You do not have enough permission to use this command.");
                    }
                    break;
                case "Moderator":
                    if (user.isMod() || user.isBroadcaster())
                    {
                        MessageSending.sendNormalMessage(getResponseFromCommand(user, message, parseCommandFromMSG(message)));
                    }
                    else
                    {
                        MessageSending.sendWhisper(user.getName().toLowerCase(), "You do not have enough permission to use this command.");
                    }
                    break;
                case "mod":
                    if (user.isMod() || user.isBroadcaster())
                    {
                        MessageSending.sendNormalMessage(getResponseFromCommand(user, message, parseCommandFromMSG(message)));
                    }
                    else
                    {
                        MessageSending.sendWhisper(user.getName().toLowerCase(), "You do not have enough permission to use this command.");
                    }
                    break;
                case "null":
                    MessageSending.sendNormalMessage(getResponseFromCommand(user, message, parseCommandFromMSG(message)));
                    break;
                default:
                    MessageSending.sendNormalMessage(getResponseFromCommand(user, message, parseCommandFromMSG(message)));
            }
        }

        for (String command : commands.keySet())
        {
            if (command.equalsIgnoreCase(parseCommandFromMSG(message)))
            {
                commands.get(command).channelCommand(user, message);
            }
        }
    }
}
