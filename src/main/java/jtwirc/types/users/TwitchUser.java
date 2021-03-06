package jtwirc.types.users;

import jtwirc.enums.USER_TYPE;
import jtwirc.events.TwircListener;

/**
 * Class for representing a Twitch User's attributes<br><br>
 * <p>
 * Whenever we receive a PRIVMSG or WHISPER from Twitch (see {@link TwircListener}, Twitch always sends us some
 * information about the sender, such as what color the user has in chat on Twitch, how the users name should
 * be capitalized, if the user has Turbo, and so on. This class encapsulates all that info, and makes it easy
 * to work with.
 */
public interface TwitchUser
{

    /**
     * Retrieves this users name, as displayed in Twitch chat
     *
     * @return The user name
     */
    String getName();

    /**
     * Retrieves info whether this user is twitch staff or not
     *
     * @return {@code true} if the user is mod, {@code false} if not
     */
    boolean isStaff();

    /**
     * Retrieves info whether this user is a twitch admin or not
     *
     * @return {@code true} if the user is mod, {@code false} if not
     */
    boolean isAdmin();

    /**
     * Retrieves info whether this user is a global mod or not
     *
     * @return {@code true} if the user is mod, {@code false} if not
     */
    boolean isGlobalMod();

    /**
     * Retrieves info whether this user is the broadcaster or not
     *
     * @return {@code true} if the user is mod, {@code false} if not
     */
    boolean isBroadcaster();

    /**
     * Retrieves info whether this user is a mod in this channel or not
     *
     * @return {@code true} if the user is mod, {@code false} if not
     */
    boolean isMod();

    /**
     * Retrieves info whether this user has turbo or not
     *
     * @return {@code true} if the user has turbo, {@code false} if not
     */
    boolean isTurbo();

    /**
     * Retrieves info whether this user is a sub to this channel or not
     *
     * @return {@code true} if the user is a sub, {@code false} if not
     */
    boolean isSub();

    /**
     * Retrieves this users {@link USER_TYPE} <br>
     * There are six USER_TYPEs: OWNER, MOD, GLOBAL_MOD, ADMIN, STAFF, DEFAULT.
     *
     * @return The user's USER_TYPE
     */
    USER_TYPE getUserType();

    /**
     * Retrieves this users display color, as seen in Twitch chat.<br>
     * The color is a hexadecimal number.
     *
     * @return The users display color, as a hex number
     */
    int getColor();

    /**
     * Retrieves the users set of badges in Twitch chat. A badge looks like this: <br>
     * {@code broadcaster/1} <br><br>
     * <p>
     * There are several different badges, such as {@code broadcaster/1}, {@code turbo/1} and so on. I do
     * not know all of them explicitly, or what to do with them.
     * <p>
     * TODO: Find out more about badges
     *
     * @return Arrays of strings, representing this users badges. Might be empty if user has none.
     */
    String[] getBadges();

    /**
     * Retrieves this user's unique user ID. This ID is decided by Twitch, and will
     * always be the same for the same user
     *
     * @return The users unique user ID
     */
    int getUserID();
}
