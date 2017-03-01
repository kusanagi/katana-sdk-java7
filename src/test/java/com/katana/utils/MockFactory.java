package com.katana.utils;

import com.katana.api.commands.ActionCommandPayload;
import com.katana.api.commands.Mapping;
import com.katana.api.commands.RequestCommandPayload;
import com.katana.api.commands.ResponseCommandPayload;
import com.katana.api.schema.ServiceSchema;
import com.katana.common.utils.Logger;
import com.katana.common.utils.MessagePackSerializer;
import com.katana.sdk.common.Serializer;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by juane on 2/11/17.
 */
public class MockFactory {

    public final String mocksPath = "/com/katana/resources/";

    public RequestCommandPayload getRequestCommandPayload() {
        return getFromJson("request_command_payload.json", RequestCommandPayload.class);
    }

    public ResponseCommandPayload getResponseCommandPayload() {
        return getFromJson("response_command_payload.json", ResponseCommandPayload.class);
    }

    public ActionCommandPayload getActionCommandPayload() {
        return getFromJson("action_command_payload.json", ActionCommandPayload.class);
    }

    public Mapping getMapping(String name, String version) {
        Mapping mapping = new Mapping();
        Map<String, Map<String, ServiceSchema>> serviceMap = new HashMap<>();
        Map<String, ServiceSchema> versionMap = new HashMap<>();

        versionMap.put(version, getServiceSchema());
        serviceMap.put(name, versionMap);
        mapping.setServiceSchema(serviceMap);

        return mapping;
    }

    private ServiceSchema getServiceSchema() {
        return getFromJson("mapping.json", ServiceSchema.class);
    }

    private <T> T getFromJson(String filename, Class<T> aClass) {
        try {
            URL url = getClass().getResource(mocksPath + filename);
            Path path = Paths.get(url.toURI());
            byte[] bytes = Files.readAllBytes(path);
            String command = new String(bytes);
            Serializer serializer = new MessagePackSerializer();
            return serializer.deserialize(command, aClass);
        } catch (IOException | URISyntaxException e) {
            Logger.log(e);
            return null;
        }
    }
}
