package org.slf4j.j2cl;

class PlatformJre extends PlatformScript{

    @GwtIncompatible
    public String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }

}
