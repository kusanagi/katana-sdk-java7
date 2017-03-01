package com.katana.sdk;

import com.katana.sdk.TransportSchema;
import com.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class TransportSchemaTest {

    private TransportSchema transportSchema;

    @Before
    public void setup() {
        transportSchema = new TransportSchema();
    }

    @Test
    public void defaultValues() {
        Assert.assertNotEquals(null, transportSchema.getProperties());
        Assert.assertNotEquals(null, transportSchema.getData());
        Assert.assertNotEquals(null, transportSchema.getRelations());
        Assert.assertNotEquals(null, transportSchema.getLinks());
        Assert.assertNotEquals(null, transportSchema.getErrors());
    }

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        TransportSchema object = mockFactory.getTransportSchema();
        Assert.assertEquals(object, new TransportSchema(object));
        Assert.assertEquals(1723662092, object.hashCode());
    }

}