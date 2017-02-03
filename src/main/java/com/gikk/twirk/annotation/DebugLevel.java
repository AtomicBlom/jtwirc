package com.gikk.twirk.annotation;

public @interface DebugLevel
{

    String value();

    Level level() default Level.OFF;

    enum Level
    {
        ALL, NOTIFICATION, MINIMAL, OFF
    }
}
