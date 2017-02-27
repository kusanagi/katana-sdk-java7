package com.katana.testutils;

import com.katana.api.commands.ActionCommandPayload;
import com.katana.api.commands.Mapping;
import com.katana.api.commands.RequestCommandPayload;
import com.katana.api.commands.ResponseCommandPayload;
import com.katana.api.schema.ServiceSchema;
import com.katana.common.utils.Logger;
import com.katana.common.utils.MessagePackSerializer;
import com.katana.sdk.common.Serializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by juane on 2/11/17.
 */
public class MockFactory {

    public static final String MOCKS_PATH = "/home/jega/development/katana/katana-sdk-java7/src/test/java/com/katana/testutils/mocks/";

    public static RequestCommandPayload getRequestCommandPayload() {
        return getFromJson("request_command_payload.json", RequestCommandPayload.class);
    }

    public static ResponseCommandPayload getResponseCommandPayload() {
        return getFromJson("response_command_payload.json", ResponseCommandPayload.class);
    }

    public static ActionCommandPayload getActionCommandPayload() {
        return getFromJson("action_command_payload.json", ActionCommandPayload.class);
    }

    public static Mapping getMapping(String name, String version) {
        Mapping mapping = new Mapping();
        Map<String, Map<String, ServiceSchema>> serviceMap = new HashMap<>();
        Map<String, ServiceSchema> versionMap = new HashMap<>();

        versionMap.put(version, getServiceSchema());
        serviceMap.put(name, versionMap);
        mapping.setServiceSchema(serviceMap);

        return mapping;
    }

    private static ServiceSchema getServiceSchema() {
        return getFromJson("mapping.json", ServiceSchema.class);
    }

    private static <T> T getFromJson(String filename, Class<T> aClass) {
        try {
            Path path = Paths.get(MOCKS_PATH + filename);
            byte[] bytes = Files.readAllBytes(path);
            String command = new String(bytes);
            Serializer serializer = new MessagePackSerializer();
            return serializer.deserialize(command, aClass);
        } catch (IOException e) {
            Logger.log(e);
            return null;
        }
    }
}
