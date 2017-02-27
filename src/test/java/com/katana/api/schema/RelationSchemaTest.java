package com.katana.api.schema;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class RelationSchemaTest {

    private RelationSchema relationSchema;

    @Before
    public void setup() {
        relationSchema = new RelationSchema();
    }

    @Test
    public void defaultValues() {
        Assert.assertEquals("one", relationSchema.getType());
    }

}