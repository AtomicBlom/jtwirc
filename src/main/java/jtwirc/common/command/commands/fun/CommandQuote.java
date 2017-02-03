package jtwirc.common.command.commands.fun;

import com.mb3364.twitch.api.handlers.ChannelResponseHandler;
import com.mb3364.twitch.api.models.Channel;
import jtwirc.TwircBot;
import jtwirc.common.command.CommandBase;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.Defaults;
import jtwirc.utils.MessageSending;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class CommandQuote extends CommandBase
{

    private boolean quoteSend = false;
    private boolean quoteAdded = false;

    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        if (args.length == 1)
        {
            Random rand = new Random();

            long quote = rand.nextInt(TwircBot.quoteList.size()) + 1;
            while (!quoteSend)
            {
                if (TwircBot.quoteList.containsKey(quote))
                {
                    if (TwircBot.quoteList.get(quote) != null)
                    {
                        MessageSending.sendNormalMessage("Quote #" + quote + " : " + TwircBot.quoteList.get(quote));
                        quoteSend = true;
                    }
                }
                else
                {
                    quote += 1;
                    quoteSend = false;
                }
            }
            quoteSend = false;
        }
        else if (args.length == 2)
        {
            if (args[1].equalsIgnoreCase("list"))
            {
                MessageSending.sendNormalMessage("Want to know all the quotes? Go here : http://duskbot.nl/botpages/quotes.php?streamer=" + Defaults.getStreamer());
            }
            else if (TwircBot.quoteList.get(Long.parseLong(args[1])) != null)
            {
                MessageSending.sendNormalMessage("Quote #" + Long.parseLong(args[1]) + " : " + TwircBot.quoteList.get(Long.parseLong(args[1])));
            }
            else
            {
                MessageSending.sendNormalMessage("Quote #" + Long.parseLong(args[1]) + " Doesn't exist.");
            }

        }
        else if (args.length >= 2)
        {
            if (user.isMod() || user.isBroadcaster())
            {
                if (args[1].equalsIgnoreCase("add") && !args[2].isEmpty())
                {
                    try
                    {
                        List<String> quote = new ArrayList<>();
                        quote.addAll(Arrays.asList(args).subList(2, args.length));
                        StringBuilder result = new StringBuilder();
                        for (int i = 0; i < quote.size(); i++)
                        {
                            result.append(quote.get(i));
                            if (i != quote.size() - 1)
                            {
                                result.append(" ");
                            }
                        }
                        final String[] game = new String[1];
                        TwircBot.twitch.channels().get(TwircBot.config.getProperty("autoJoinChannel"), new ChannelResponseHandler()
                        {
                            @Override
                            public void onSuccess(Channel channel)
                            {
                                game[0] = channel.getGame();
                            }

                            @Override
                            public void onFailure(int statusCode, String statusMessage, String errorMessage)
                            {
                                System.out.println(String.format("ERROR %d %s, With message %s", statusCode, statusMessage, errorMessage));
                            }

                            @Override
                            public void onFailure(Throwable e)
                            {
                                e.printStackTrace();
                            }
                        });

                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
                        //get current date time with Date()
                        Date date = new Date();
                        String completeQuote = result.toString() + " ~" + game[0] + " " + dateFormat.format(date);
                        long quoted = (long) TwircBot.quoteList.size() + 1;

                        while (!quoteAdded)
                        {
                            if (!TwircBot.quoteList.containsKey(quoted))
                            {
                                TwircBot.quoteList.put(quoted, completeQuote);
                                quoteAdded = true;
                                MessageSending.sendNormalMessage("Quote has been added as #" + quoted + ".");
                                TwircBot.log.info("Quote " + quoted + " has been added");
                            }
                            else
                            {
                                quoted += 1;
                                quoteAdded = false;
                            }
                        }
                        quoteAdded = false;
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                    finally
                    {
                        TwircBot.saveAllTheThings();
                    }
                }
                else if (args[1].equals("remove"))
                {
                    try
                    {
                        TwircBot.quoteList.remove(Long.parseLong(args[2]));
                        MessageSending.sendNormalMessage("Quote " + args[2] + " removed.");
                        TwircBot.log.info("Quote " + args[2] + " has been removed");
                    }
                    catch (Exception e)
                    {
                        MessageSending.sendNormalMessage("Quote wasn't found");
                    }
                    finally
                    {
                        TwircBot.saveAllTheThings();
                    }
                }
            }
        }
        else
        {
            MessageSending.sendNormalMessage("Correct Args: !quote #/add/remove [args-for-operation]");
        }
    }


}

