package com.katana.sdk;

import com.katana.sdk.TransportMeta;
import com.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class TransportMetaTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        TransportMeta object = mockFactory.getTransportMeta();
        Assert.assertEquals(object, new TransportMeta(object));
        Assert.assertEquals(798260320, object.hashCode());
    }

}