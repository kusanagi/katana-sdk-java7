package com.katana.api.schema;

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

}