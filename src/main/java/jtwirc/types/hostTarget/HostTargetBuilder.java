package jtwirc.types.hostTarget;

import jtwirc.types.twitchMessage.TwitchMessage;

/**
 * Constructs a {@link HostTargetEvent} object. To create a {@link HostTargetEvent} object, call the {@link #build(TwitchMessage)} method
 */
public interface HostTargetBuilder
{

    /**
     * Constructs a new {@link HostTargetEvent} object from a {@link TwitchMessage}, received from Twitch. The user
     * is responsible for making sure that this message can actually be made into a {@link HostTargetEvent} object.
     * Make sure that the COMMAND of the message equals "HOSTTARGET"
     *
     * @param message The message we received from Twitch
     * @return A {@link HostTargetEvent}, or <code>null</code> if a {@link HostTargetEvent} could not be created
     */
    HostTargetEvent build(TwitchMessage message);
}
