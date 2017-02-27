package com.katana.sdk.common;

/**
 * Created by juan on 25/09/16.
 */
public interface Serializer {
    /**
     * @param message
     * @param aClass
     * @param <T>
     * @return
     */
    <T> T deserialize(byte[] message, Class<T> aClass);

    <T> T deserialize(String jsonMessage, Class<T> aClass);

    /**
     * @param message
     * @return
     */
    byte[] serializeInBytes(Object message);

    String serializeInJson(Object message);

}
