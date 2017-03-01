package com.katana.sdk;

import com.katana.sdk.ActionParamSchema;
import com.katana.utils.MockFactory;
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

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        ActionParamSchema object = mockFactory.getActionParamSchema();
        Assert.assertEquals(object, new ActionParamSchema(object));
        Assert.assertEquals(-1440566884, object.hashCode());
    }

}