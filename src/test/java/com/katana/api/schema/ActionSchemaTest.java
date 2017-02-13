package com.katana.api.schema;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by juane on 2/11/17.
 */
public class ActionSchemaTest {

    private ActionSchema actionSchema;

    @Before
    public void setup() {
        actionSchema = new ActionSchema();
    }

    @Test
    public void defaultValues(){
        Assert.assertEquals(1000, actionSchema.getTimeout());
        Assert.assertEquals("/", actionSchema.getPathDelimiter());
        Assert.assertEquals("id", actionSchema.getPrimaryKey());
        Assert.assertFalse(actionSchema.isCollection());
        Assert.assertFalse(actionSchema.isDeprecated());
    }

}