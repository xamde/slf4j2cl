package org.slf4j.j2cl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

class PlatformJre extends PlatformScript {

    @GwtIncompatible
    @Override
    public String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }

    @GwtIncompatible
    @Override
    public <E> CopyOnWriteArrayList<E> createCopyOnWriteArrayList() {
        return new CopyOnWriteArrayList<E>();
    }

    @GwtIncompatible
    @Override
    public boolean isScript() {
        return false;
    }

    @GwtIncompatible
    @Override
    public void runOnlyOnJre(Runnable runnable) {
        runnable.run();
    }

    @GwtIncompatible
    @Override
    public void runOnlyInScript(Runnable runnable) {
        // NO-OP
    }

    @GwtIncompatible
    @Override
    public <E> int drainQueueTo(Queue<E> queue, Collection<? super E> c, int maxElements) {
        if(queue instanceof LinkedBlockingQueue) {
            LinkedBlockingQueue<E> linkedBlockingQueue = (LinkedBlockingQueue<E>) queue;
            return linkedBlockingQueue.drainTo(c,maxElements);
        } else {
            return drainQueueTo_(queue, c, maxElements);
        }
    }

    @GwtIncompatible
    @Override
    public <E> LinkedBlockingQueue<E> createLinkedBlockingQueue() {
        return new LinkedBlockingQueue<E>();
    }

    @GwtIncompatible
    @Override
    public ThreadLocal<Map<String, String>> createInheritableThreadLocal() {
        return new InheritableThreadLocal<Map<String, String>>() {
            @Override
            protected Map<String, String> childValue(Map<String, String> parentValue) {
                if (parentValue == null) {
                    return null;
                }
                return new HashMap<String, String>(parentValue);
            }
        };
    }
}