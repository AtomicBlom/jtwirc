package jtwirc.enums;

/**
 * Enum for representing different sizes of Twitch Emotes.<br><br>
 * <p>
 * Emotes comes in three different sizes: Small, Medium and Large.
 */
public enum EMOTE_SIZE
{
    SMALL("/1.0"),
    MEDIUM("/2.0"),
    LARGE("/3.0");
    public final String value;

    EMOTE_SIZE(String val)
    {
        this.value = val;
    }
}
