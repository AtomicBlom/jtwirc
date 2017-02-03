package jtwirc.common.command.commands.fun;

import jtwirc.TwircBot;
import jtwirc.common.command.CommandBase;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.Defaults;
import jtwirc.utils.MessageSending;
import jtwirc.utils.json.Save;
import jtwirc.utils.json.Upload;

import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.Set;

@SuppressWarnings("ConstantConditions")
public class CommandRaffle extends CommandBase
{

    private Random rand = new Random();

    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        if (args.length == 1)
        {
            if (user.isMod() || user.isBroadcaster())
            {
                drawRaffle();
            }
        }
        else if (args.length == 2)
        {
            if (args[1].equalsIgnoreCase("buy"))
            {
                buyTickets(user, (long) 1);
            }
            if (args[1].equalsIgnoreCase("clear"))
            {
                if (user.isMod() || user.isBroadcaster())
                {
                    TwircBot.raffleList.clear();
                    try
                    {
                        Save.raffleList();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    Upload.uploadDataFiles();
                }
            }
            if (args[1].equalsIgnoreCase("check"))
            {
                MessageSending.sendWhisper(user.getName().toLowerCase(), "You have " + getRaffleTickets(user.getName()) + " tickets");
            }
            if (args[1].equalsIgnoreCase("price"))
            {
                MessageSending.sendWhisper(user.getName().toLowerCase(), " The tickets cost " + Defaults.raffleCost + " " + Defaults.getPointName() + " each.");
            }
            if (args[1].equalsIgnoreCase("total"))
            {
                MessageSending.sendNormalMessage("Currently there have been " + TwircBot.raffleList.size() + " ticket(s) sold.");
            }
            if (args[1].equalsIgnoreCase("list"))
            {
                MessageSending.sendNormalMessage("You can check the amount of tickets sold, and how many tickets someone has on this website : http://duskbot.nl/botpages/raffle.php?streamer=" + Defaults.getStreamer());
            }
        }
        else if (args.length == 3)
        {
            if (args[1].equalsIgnoreCase("buy"))
            {
                if (Long.valueOf(args[2]) > 0)
                {
                    buyTickets(user, Long.valueOf(args[2]));
                }
                else
                {
                    MessageSending.sendWhisper(user.getName(), "Buying negative tickets won't work...");
                }

            }
            if (args[1].equalsIgnoreCase("set"))
            {
                if (user.isMod() || user.isBroadcaster())
                {
                    Defaults.setRaffleCost(Integer.valueOf(args[2]));
                    MessageSending.sendNormalMessage("Raffle tickets now costs " + Defaults.raffleCost + " " + Defaults.getPointName());
                    try
                    {
                        Save.dataList();
                        Save.raffleList();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    Upload.uploadDataFiles();
                }
            }
        }
    }

    private void drawRaffle()
    {
        int size = TwircBot.raffleList.size();
        int randomnumber = rand.nextInt((size - 1) + 1) + 1;
        String user = TwircBot.raffleList.get((long) randomnumber);

        MessageSending.sendNormalMessage(user + " you have won the raffle, the draw ID was " + randomnumber);

        TwircBot.raffleList.clear();
        try
        {
            Save.dataList();
            Save.raffleList();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        Upload.uploadDataFiles();
    }

    private void buyTickets(TwitchUser user, Long amount)
    {
        Long totalCost = amount * Defaults.raffleCost;
        Long points = TwircBot.userList.get(user.getName());
        if (points >= totalCost)
        {
            Long size = (long) TwircBot.raffleList.size();
            for (int i = 1; i <= amount; i++)
            {
                TwircBot.raffleList.put(size + i, user.getName());
            }
            TwircBot.userList.put(user.getName(), TwircBot.userList.get(user.getName()) - totalCost);
            MessageSending.sendNormalMessage(user.getName() + " just bought " + amount + " tickets.");
            try
            {
                Save.raffleList();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            Upload.uploadDataFiles();
        }
        else
        {
            MessageSending.sendWhisper(user.getName(), "You don't have enough " + Defaults.getPointName() + " to buy " + amount + " tickets. The most you can buy is " + Math.round(points / Defaults.raffleCost));
        }
    }

    private int getRaffleTickets(String user)
    {
        int tickets = 0;

        Set<Map.Entry<Long, String>> mapSet = TwircBot.raffleList.entrySet();
        for (Map.Entry<Long, String> mapEntry : mapSet)
        {
            if (mapEntry.getValue().equals(user))
            {
                tickets++;
            }
        }

        return tickets;
    }
}
