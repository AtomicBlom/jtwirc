package com.gikk.twirk.types.action;

import com.gikk.twirk.types.twitchMessage.TwitchMessage;

/**
 * Constructs a {@link ActionEvent} object. To create a {@link ActionEvent} object, call the {@link #build(TwitchMessage)} method
 */
public interface ActionBuilder
{

    /**
     * Constructs a new {@link ActionEvent} object from a {@link TwitchMessage}, received from Twitch. The user
     * is responsible for making sure that this message can actually be made into a {@link ActionEvent} object.
     * Make sure that the COMMAND of the message equals "ACTION"
     *
     * @param message The message we received from Twitch
     * @return A {@link ActionEvent}, or <code>null</code> if a {@link ActionEvent} could not be created
     */
    ActionEvent build(TwitchMessage message);
}
