package com.gikk.twirk.types.roomstate;

import com.gikk.twirk.types.twitchMessage.TwitchMessage;

/**
 * Constructs a {@link RoomstateEvent} object. To create a {@link RoomstateEvent} object, call the {@link #build(TwitchMessage)} method
 */
public interface RoomstateBuilder
{

    /**
     * Constructs a new {@link RoomstateEvent} object from a {@link TwitchMessage}, received from Twitch. The user
     * is responsible for making sure that this message can actually be made into a {@link RoomstateEvent} object.
     * Make sure that the COMMAND of the message equals "ROOMSTATE"
     *
     * @param message The message we received from Twitch
     * @return A {@link RoomstateEvent}, or <code>null</code> if a {@link RoomstateEvent} could not be created
     */
    RoomstateEvent build(TwitchMessage message);
}
