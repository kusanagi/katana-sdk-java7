package com.katana.api.schema;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class ActionHttpSchemaTest {

    private ActionHttpSchema actionHttpSchema;

    @Before
    public void setup() {
        actionHttpSchema = new ActionHttpSchema();
    }

    @Test
    public void defaultValues() {
        Assert.assertTrue(actionHttpSchema.isGateway());
        Assert.assertEquals("/", actionHttpSchema.getPath());
        Assert.assertEquals("get", actionHttpSchema.getMethod());
        Assert.assertEquals("query", actionHttpSchema.getInput());
        Assert.assertEquals("text/plain", actionHttpSchema.getBody()[0]);
    }

}