package com.katana.common;

/**
 * Created by juan on 1/10/16.
 */
public class Constants {

    public static final String VERSION_PATTERN = "[a-zA-Z0-9*.,_\\-]+";

    public static final String SERVICE = "service";
    public static final String MIDDLEWARE = "middleware";

    public static final String WORKER_ENDPOINT = "ipc://workers";

    private Constants() {
        // private constructor to block the instantiation of this object
    }

}
