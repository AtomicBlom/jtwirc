package com.gikk.twirk.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Unfinished
{
    String value();

    String[] owners() default "";

    Priority priority() default Priority.MEDIUM;

    enum Priority
    {
        LOWEST, LOW, MEDIUM, HIGH, HIGHEST
    }
}
