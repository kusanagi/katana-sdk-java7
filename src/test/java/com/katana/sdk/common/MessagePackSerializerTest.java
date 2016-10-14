package com.katana.sdk.common;

import com.katana.api.commands.ActionCommandPayload;
import com.katana.api.commands.common.CommandPayload;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by juan on 3/10/16.
 */
public class MessagePackSerializerTest {
    @Test
    public void write() throws Exception {
        Serializer serializer = new MessagePackSerializer();
        CommandPayload commandPayload = new ActionCommandPayload();

        byte[] bytes = serializer.write(commandPayload);
        CommandPayload deserializeCommandPayload = serializer.read(bytes, ActionCommandPayload.class);

        Assert.assertTrue(commandPayload.equals(deserializeCommandPayload));
    }

}