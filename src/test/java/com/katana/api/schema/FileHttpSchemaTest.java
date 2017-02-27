package com.katana.api.schema;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class FileHttpSchemaTest {

    private FileHttpSchema fileHttpSchema;

    @Before
    public void setup() {
        fileHttpSchema = new FileHttpSchema();
    }

    @Test
    public void defaultValues() {
        Assert.assertTrue(fileHttpSchema.isGateway());
    }
}