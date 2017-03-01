package com.katana.api;

import com.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class HttpRequestTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        HttpRequest object = mockFactory.getHttpRequest();
        Assert.assertEquals(object, new HttpRequest(object));
        Assert.assertEquals(-110193202, object.hashCode());
    }

}