package com.gikk.twirk.common.command.commands.fun;

import com.gikk.twirk.common.command.CommandBase;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;
import com.gikk.twirk.utils.Defaults;
import com.gikk.twirk.utils.MessageSending;
import org.apache.commons.lang3.text.WordUtils;
import todo.ChirpBot;

public class CommandRanks extends CommandBase
{

    /*public static String getRankList() {
        String rankListing = "";
        Set<Map.Entry<String, Long>> mapSet = ChirpBot.rankList.entrySet();
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
            if (ChirpBot.rankUserList.get(user.getName().toLowerCase()) != null)
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
                ChirpBot.log.info(user.getName() + "sold his rank.");
                ChirpBot.saveAllTheThings();
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
                ChirpBot.log.info(user + "bought " + args[2]);
                ChirpBot.saveAllTheThings();
            }
            if (args[1].equalsIgnoreCase("remove"))
            {
                if (user.isMod() || user.isBroadcaster())
                {
                    removeRank(removeUnderscores(args[2]), user);
                    ChirpBot.log.info(args[2] + " has got their rank removed.");
                    ChirpBot.saveAllTheThings();
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
                    ChirpBot.log.info(args[2] + " received a special rank " + args[3]);
                    ChirpBot.saveAllTheThings();
                }
            }
            if (args[1].equalsIgnoreCase("add"))
            {
                if (user.isMod() || user.isBroadcaster())
                {
                    addRank(removeUnderscores(args[2]), Long.parseLong(args[3]), user);
                    ChirpBot.log.info("A new rank was added: " + args[2]);
                    ChirpBot.saveAllTheThings();
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
        ChirpBot.rankUserList.put(user, rank);
    }

    private void addRank(String rank, Long amount, TwitchUser user)
    {
        if (ChirpBot.rankList.get(rank) == null)
        {
            ChirpBot.rankList.put(rank, amount);
            //MessageSending.sendMessageWithPrefix(user.getName() + " added " + rank, user);
            ChirpBot.saveAllTheThings();
        }
        else
        {
            //MessageSending.sendMessageWithPrefix(user.getName() + " this rank already exists.", user);
        }
    }

    private void removeRank(String rank, TwitchUser user)
    {
        if (ChirpBot.rankList.get(rank) == null)
        {
            //MessageSending.sendMessageWithPrefix(user.getName() + " this rank doesn't exist", user);
        }
        else
        {
            ChirpBot.rankList.remove(rank);
            //MessageSending.sendMessageWithPrefix(user.getName() + " " + rank + " removed", user);
            ChirpBot.saveAllTheThings();
        }
    }

    private void buyRank(String rank, TwitchUser user)
    {
        try
        {
            Long points = ChirpBot.userList.get(user.getName().toLowerCase());
            Long rankCost = ChirpBot.rankList.get(rank);
            if (ChirpBot.rankList.get(rank) != null)
            {
                if (points >= rankCost)
                {
                    ChirpBot.userList.put(user.getName().toLowerCase(), ChirpBot.userList.get(user.getName().toLowerCase()) - rankCost);
                    ChirpBot.rankUserList.put(user.getName().toLowerCase(), rank);
                    //MessageSending.sendMessageWithPrefix(user.getName() + " successfully bought " + rank, user);
                    ChirpBot.saveAllTheThings();
                }
                else
                {
                    //MessageSending.sendMessageWithPrefix(user.getName() + " you do not have enough " + Defaults.getPointName(), user);
                }
            }
            else
            {
                System.out.println(rank);
                System.out.println(ChirpBot.rankList.get(rank));
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
            Long rankCost = ChirpBot.rankList.get(getRank(user.getName().toLowerCase()));
            Long points = ChirpBot.userList.get(user.getName().toLowerCase());
            if (ChirpBot.rankUserList.get(user.getName().toLowerCase()) != null)
            {
                ChirpBot.rankUserList.remove(user.getName().toLowerCase());
                ChirpBot.userList.put(user.getName().toLowerCase(), (points + (rankCost / 2)));
                //MessageSending.sendMessageWithPrefix(user.getName() + " you have successfully sold your rank. But you only received half of your " + Defaults.getPointName() + " back, because inflation.", user);
                ChirpBot.saveAllTheThings();
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
        return ChirpBot.rankUserList.get(user);
    }
}
