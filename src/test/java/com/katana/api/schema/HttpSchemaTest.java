package com.katana.api.schema;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class HttpSchemaTest {

    private HttpSchema httpSchema;

    @Before
    public void setup() {
        httpSchema = new HttpSchema();
    }

    @Test
    public void defaultValues() {
        Assert.assertTrue(httpSchema.isGateway());
        Assert.assertEquals("", httpSchema.getBasePath());
    }

}