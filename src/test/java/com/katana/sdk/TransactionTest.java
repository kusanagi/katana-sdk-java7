package com.katana.sdk;

import com.katana.sdk.Transaction;
import com.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class TransactionTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        Transaction object = mockFactory.getTransaction();
        Assert.assertEquals(object, new Transaction(object));
        Assert.assertEquals(-669451010, object.hashCode());
    }

}