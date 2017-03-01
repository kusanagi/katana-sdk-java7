package com.katana.sdk;

import com.katana.sdk.Request;
import com.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class RequestTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        Request object = mockFactory.getRequest();
        Assert.assertEquals(object, new Request(object));
        Assert.assertEquals(-33019121, object.hashCode());
    }

}