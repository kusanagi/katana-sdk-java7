package com.katana.api.schema;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by juane on 2/11/17.
 */
public class ObjectFieldSchemaTest {

    private ObjectFieldSchema objectFieldSchema;

    @Before
    public void setup() {
        objectFieldSchema = new ObjectFieldSchema();
    }

    @Test
    public void defaultValues(){
        Assert.assertFalse(objectFieldSchema.isOptional());
        Assert.assertNotEquals(null, objectFieldSchema.getField());
        Assert.assertNotEquals(null, objectFieldSchema.getFields());
    }

}