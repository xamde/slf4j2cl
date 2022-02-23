package org.slf4j.j2cl;

import elemental2.dom.Console;
import elemental2.dom.DomGlobal;

/**
 * Back-wrap the elemental2.dom.Console so that it runs also on the JRE side. This simplifies testing.
 * Only the bare minimum for testing Console in log context is wrapped.
 */
public class Console_script {

    private Console console = DomGlobal.console;

    public void debug(Object... var_data) {
        console.debug(var_data);
    }

    public void info(Object... var_data) {
        console.info(var_data);
    }

    public void warn(Object... var_data) {
        console.warn(var_data);
    }

    public void error(Object... var_data) {
        console.error(var_data);
    }

    public void trace(Object... var_data) {
        console.trace(var_data);
    }


}
