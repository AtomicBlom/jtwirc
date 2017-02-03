package com.gikk.twirk.types.globaluserstate;

import com.gikk.twirk.enums.USER_TYPE;
import com.gikk.twirk.types.AbstractType;
import com.gikk.twirk.types.emote.Emote;

import java.util.List;

public interface GlobalUserStateEvent extends AbstractType
{

    /**
     * Retrieves our display color, as a hex-number. <br>
     * If we have no color set on Twitch's side, a semi-random color will be generated
     *
     * @return Our display color
     */
    int getColor();

    String getDisplayName();

    int getUserID();

    USER_TYPE getUserType();

    /**
     * Retrieves the emote sets that are available to this account. You can uses the emote set's names to request information
     * about those emotes from Twitch.<br><br>
     * <p>
     * For example, if we have emote set 0, we can request information about the emotes in that set by visiting:<br>
     * <a href="https://api.twitch.tv/kraken/chat/emoticon_images?emotesets=0">https://api.twitch.tv/kraken/chat/emoticon_images?emotesets=0</a>
     * A request to that adress will return a JSON file with all emotes in that set.<br><br>
     * <p>
     * You can request information about several sets at once, by listing emote sets after one another separated by a comma ( , )<br>
     * For example: <a href="https://api.twitch.tv/kraken/chat/emoticon_images?emotesets=0,12">https://api.twitch.tv/kraken/chat/emoticon_images?emotesets=0,12</a>
     *
     * @return Our emote sets
     */
    List<Emote> getEmoteSets();
}
