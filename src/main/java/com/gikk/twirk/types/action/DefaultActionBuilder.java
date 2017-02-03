package com.gikk.twirk.types.action;

import com.gikk.twirk.types.AbstractTwitchUserFields;
import com.gikk.twirk.types.emote.Emote;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.utils.ParsingUtil;

import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class DefaultActionBuilder extends AbstractTwitchUserFields implements ActionBuilder
{

    private final static String LOGIN_IDENTIFIER = "login=";
    private final static String MESSAGE_ID_IDENTIFIER = "msg-id=";
    private final static String SYSTEM_MESSAGE_IDENTIFIER = "system-msg=";
    private final static String MONTHS_IDENTIFIER = "msg-param-months=";


    String loginName;
    List<Emote> emotes;
    boolean hasEmotes;
    private int months;
    private String subMessage;
    private String messageID;
    private String systemMessage;


    @Override
    public ActionEvent build(TwitchMessage message)
    {
        parseUserProperties(message);

        String tag = message.getTag(), temp;
        this.hasEmotes = message.hasEmotes();
        this.emotes = message.getEmotes();
        this.subMessage = message.getContent();
        this.loginName = ParsingUtil.parseString(LOGIN_IDENTIFIER, tag);
        this.messageID = ParsingUtil.parseString(MESSAGE_ID_IDENTIFIER, tag);
        this.systemMessage = ParsingUtil.parseString(SYSTEM_MESSAGE_IDENTIFIER, tag).replace("\\s", " ");

        temp = ParsingUtil.parseString(MONTHS_IDENTIFIER, tag);
        this.months = temp.isEmpty() ? 0 : Integer.parseInt(temp);

        return new ActionImpl(this);
    }
}
