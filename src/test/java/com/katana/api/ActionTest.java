package com.katana.api;

import com.katana.api.schema.ActionHttpSchema;
import com.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class ActionTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        Action object = mockFactory.getAction();
        Assert.assertEquals(object, new Action(object));
        Assert.assertEquals(787668137, object.hashCode());
    }

}