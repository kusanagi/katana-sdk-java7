package com.katana.sdk;

import com.katana.sdk.ValueSchema;
import com.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    public void defaultValues() {
        Assert.assertEquals("string", valueSchema.getType());
    }

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        ValueSchema object = mockFactory.getValueSchema();
        Assert.assertEquals(object, new ValueSchema(object));
        Assert.assertEquals(570075075, object.hashCode());
    }

}