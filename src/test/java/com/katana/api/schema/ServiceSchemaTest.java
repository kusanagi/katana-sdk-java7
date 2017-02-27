package com.katana.api.schema;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class ServiceSchemaTest {

    private ServiceSchema serviceSchema;

    @Before
    public void setup() {
        serviceSchema = new ServiceSchema();
    }

    @Test
    public void defaultValues() {
        Assert.assertFalse(serviceSchema.isFiles());
        Assert.assertNotEquals(null, serviceSchema.getHttpSchema());
        Assert.assertNotEquals(null, serviceSchema.getActions());
    }

}