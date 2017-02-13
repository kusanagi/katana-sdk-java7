package com.katana.api.schema;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by juane on 2/11/17.
 */
public class ValueSchemaTest {

    private ValueSchema valueSchema;

    @Before
    public void setup() {
        valueSchema = new ValueSchema();
    }

    @Test
    public void defaultValues(){
        Assert.assertEquals("string", valueSchema.getType());
    }

}