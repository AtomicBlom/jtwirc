package com.gikk.twirk.types.users;

import com.gikk.twirk.types.twitchMessage.TwitchMessage;

/**
 * Constructs a {@link UserStateEvent} object. To create a {@link UserStateEvent} object, call the {@link #build(TwitchMessage)} method
 */
public interface UserstateBuilder
{

    /**
     * Constructs a new {@link UserStateEvent} object from a {@link TwitchMessage}, received from Twitch. The user
     * is responsible for making sure that this message can actually be made into a {@link UserStateEvent} object.
     * Make sure that the COMMAND equals "USERSTATE"
     *
     * @param message The message we received from Twitch
     * @return A {@link UserStateEvent}, or <code>null</code> if a {@link UserStateEvent} could not be created
     */
    UserStateEvent build(TwitchMessage message);
}
