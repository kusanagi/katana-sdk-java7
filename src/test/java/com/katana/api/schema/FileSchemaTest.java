package com.katana.api.schema;

import com.katana.api.commands.ActionCommandPayload;
import com.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class FileSchemaTest {

    private FileSchema fileSchema;

    @Before
    public void setup() {
        fileSchema = new FileSchema();
    }

    @Test
    public void defaultValues() {
        Assert.assertFalse(fileSchema.isRequired());
        Assert.assertFalse(fileSchema.isExclusiveMax());
        Assert.assertFalse(fileSchema.isExclusiveMin());
        Assert.assertNotEquals(null, fileSchema.getHttpSchema());
    }

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        FileSchema object = mockFactory.getFileSchema();
        Assert.assertEquals(object, new FileSchema(object));
        Assert.assertEquals(-594969070, object.hashCode());
    }

}