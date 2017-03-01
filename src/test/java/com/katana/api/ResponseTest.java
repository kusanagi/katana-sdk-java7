package com.katana.api;

import com.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class ResponseTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        Response object = mockFactory.getResponse();
        Assert.assertEquals(object, new Response(object));
        Assert.assertEquals(-218357, object.hashCode());
    }

}