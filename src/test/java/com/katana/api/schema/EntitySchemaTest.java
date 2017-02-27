package com.katana.api.schema;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class EntitySchemaTest {

    private EntitySchema entitySchema;

    @Before
    public void setup() {
        entitySchema = new EntitySchema();
    }

    @Test
    public void defaultValues() {
        Assert.assertNotEquals(null, entitySchema.getField());
        Assert.assertNotEquals(null, entitySchema.getFields());
        Assert.assertFalse(entitySchema.isValidate());
    }

}