package com.katana.api.schema;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by juane on 2/11/17.
 */
public class ActionParamHttpSchemaTest {

    private ActionParamHttpSchema actionParamHttpSchema;

    @Before
    public void setup() {
        actionParamHttpSchema = new ActionParamHttpSchema();
    }

    @Test
    public void defaultValues(){
        Assert.assertTrue(actionParamHttpSchema.isGateway());
        Assert.assertEquals("query", actionParamHttpSchema.getInput());
    }

}