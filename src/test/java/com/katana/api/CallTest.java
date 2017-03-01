package com.katana.api;

import com.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class CallTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        Call object = mockFactory.getCall();
        Assert.assertEquals(object, new Call(object));
        Assert.assertEquals(1824560448, object.hashCode());
    }

}