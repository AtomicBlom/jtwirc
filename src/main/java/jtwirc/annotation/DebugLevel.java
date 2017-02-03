package jtwirc.annotation;

public @interface DebugLevel
{

    String value();

    Level level() default Level.OFF;

    enum Level
    {
        ALL, NOTIFICATION, MINIMAL, OFF
    }
}
