package org.slf4j.j2cl;

import java.util.ArrayList;
import java.util.List;

class PlatformScript {

    public String getCurrentThreadName() {
        return "n/a";
    }

    /**
     * There are no threads in script mode, so this {@link ArrayList} is thread-safe enough
     * @param <E>
     * @return
     */
    public <E> List<E> createThreadSafeArrayList() {
        return new ArrayList<E>();
    }


}
