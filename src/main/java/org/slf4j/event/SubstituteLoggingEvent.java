package org.slf4j.event;

import org.slf4j.Marker;
import org.slf4j.helpers.SubstituteLogger;

public class SubstituteLoggingEvent implements LoggingEvent {

    Level level;
    Marker marker;
    String loggerName;
    SubstituteLogger logger;
    String threadName;
    String message;
    Object[] argArray;
    long timeStamp;
    Throwable throwable;

    public Object[] getArgumentArray() {
        return argArray;
    }

    public Level getLevel() {
        return level;
    }

    public SubstituteLogger getLogger() {
        return logger;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public Marker getMarker() {
        return marker;
    }

    public String getMessage() {
        return message;
    }

    public String getThreadName() {
        return threadName;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setArgumentArray(Object[] argArray) {
        this.argArray = argArray;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setLogger(SubstituteLogger logger) {
        this.logger = logger;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
