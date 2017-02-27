package com.katana.api.schema;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class ActionParamSchemaTest {

    private ActionParamSchema actionParamSchema;

    @Before
    public void setup() {
        actionParamSchema = new ActionParamSchema();
    }

    @Test
    public void defaultValues() {
        Assert.assertEquals("string", actionParamSchema.getType());
        Assert.assertEquals("csv", actionParamSchema.getArrayFormat());
        Assert.assertFalse(actionParamSchema.isAllowEmpty());
        Assert.assertFalse(actionParamSchema.isRequired());
        Assert.assertFalse(actionParamSchema.isExclusiveMax());
        Assert.assertFalse(actionParamSchema.isExclusiveMin());
        Assert.assertFalse(actionParamSchema.isUniqueItems());
        Assert.assertNotEquals(null, actionParamSchema.getHttpSchema());
    }

}