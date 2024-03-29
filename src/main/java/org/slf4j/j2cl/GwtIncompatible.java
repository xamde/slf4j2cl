package org.slf4j.j2cl;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

@Retention(value = CLASS)
@Target(value = {TYPE, METHOD, CONSTRUCTOR, FIELD})
@Documented
public @interface GwtIncompatible {
}