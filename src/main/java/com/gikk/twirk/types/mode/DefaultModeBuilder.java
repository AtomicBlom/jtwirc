package com.gikk.twirk.types.mode;

import com.gikk.twirk.types.twitchMessage.TwitchMessage;

public class DefaultModeBuilder implements ModeBuilder
{
    ModeEvent.MODE_EVENT event;
    String user;
    String rawLine;

    @Override
    public ModeEvent build(TwitchMessage message)
    {
        /* Mode events can have two different layouts:
		 * 	
		 * 	> :jtv MODE #channel +o operator_user		- Gained Mod
		 *	> :jtv MODE #channel -o operator_user		- Lost Mod
		 * 
		 * So we simply look at the content part to determine which user is affected
		 * and what event occurred
		 */
        this.rawLine = message.getRaw();
        String content = message.getContent();
        this.event = content.startsWith("+o") ? ModeEvent.MODE_EVENT.GAINED_MOD : ModeEvent.MODE_EVENT.LOST_MOD;
        this.user = content.substring(content.indexOf(' ') + 1);

        return new ModeImpl(this);
    }
}
