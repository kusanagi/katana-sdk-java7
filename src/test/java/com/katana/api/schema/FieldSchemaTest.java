package com.katana.api.schema;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class FieldSchemaTest {

    private FieldSchema fieldSchema;

    @Before
    public void setup() {
        fieldSchema = new FieldSchema();
    }

    @Test
    public void defaultValues() {
        Assert.assertFalse(fieldSchema.isOptional());
    }

}