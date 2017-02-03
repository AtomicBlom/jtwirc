package jtwirc.types.clearChat;

import jtwirc.types.twitchMessage.TwitchMessage;

/**
 * Constructs a {@link ClearChatEvent} object. To create a {@link ClearChatEvent} object, call the {@link #build(TwitchMessage)} method
 */
public interface ClearChatBuilder
{

    /**
     * Constructs a new {@link ClearChatEvent} object from a {@link TwitchMessage}, received from Twitch. The user
     * is responsible for making sure that this message can actually be made into a {@link ClearChatEvent} object.
     * Make sure that the COMMAND of the message equals "CLEARCHAT"
     *
     * @param message The message we received from Twitch
     * @return A {@link ClearChatEvent}, or <code>null</code> if a {@link ClearChatEvent} could not be created
     */
    ClearChatEvent build(TwitchMessage message);
}
