package org.slf4j.j2cl;

public class Platform extends PlatformJre {

    private static Platform INSTANCE;

    public static synchronized Platform get() {
        if(INSTANCE==null)
            INSTANCE = new Platform();
        return INSTANCE;
    }

}
