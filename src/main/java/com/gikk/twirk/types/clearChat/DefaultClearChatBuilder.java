package com.gikk.twirk.types.clearChat;

import com.gikk.twirk.enums.CLEARCHAT_MODE;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.utils.ParsingUtil;

public class DefaultClearChatBuilder implements ClearChatBuilder
{
    private static String DURATION_TAG = "ban-duration=";
    private static String REASON_TAG = "ban-reason=";

    CLEARCHAT_MODE mode;
    String target = "";
    int duration = -1;
    String reason = "";
    String rawLine;

    @Override
    public ClearChatEvent build(TwitchMessage twitchMessage)
    {
        this.rawLine = twitchMessage.getRaw();

        if (twitchMessage.getContent().isEmpty())
        {
            this.mode = CLEARCHAT_MODE.COMPLETE;
            this.target = "";
        }
        else
        {
            this.mode = CLEARCHAT_MODE.USER;
            this.target = twitchMessage.getContent();

            String temp = ParsingUtil.parseString(DURATION_TAG, twitchMessage.getTag());
            this.duration = temp.isEmpty() ? -1 : Integer.parseInt(temp);

            temp = ParsingUtil.parseString(REASON_TAG, twitchMessage.getTag());
            this.reason = temp.replace("\\s", " ");
        }

        return new ClearChatImpl(this);
    }
}
