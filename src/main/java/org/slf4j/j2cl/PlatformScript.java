package org.slf4j.j2cl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class PlatformScript {

    public String getCurrentThreadName() {
        return "n/a";
    }

    /**
     * There are no threads in script mode, so this {@link ArrayList} is thread-safe enough
     *
     * @param <E>
     * @return
     */
    public <E> List<E> createCopyOnWriteArrayList() {
        return new ArrayList<E>();
    }

    public boolean isScript() {
        return true;
    }

    public void runOnlyOnJre(Runnable runnable) {
        // NO-OP
    }

    public void runOnlyInScript(Runnable runnable) {
        runnable.run();
    }


    /**
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegalArgumentException      {@inheritDoc}
     */
    public <E> int drainQueueTo(Queue<E> queue, Collection<? super E> c) {
        return drainQueueTo(queue, c, Integer.MAX_VALUE);
    }

    /**
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegalArgumentException      {@inheritDoc}
     */
    public static <E> int drainQueueTo_(Queue<E> queue, Collection<? super E> c, int maxElements) {
        if (c == null)
            throw new NullPointerException();
        if (c == queue)
            throw new IllegalArgumentException();
        if (maxElements <= 0)
            return 0;
        boolean signalNotFull = false;
        int n = Math.min(maxElements, queue.size());
        int i = 0;
        try {
            while (i < n) {
                // remove from queue only if add worked
                E element = queue.peek();
                c.add(element);
                queue.poll();
                i++;
            }
        } finally {
            return i;
        }
    }

    /**
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegalArgumentException      {@inheritDoc}
     */
    public <E> int drainQueueTo(Queue<E> queue, Collection<? super E> c, int maxElements) {
        return drainQueueTo_(queue, c, maxElements);
    }

    public <E> Queue<E> createLinkedBlockingQueue() {
        return new SimpleFifoQueue<>();
    }

    public ThreadLocal<Map<String, String>> createInheritableThreadLocal() {
        return new ThreadLocal<>();
    }
}