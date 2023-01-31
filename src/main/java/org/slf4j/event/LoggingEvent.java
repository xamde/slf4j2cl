package org.slf4j.event;

import org.slf4j.Marker;

/**
 * @author ceki
 * @since 1.7.15
 */
public interface LoggingEvent {

    Object[] getArgumentArray();

    Level getLevel();

    String getLoggerName();

    Marker getMarker();

    String getMessage();

    String getThreadName();

    Throwable getThrowable();

    long getTimeStamp();

}
