package jtwirc.common.command.commands.util;

import jtwirc.TwircBot;
import jtwirc.common.command.CommandBase;
import jtwirc.common.threads.ScheduleCommon;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.types.users.TwitchUser;
import jtwirc.utils.Defaults;
import jtwirc.utils.MessageSending;
import jtwirc.utils.jsonclasses.Schedule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CommandSchedule extends CommandBase
{

    // x !schedule add <message>
    //!schedule remove <number>
    // x !schedule toggle
    // x !schedule on <number>
    // x !schedule off <number>

    //Number - Message

    private boolean messageAdded = false;

    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        if (user.isMod() || user.isBroadcaster())
        {
            if (args.length == 2)
            {
                if (args[1].equals("toggle"))
                {
                    if (Defaults.schedule)
                    {
                        Defaults.schedule = false;
                        ScheduleCommon.toggled = false;
                    }
                    else
                    {
                        Defaults.schedule = true;
                        ScheduleCommon.toggled = true;
                    }
                }
            }
            else if (args.length >= 2)
            {
                if (args[1].equals("add") && !args[2].isEmpty())
                {
                    try
                    {
                        List<String> messages = new ArrayList<>();
                        messages.addAll(Arrays.asList(args).subList(2, args.length));
                        StringBuilder result = new StringBuilder();
                        for (int i = 0; i < messages.size(); i++)
                        {
                            result.append(messages.get(i));
                            if (i != messages.size() - 1)
                            {
                                result.append(" ");
                            }
                        }
                        long quoteSize = (long) TwircBot.quoteList.size() + 1;
                        while (!messageAdded)
                        {
                            if (TwircBot.scheduledList.get(quoteSize) == null)
                            {
                                TwircBot.scheduledList.put(quoteSize, new Schedule(quoteSize, result.toString(), true));
                                MessageSending.sendWhisper(user.getName(), "Scheduled message has been added as number " + quoteSize);
                                messageAdded = true;
                            }
                            else
                            {
                                quoteSize += 1;
                                messageAdded = false;
                            }
                        }
                        messageAdded = false;
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

            }
            else if (args.length == 3)
            {
                if (args[1].equalsIgnoreCase("on") && !args[2].isEmpty())
                {
                    for (long i = 0; i < TwircBot.scheduledList.size(); i++)
                    {
                        if (Objects.equals(TwircBot.scheduledList.get(i).getNumber(), Long.valueOf(args[2])))
                        {
                            Schedule schedule = TwircBot.scheduledList.get(i);
                            schedule.setToggle(true);
                            TwircBot.scheduledList.replace(i, schedule);
                            TwircBot.extra.setProperty("scheduleToggle", "true");
                        }
                    }
                }
                if (args[1].equalsIgnoreCase("off") && !args[2].isEmpty())
                {
                    for (long i = 0; i < TwircBot.scheduledList.size(); i++)
                    {
                        if (Objects.equals(TwircBot.scheduledList.get(i).getNumber(), Long.valueOf(args[2])))
                        {
                            Schedule schedule = TwircBot.scheduledList.get(i);
                            schedule.setToggle(false);
                            TwircBot.scheduledList.replace(i, schedule);
                            TwircBot.extra.setProperty("scheduleToggle", "false");
                        }
                    }
                }
                if (args[1].equalsIgnoreCase("remove") && !args[2].isEmpty())
                {
                    for (long i = 0; i < TwircBot.scheduledList.size(); i++)
                    {
                        if (Objects.equals(TwircBot.scheduledList.get(i).getNumber(), Long.valueOf(args[2])))
                        {
                            TwircBot.scheduledList.remove(i);
                        }
                    }
                }
            }
        }
    }
}
