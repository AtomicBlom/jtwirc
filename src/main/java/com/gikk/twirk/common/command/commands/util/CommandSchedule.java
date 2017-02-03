package com.gikk.twirk.common.command.commands.util;

import com.gikk.twirk.common.command.CommandBase;
import com.gikk.twirk.common.threads.ScheduleCommon;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;
import com.gikk.twirk.utils.Defaults;
import com.gikk.twirk.utils.MessageSending;
import com.gikk.twirk.utils.jsonclasses.Schedule;
import todo.ChirpBot;

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
                        long quoteSize = (long) ChirpBot.quoteList.size() + 1;
                        while (!messageAdded)
                        {
                            if (ChirpBot.scheduledList.get(quoteSize) == null)
                            {
                                ChirpBot.scheduledList.put(quoteSize, new Schedule(quoteSize, result.toString(), true));
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
                        ChirpBot.saveAllTheThings();
                    }
                }

            }
            else if (args.length == 3)
            {
                if (args[1].equalsIgnoreCase("on") && !args[2].isEmpty())
                {
                    for (long i = 0; i < ChirpBot.scheduledList.size(); i++)
                    {
                        if (Objects.equals(ChirpBot.scheduledList.get(i).getNumber(), Long.valueOf(args[2])))
                        {
                            Schedule schedule = ChirpBot.scheduledList.get(i);
                            schedule.setToggle(true);
                            ChirpBot.scheduledList.replace(i, schedule);
                            ChirpBot.extra.setProperty("scheduleToggle", "true");
                        }
                    }
                }
                if (args[1].equalsIgnoreCase("off") && !args[2].isEmpty())
                {
                    for (long i = 0; i < ChirpBot.scheduledList.size(); i++)
                    {
                        if (Objects.equals(ChirpBot.scheduledList.get(i).getNumber(), Long.valueOf(args[2])))
                        {
                            Schedule schedule = ChirpBot.scheduledList.get(i);
                            schedule.setToggle(false);
                            ChirpBot.scheduledList.replace(i, schedule);
                            ChirpBot.extra.setProperty("scheduleToggle", "false");
                        }
                    }
                }
                if (args[1].equalsIgnoreCase("remove") && !args[2].isEmpty())
                {
                    for (long i = 0; i < ChirpBot.scheduledList.size(); i++)
                    {
                        if (Objects.equals(ChirpBot.scheduledList.get(i).getNumber(), Long.valueOf(args[2])))
                        {
                            ChirpBot.scheduledList.remove(i);
                        }
                    }
                }
            }
        }
    }
}
