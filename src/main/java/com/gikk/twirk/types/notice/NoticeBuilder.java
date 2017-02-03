package com.gikk.twirk.types.notice;

import com.gikk.twirk.types.twitchMessage.TwitchMessage;

/**
 * Constructs a {@link NoticeEvent} object. To create a {@link NoticeEvent} object, call the {@link #build(TwitchMessage)} method
 */
public interface NoticeBuilder
{

    /**
     * Constructs a new {@link NoticeEvent} object from a {@link TwitchMessage}, received from Twitch. The user
     * is responsible for making sure that this message can actually be made into a {@link NoticeEvent} object.
     * Make sure that the COMMAND of the message equals "NOTICE"
     *
     * @param message The message we received from Twitch
     * @return A {@link NoticeEvent}, or <code>null</code> if a {@link NoticeEvent} could not be created
     */
    NoticeEvent build(TwitchMessage message);
}
