package org.slf4j.j2cl;

public class Util {

    public static Object[] add(final Object head, final Object ... elements) {
        if (elements == null) {
            return new Object[] { head };
        }
        final Object[] newArray = new Object[1 + elements.length];
        newArray[0] = head;
        System.arraycopy(elements, 0, newArray, 1, elements.length);
        return newArray;
    }

}
