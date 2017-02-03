package jtwirc.common.module;

import jtwirc.Twirk.BotType;
import jtwirc.events.TwirkListenerBaseImpl;
import jtwirc.types.notice.NoticeEvent;
import jtwirc.utils.MessageSending;

public class HooksWhisperer extends TwirkListenerBaseImpl
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
