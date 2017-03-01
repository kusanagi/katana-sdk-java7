package com.katana.sdk;

import com.katana.sdk.EntitySchema;
import com.katana.utils.MockFactory;
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

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        EntitySchema object = mockFactory.getEntitySchema();
        Assert.assertEquals(object, new EntitySchema(object));
        Assert.assertEquals(2003584586, object.hashCode());
    }

}