package jtwirc.common.module;

import jtwirc.Twirc.BotType;
import jtwirc.events.TwircListenerBaseImpl;
import jtwirc.types.notice.NoticeEvent;
import jtwirc.utils.MessageSending;

public class HooksWhisperer extends TwircListenerBaseImpl
{

    private BotType type;

    public HooksWhisperer(BotType type)
    {
        this.type = type;
    }

    @Override
    public void onNotice(NoticeEvent event)
    {
        if (type == BotType.WHISPER)
        {
            if (event.getMessage().contains("That user"))
            {
                MessageSending.sendNormalMessage("If you're not getting a response from the bot, you will either need to follow the bot or change your settings here http://www.twitch.tv/settings/security .");
            }
        }
    }
}
