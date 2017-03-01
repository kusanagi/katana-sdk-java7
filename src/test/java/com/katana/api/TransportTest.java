package com.katana.api;

import com.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class TransportTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        Transport object = mockFactory.getTransport();
        Assert.assertEquals(object, new Transport(object));
        Assert.assertEquals(-1487226011, object.hashCode());
    }

}