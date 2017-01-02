package com.katana.api.common;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan on 22/11/16.
 */
public class TransactionTest {

    private Transaction transaction;

    @Before
    public void setup() {
        this.transaction = new Transaction();
    }

    @Test
    public void equals() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        Transaction mockTransaction1 = podamFactory.manufacturePojoWithFullData(Transaction.class);
        Transaction mockTransaction2 = podamFactory.manufacturePojoWithFullData(Transaction.class);
        Transaction mockTransaction3 = new Transaction(mockTransaction1);
        Assert.assertNotEquals(mockTransaction1, mockTransaction2);
        Assert.assertNotEquals(mockTransaction1, new Object());
        Assert.assertEquals(mockTransaction1, mockTransaction3);
    }
}