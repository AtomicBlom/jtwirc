package com.gikk.twirk.common.module;

import com.gikk.twirk.Twirk.BotType;
import com.gikk.twirk.events.TwirkListenerBaseImpl;
import com.gikk.twirk.types.notice.NoticeEvent;
import com.gikk.twirk.utils.MessageSending;

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
