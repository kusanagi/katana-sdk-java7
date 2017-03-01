package com.katana.api;

import com.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class ServiceTransactionTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        ServiceTransaction object = mockFactory.getServiceTransaction();
        Assert.assertEquals(object, new ServiceTransaction(object));
        Assert.assertEquals(-723744961, object.hashCode());
    }

}