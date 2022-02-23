package org.slf4j.j2cl;

import elemental2.dom.Console;
import org.slf4j.event.Level;
import org.slf4j.helpers.MarkerIgnoringBase;

/**
 * You can put colors.
 * <p>
 * console.log('%c Sample Text', 'color:green;');
 * <p>
 * Or add some VAR in the text using:
 * <p>
 * console.log(`Sample ${variable}`, 'color:green;');
 * <p>
 * %o or %O
 * Outputs a JavaScript object. Clicking the object name opens more information about it in the inspector.
 * <p>
 * %d or %i
 * Outputs an integer. Number formatting is supported, for example console.log("Foo %.2d", 1.1) will output the number as two significant figures with a leading 0: Foo 01
 * <p>
 * %s
 * Outputs a string.
 * <p>
 * %f
 * Outputs a floating-point value. Formatting is supported, for example console.log("Foo %.2f", 1.1) will output the number to 2 decimal places: Foo 1.10
 * <p>
 * ou can use the %c directive to apply a CSS style to console output:
 */
public class J2clLogger extends MarkerIgnoringBase {

    private final Console console = new Console_wrapped();
    private Level level;

    public J2clLogger(Level level) {
        this.level = level;
    }

    /**
     * Note existing %o, %s or whatever remain. Only the '{}' from slf4j are translated to %s
     *
     * @param slf4jFormat
     * @return
     */
    private static String translate(String slf4jFormat) {
        return slf4jFormat.replace("{}", "%s");
    }

    /**
     * true iff this log level is enabled when given threshold is active
     */

    @Override
    public boolean isTraceEnabled() {
        return isLevelEnabledAt(Level.TRACE,level);
    }

    /**
     * NOTE: console.debug is not visible by default in the browser's JS console. It can be enabled by using the console's filter options 'Verbose'.
     */
    private boolean isLevelEnabledAt(Level level, Level threshold) {
        return level.toInt() >= threshold.toInt();
    }

    @Override
    public boolean isErrorEnabled() {
        return isLevelEnabledAt(Level.ERROR,level);
    }

    @Override
    public boolean isWarnEnabled() {
        return isLevelEnabledAt(Level.WARN,level);
    }

    @Override
    public boolean isDebugEnabled() {
        return isLevelEnabledAt(Level.DEBUG,level);
    }

    @Override
    public boolean isInfoEnabled() {
        return isLevelEnabledAt(Level.INFO,level);
    }

    @Override
    public void trace(String msg) {
        if (!isTraceEnabled()) return;
        console.debug("trace: " + msg);
    }

    @Override
    public void trace(String format, Object arg) {
        if (!isTraceEnabled()) return;
        console.debug("trace: " + translate(format), arg);
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        if (!isTraceEnabled()) return;
        console.debug("trace: " + translate(format), arg1, arg2);
    }

    @Override
    public void trace(String format, Object... arguments) {
        if (!isTraceEnabled()) return;
        console.debug(Util.add("trace: " + translate(format), arguments));
    }

    @Override
    public void trace(String msg, Throwable t) {
        if (!isTraceEnabled()) return;
        console.debug("trace: " + msg, t);
        console.trace("client-side trace");
    }


    @Override
    public void debug(String msg) {
        if (!isDebugEnabled()) return;
        console.debug(msg);
    }

    @Override
    public void debug(String format, Object arg) {
        if (!isDebugEnabled()) return;
        console.debug(translate(format), arg);
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        if (!isDebugEnabled()) return;
        console.debug(translate(format), arg1, arg2);
    }

    @Override
    public void debug(String format, Object... arguments) {
        if (!isDebugEnabled()) return;
        console.debug(Util.add(translate(format), arguments));
    }

    @Override
    public void debug(String msg, Throwable t) {
        if (!isDebugEnabled()) return;
        console.debug(msg, t);
        console.trace("client-side trace");
    }


    @Override
    public void info(String msg) {
        if (!isInfoEnabled()) return;
        console.info(msg);
    }

    @Override
    public void info(String format, Object arg) {
        if (!isInfoEnabled()) return;
        console.info(translate(format), arg);
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        if (!isInfoEnabled()) return;
        console.info(translate(format), arg1, arg2);
    }

    @Override
    public void info(String format, Object... arguments) {
        if (!isInfoEnabled()) return;
        console.info(Util.add(translate(format), arguments));
    }

    @Override
    public void info(String msg, Throwable t) {
        if (!isInfoEnabled()) return;
        console.info(msg, t);
        console.trace("client-side trace");
    }

    @Override
    public void warn(String msg) {
        if (isWarnEnabled()) console.warn(msg);
    }

    @Override
    public void warn(String format, Object arg) {
        if (isWarnEnabled()) console.warn(translate(format), arg);
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        if (isWarnEnabled()) console.warn(translate(format), arg1, arg2);
    }

    @Override
    public void warn(String format, Object... arguments) {
        if (isWarnEnabled()) console.warn(Util.add(translate(format), arguments));
    }

    @Override
    public void warn(String msg, Throwable t) {
        if (!isWarnEnabled()) return;
        console.warn(msg, t);
        console.trace("client-side trace");
    }

    @Override
    public void error(String msg) {
        if (!isErrorEnabled()) return;
        console.error(msg);
    }

    @Override
    public void error(String format, Object arg) {
        if (!isErrorEnabled()) return;
        console.error(translate(format), arg);
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        if (!isErrorEnabled()) return;
        console.error(translate(format), arg1, arg2);
    }

    @Override
    public void error(String format, Object... arguments) {
        if (!isErrorEnabled()) return;
        console.error(Util.add(translate(format), arguments));
    }

    @Override
    public void error(String msg, Throwable t) {
        if (!isErrorEnabled()) return;
        console.error(msg, t);
        console.trace("client-side trace");
    }

    public void setLevel(Level level) {
        // if this level had been compiled out by using a NOPLogger, there should be a warning and no exception
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }
}
