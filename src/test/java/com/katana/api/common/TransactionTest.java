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
    public void getAction() {
        //SETUP
        String newAction = "New action";
        this.transaction.setAction(newAction);

        // ACTION
        String action = this.transaction.getAction();

        // EXPECTED
        Assert.assertEquals(action, newAction);
    }

    @Test
    public void setAction() {
        //SETUP
        String action = "New action";

        // ACTION
        this.transaction.setAction(action);

        // EXPECTED
        Assert.assertEquals(this.transaction.getAction(), action);
    }

    @Test
    public void getParams() {
        //SETUP
        List<Param> newParams = new ArrayList<>();
        this.transaction.setParams(newParams);

        // ACTION
        List<Param> params = this.transaction.getParams();

        // EXPECTED
        Assert.assertEquals(params, newParams);
    }

    @Test
    public void setParams() {
        //SETUP
        List<Param> newParams = new ArrayList<>();

        // ACTION
        this.transaction.setParams(newParams);

        // EXPECTED
        Assert.assertEquals(this.transaction.getParams(), newParams);
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