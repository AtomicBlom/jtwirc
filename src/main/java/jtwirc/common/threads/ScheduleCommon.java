package jtwirc.common.threads;

import jtwirc.annotation.Unfinished;
import jtwirc.todo.ChirpBot;
import jtwirc.utils.Defaults;
import jtwirc.utils.MessageSending;
import jtwirc.utils.jsonclasses.Schedule;

@Unfinished(value = "Need to find a way to get the TwitchMessage in here or we can just assume 'safely' that we want #WeAllPlayCast")
public class ScheduleCommon
{


    public static boolean toggled = true;
    private static int interval = 5 * 60;
    private static int ms = 1000;
    private static int time = interval * ms;
    private static long message = 0;
    public static Thread scheduleMessages = new Thread("ScheduleCommon")
    {
        @Override
        public void run()
        {
            //noinspection InfiniteLoopStatement
            while (true)
            {
                if (Defaults.schedule)
                {
                    try
                    {
                        Thread.sleep(time);
                        if (toggled)
                        {
                            Schedule scheduleMessage = ChirpBot.scheduledList.get(getMessageNumber());
                            if (scheduleMessage.getToggle())
                            {
                                MessageSending.sendNormalMessage(scheduleMessage.getMessage());
                            }
                            message++;
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    };


    private static Long getMessageNumber()
    {
        Long messageCount = (long) ChirpBot.scheduledList.size();
        if (messageCount == 0)
        { //2
            Defaults.schedule = false;
            //MessageSending.sendNormalMessage("There are no messages scheduled. Please add these messages");
        }

        while (ChirpBot.scheduledList.get(message) == null || !ChirpBot.scheduledList.get(message).getToggle())
        {
            message++;
            if (message >= messageCount)
            {
                message = 0;
            }
        }
        return message;
    }
}
