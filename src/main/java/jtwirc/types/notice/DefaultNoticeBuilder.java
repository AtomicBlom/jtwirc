package jtwirc.types.notice;

import jtwirc.enums.NOTICE_EVENT;
import jtwirc.types.twitchMessage.TwitchMessage;

public class DefaultNoticeBuilder implements NoticeBuilder
{
    NOTICE_EVENT event;
    String message;
    String rawLine;
    String rawEvent;

    @Override
    public NoticeEvent build(TwitchMessage message)
    {
        //The event is posted after the '@msg-id=' part of the tag
        this.rawEvent = message.getTag().substring(message.getTag().indexOf('=') + 1);
        this.event = parseEvent(rawEvent);
        this.message = message.getContent();
        this.rawLine = message.getRaw();

        return new NoticeImpl(this);
    }

    private NOTICE_EVENT parseEvent(String event)
    {
        switch (event)
        {
            case "subs_on":
                return NOTICE_EVENT.SUB_MODE_ON;
            case "subs_off":
                return NOTICE_EVENT.SUB_MODE_OFF;
            case "slow_on":
                return NOTICE_EVENT.SLOW_MODE_ON;
            case "slow_off":
                return NOTICE_EVENT.SLOW_MODE_OFF;
            case "r9k_on":
                return NOTICE_EVENT.R9K_MODE_ON;
            case "r9k_off":
                return NOTICE_EVENT.R9K_MODE_OFF;
            case "host_on":
                return NOTICE_EVENT.HOST_MODE_ON;
            case "host_off":
                return NOTICE_EVENT.HOST_MODE_OFF;
            default:
                return NOTICE_EVENT.OTHER;
        }
    }

}
