package com.katana.sdk;

import com.katana.sdk.ServiceSchema;
import com.katana.utils.MockFactory;
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

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        ServiceSchema object = mockFactory.getServiceSchema();
        Assert.assertEquals(object, new ServiceSchema(object));
        Assert.assertEquals(-1047615797, object.hashCode());
    }

}