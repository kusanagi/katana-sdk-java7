package com.katana.common;

import com.katana.common.utils.Option;

/**
 * Created by juan on 1/10/16.
 */
public class Constants {

    public static final String VERSION_PATTERN = "[a-zA-Z0-9*.,_\\-]+";

    public static final String SERVICE = "service";

    public static final String MIDDLEWARE = "middleware";

    public static final String REQUEST_STRING = "request";

    public static final String WORKER_ENDPOINT = "ipc://workers";

    public static final String WORKERS = "workers";

    public static final int VOID_BYTE = 0x00;
    public static final int CALL_BYTE = 0x01;
    public static final int FILE_BYTE = 0x02;
    public static final int TRANSACTION_BYTE = 0x03;
    public static final int DOWNLOAD_BYTE = 0x04;

    private Constants() {
        // private constructor to block the instantiation of this object
    }

}
