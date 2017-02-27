package com.katana.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.katana.sdk.common.Serializer;
import org.msgpack.jackson.dataformat.MessagePackFactory;

import java.io.IOException;

/**
 * Created by juan on 25/09/16.
 */
public class MessagePackSerializer implements Serializer {

    private ObjectMapper msgPackMapper = new ObjectMapper(new MessagePackFactory());
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @param message
     * @param aClass
     * @param <T>
     * @return
     */
    @Override
    public <T> T deserialize(byte[] message, Class<T> aClass) {
        try {
            return msgPackMapper.readValue(message, aClass);
        } catch (IOException e) {
            Logger.log(e);
            return null;
        }
    }

    @Override
    public <T> T deserialize(String jsonMessage, Class<T> aClass) {
        try {
            return objectMapper.readValue(jsonMessage, aClass);
        } catch (IOException e) {
            Logger.log(e);
            return null;
        }
    }

    /**
     * @param message
     * @return
     */
    @Override
    public byte[] serializeInBytes(Object message) {
        try {
            msgPackMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            return msgPackMapper.writeValueAsBytes(message);
        } catch (Exception e) {
            Logger.log(e);
            return new byte[0];
        }
    }

    @Override
    public String serializeInJson(Object message) {
        try {
            return objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            Logger.log(e);
            return null;
        }
    }

}
