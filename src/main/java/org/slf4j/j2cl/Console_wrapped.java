package org.slf4j.j2cl;

import elemental2.dom.Console;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

import java.util.Arrays;

/**
 * Back-wrap the elemental2.dom.Console so that it runs also on the JRE side. This simplifies testing.
 * Only the bare minimum for testing Console in log context is wrapped.
 */
public class Console_wrapped extends Console_script {

    @Override
    @GwtIncompatible
    public void debug(Object... var_data) {
        System.out.println("DEBUG " + Arrays.toString(var_data));
    }

    @Override
    @GwtIncompatible
    public void info(Object... var_data) {
        System.out.println("INFO " + Arrays.toString(var_data));
    }

    @Override
    @GwtIncompatible
    public void warn(Object... var_data) {
        System.out.println("WARN " + Arrays.toString(var_data));
    }

    @Override
    @GwtIncompatible
    public void error(Object... var_data) {
        System.out.println("ERROR " + Arrays.toString(var_data));
    }

    @Override
    @GwtIncompatible
    public void trace(Object... var_data) {
        System.out.println("TRACE " + Arrays.toString(var_data));
    }


}
