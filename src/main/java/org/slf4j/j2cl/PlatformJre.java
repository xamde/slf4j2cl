package org.slf4j.j2cl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class PlatformJre extends PlatformScript{

    @GwtIncompatible
    @Override
    public String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }

    @GwtIncompatible
    @Override
    public <E> List<E> createThreadSafeArrayList() {
        return new CopyOnWriteArrayList<E>();
    }

}
