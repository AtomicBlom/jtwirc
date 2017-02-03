package jtwirc.types.usernotice;

import jtwirc.types.AbstractTwitchUserFields;
import jtwirc.types.emote.Emote;
import jtwirc.types.twitchMessage.TwitchMessage;
import jtwirc.utils.ParsingUtil;

import java.util.List;

public class DefaultUsernoticeBuilder extends AbstractTwitchUserFields implements UsernoticeBuilder
{
    private final static String LOGIN_IDENTIFIER = "login=";
    private final static String MESSAGE_ID_IDENTIFIER = "msg-id=";
    private final static String SYSTEM_MESSAGE_IDENTIFIER = "system-msg=";
    private final static String MONTHS_IDENTIFIER = "msg-param-months=";


    String loginName;
    List<Emote> emotes;
    boolean hasEmotes;
    int months;
    String subMessage;
    @SuppressWarnings("WeakerAccess")
    String messageID;
    String systemMessage;

    @Override
    public UserNoticeEvent build(TwitchMessage message)
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

        return new UsernoticeImpl(this);
    }
}
