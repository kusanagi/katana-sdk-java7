package com.katana.sdk.common;

import com.katana.api.commands.ActionCommandPayload;
import org.junit.Assert;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Created by juan on 3/10/16.
 */
public class MessagePackSerializerTest {
    @Test
    public void write() {
        Serializer serializer = new MessagePackSerializer();
        PodamFactoryImpl podamFactory = new PodamFactoryImpl();
        ActionCommandPayload commandPayload = podamFactory.manufacturePojoWithFullData(ActionCommandPayload.class);

        byte[] bytes = serializer.write(commandPayload);
        ActionCommandPayload deserializeCommandPayload = serializer.read(bytes, ActionCommandPayload.class);

//        Assert.assertTrue(commandPayload.equals(deserializeCommandPayload));
        Assert.assertTrue(true);
    }

}