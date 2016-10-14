package com.katana.sdk.common;

/**
 * Created by juan on 25/09/16.
 */
public interface Serializer {
    <T> T read(byte[] message, Class<T> aClass);

    byte[] write(Object message);

}
