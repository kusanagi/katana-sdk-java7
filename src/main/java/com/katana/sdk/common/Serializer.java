package com.katana.sdk.common;

/**
 * Created by juan on 25/09/16.
 */
public interface Serializer {
    /**
     *
     * @param message
     * @param aClass
     * @param <T>
     * @return
     */
    <T> T read(byte[] message, Class<T> aClass);

    /**
     *
     * @param message
     * @return
     */
    byte[] write(Object message);

}
