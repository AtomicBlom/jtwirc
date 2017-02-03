package jtwirc.common.command.commands.fun;

import jtwirc.TwircBot;
import jtwirc.common.command.CommandBase;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.Defaults;
import jtwirc.utils.MessageSending;
import org.apache.commons.lang3.text.WordUtils;

public class CommandRanks extends CommandBase
{

    /*public static String getRankList() {
        String rankListing = "";
        Set<Map.Entry<String, Long>> mapSet = TwircBot.rankList.entrySet();
        Iterator<Map.Entry<String, Long>> mapIterator = mapSet.iterator();
        while (mapIterator.hasNext()) {
            Map.Entry<String, Long> mapEntry = mapIterator.next();
            String key = mapEntry.getKey();
            Long value = mapEntry.getValue();
            rankListing += ("Rank : " + key + " | Costs : " + value) + "\n";
        }
        return rankListing;
    }*/

    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        if (args.length == 1)
        {
            if (TwircBot.rankUserList.get(user.getName().toLowerCase()) != null)
            {
                //MessageSending.sendMessageWithPrefix(user.getName() + " is " + getRank(user.getName().toLowerCase()) + "!", user);
            }
            else
            {
                //MessageSending.sendMessageWithPrefix(user.getName() + " has no rank.", user);
            }
        }
        else if (args.length == 2)
        {
            if (args[1].equalsIgnoreCase("sell"))
            {
                sellRank(user);
                TwircBot.log.info(user.getName() + "sold his rank.");
                TwircBot.saveAllTheThings();
            }
            if (args[1].equalsIgnoreCase("list"))
            {
                MessageSending.sendNormalMessage("The ranks you can buy, check them here : http://duskbot.nl/botpages/rankList.php?streamer=" + Defaults.getStreamer());
            }
        }
        else if (args.length == 3)
        {
            if (args[1].equalsIgnoreCase("buy"))
            {
                buyRank(removeUnderscores(args[2]), user);
                TwircBot.log.info(user + "bought " + args[2]);
                TwircBot.saveAllTheThings();
            }
            if (args[1].equalsIgnoreCase("remove"))
            {
                if (user.isMod() || user.isBroadcaster())
                {
                    removeRank(removeUnderscores(args[2]), user);
                    TwircBot.log.info(args[2] + " has got their rank removed.");
                    TwircBot.saveAllTheThings();
                }
            }
        }
        else if (args.length > 3)
        {
            if (args[1].equalsIgnoreCase("give"))
            {
                if (user.isBroadcaster())
                {
                    giveRank(args[2].toLowerCase(), removeUnderscores(args[3]));
                    TwircBot.log.info(args[2] + " received a special rank " + args[3]);
                    TwircBot.saveAllTheThings();
                }
            }
            if (args[1].equalsIgnoreCase("add"))
            {
                if (user.isMod() || user.isBroadcaster())
                {
                    addRank(removeUnderscores(args[2]), Long.parseLong(args[3]), user);
                    TwircBot.log.info("A new rank was added: " + args[2]);
                    TwircBot.saveAllTheThings();
                }
            }

        }
        else
        {
            //MessageSending.sendMessageWithPrefix("Correct Args: !rank &lt;operation&gt; [args-for-operation]", user);
        }

    }

    private String removeUnderscores(String arg)
    {
        String newName;
        newName = arg.replace("_", " ");
        WordUtils.capitalize(newName);
        return newName;
    }

    private void giveRank(String user, String rank)
    {
        TwircBot.rankUserList.put(user, rank);
    }

    private void addRank(String rank, Long amount, TwitchUser user)
    {
        if (TwircBot.rankList.get(rank) == null)
        {
            TwircBot.rankList.put(rank, amount);
            //MessageSending.sendMessageWithPrefix(user.getName() + " added " + rank, user);
            TwircBot.saveAllTheThings();
        }
        else
        {
            //MessageSending.sendMessageWithPrefix(user.getName() + " this rank already exists.", user);
        }
    }

    private void removeRank(String rank, TwitchUser user)
    {
        if (TwircBot.rankList.get(rank) == null)
        {
            //MessageSending.sendMessageWithPrefix(user.getName() + " this rank doesn't exist", user);
        }
        else
        {
            TwircBot.rankList.remove(rank);
            //MessageSending.sendMessageWithPrefix(user.getName() + " " + rank + " removed", user);
            TwircBot.saveAllTheThings();
        }
    }

    private void buyRank(String rank, TwitchUser user)
    {
        try
        {
            Long points = TwircBot.userList.get(user.getName().toLowerCase());
            Long rankCost = TwircBot.rankList.get(rank);
            if (TwircBot.rankList.get(rank) != null)
            {
                if (points >= rankCost)
                {
                    TwircBot.userList.put(user.getName().toLowerCase(), TwircBot.userList.get(user.getName().toLowerCase()) - rankCost);
                    TwircBot.rankUserList.put(user.getName().toLowerCase(), rank);
                    //MessageSending.sendMessageWithPrefix(user.getName() + " successfully bought " + rank, user);
                    TwircBot.saveAllTheThings();
                }
                else
                {
                    //MessageSending.sendMessageWithPrefix(user.getName() + " you do not have enough " + Defaults.getPointName(), user);
                }
            }
            else
            {
                System.out.println(rank);
                System.out.println(TwircBot.rankList.get(rank));
                //MessageSending.sendMessageWithPrefix(user.getName() + " this rank doesn't exist.", user);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void sellRank(TwitchUser user)
    {
        try
        {
            Long rankCost = TwircBot.rankList.get(getRank(user.getName().toLowerCase()));
            Long points = TwircBot.userList.get(user.getName().toLowerCase());
            if (TwircBot.rankUserList.get(user.getName().toLowerCase()) != null)
            {
                TwircBot.rankUserList.remove(user.getName().toLowerCase());
                TwircBot.userList.put(user.getName().toLowerCase(), (points + (rankCost / 2)));
                //MessageSending.sendMessageWithPrefix(user.getName() + " you have successfully sold your rank. But you only received half of your " + Defaults.getPointName() + " back, because inflation.", user);
                TwircBot.saveAllTheThings();
            }
            else
            {
                //MessageSending.sendMessageWithPrefix(user.getName() + " you either have no rank, or you have a unsellable rank.", user);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private String getRank(String user)
    {
        return TwircBot.rankUserList.get(user);
    }
}
