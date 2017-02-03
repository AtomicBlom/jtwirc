package com.gikk.twirk.types.mode;

import com.gikk.twirk.types.twitchMessage.TwitchMessage;

/**
 * Constructs a {@link ModeEvent} object. To create a {@link ModeEvent} object, call the {@link #build(TwitchMessage)} method
 */
public interface ModeBuilder
{

    /**
     * Constructs a new {@link ModeEvent} object from a {@link TwitchMessage}, received from Twitch. The user
     * is responsible for making sure that this message can actually be made into a {@link ModeEvent} object.
     * Make sure that the COMMAND of the message equals "MODE"
     *
     * @param message The message we received from Twitch
     * @return A {@link ModeEvent}, or <code>null</code> if a {@link ModeEvent} could not be created
     */
    ModeEvent build(TwitchMessage message);
}
