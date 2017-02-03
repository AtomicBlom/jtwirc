package com.gikk.twirk.types.usernotice;

import com.gikk.twirk.types.twitchMessage.TwitchMessage;

/**
 * Constructs a {@link UserNoticeEvent} object. To create a {@link UserNoticeEvent} object, call the {@link #build(TwitchMessage)} method
 */
public interface UsernoticeBuilder
{

    /**
     * Constructs a new {@link UserNoticeEvent} object from a {@link TwitchMessage}, received from Twitch. The user
     * is responsible for making sure that this message can actually be made into a {@link UserNoticeEvent} object.
     * Make sure that the COMMAND of the message equals "USERNOTICE"
     *
     * @param message The message we received from Twitch
     * @return A {@link UserNoticeEvent}, or <code>null</code> if a {@link UserNoticeEvent} could not be created
     */
    UserNoticeEvent build(TwitchMessage message);
}
