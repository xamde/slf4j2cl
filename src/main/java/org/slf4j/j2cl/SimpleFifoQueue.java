package org.slf4j.j2cl;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * FIFO ordered just like java.util.concurrent.LinkedBlockingQueue. Does not allow null elements
 */
public class SimpleFifoQueue<E> extends AbstractQueue<E> {

    private final LinkedList<E> elements = new LinkedList<>();

    @Override
    public Iterator<E> iterator() {
        return elements.iterator();
    }

    @Override
    public boolean offer(E e) {
        elements.add(e);
        return true;
    }

    @Override
    public E peek() {
        if (size() == 0)
            return null;
        return elements.getFirst();
    }

    @Override
    public E poll() {
        if (size() == 0)
            return null;
        return elements.remove(0);
    }

    @Override
    public int size() {
        return elements.size();
    }
}
