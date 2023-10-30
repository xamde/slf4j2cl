package org.slf4j.j2cl;

import elemental2.dom.Console;
import elemental2.dom.DomGlobal;

/**
 * Back-wrap the elemental2.dom.Console so that it runs also on the JRE side. This simplifies testing. Only the bare
 * minimum for testing Console in log context is wrapped.
 */
public class Console_script {

    private Console console;

    public void debug(Object... var_data) {
        ensureInit();
        console.debug(var_data);
    }

    public void error(Object... var_data) {
        ensureInit();
        console.error(var_data);
    }

    public void info(Object... var_data) {
        ensureInit();
        console.info(var_data);
    }

    public void trace(Object... var_data) {
        ensureInit();
        console.trace(var_data);
    }

    public void warn(Object... var_data) {
        ensureInit();
        console.warn(var_data);
    }

    /**
     * Defer init until actually used. This allows to use slf4jcl init before the DOM has loaded.
     */
    private void ensureInit() {
        if (console == null) {
            console = DomGlobal.console;
        }
    }


}
