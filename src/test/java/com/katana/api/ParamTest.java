package com.katana.api;

import com.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class ParamTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        Param object = mockFactory.getParam();
        Assert.assertEquals(object, new Param(object));
        Assert.assertEquals(-323295288, object.hashCode());
    }

}