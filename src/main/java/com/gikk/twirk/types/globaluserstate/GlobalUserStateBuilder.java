package com.gikk.twirk.types.globaluserstate;

import com.gikk.twirk.types.twitchMessage.TwitchMessage;

/**
 * Constructs a {@link GlobalUserStateEvent} object. To create a {@link GlobalUserStateEvent} object, call the {@link #build(TwitchMessage)} method
 */
public interface GlobalUserStateBuilder
{

    /**
     * Constructs a new {@link GlobalUserStateEvent} object from a {@link TwitchMessage}, received from Twitch. The user
     * is responsible for making sure that this message can actually be made into a {@link GlobalUserStateEvent} object.
     * Make sure that the COMMAND of the message equals "USERNOTICE"
     *
     * @param message The message we received from Twitch
     * @return A {@link GlobalUserStateEvent}, or <code>null</code> if a {@link GlobalUserStateEvent} could not be created
     */
    GlobalUserStateEvent build(TwitchMessage message);
}
