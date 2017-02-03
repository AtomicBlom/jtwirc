package jtwirc.types;

/**
 * Base interface for all types
 */
public interface AbstractType
{
    /**
     * Get the raw chat line, which this type was constructed from
     *
     * @return The raw chat line, just as seen from the server
     */
    String getRaw();
}
