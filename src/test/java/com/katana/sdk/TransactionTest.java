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
        Assert.assertEquals(
                "Transaction{commit=[ServiceTransaction{name='users', version='1.0.0', action='create', callee='save', params=[Param{name='user_id', value='123', type='integer', exists=false}]}], rollback=[ServiceTransaction{name='users', version='1.0.0', action='create', callee='undo', params=[Param{name='user_id', value='123', type='integer', exists=false}]}], complete=[ServiceTransaction{name='users', version='1.0.0', action='create', callee='cleanup', params=[Param{name='user_id', value='123', type='integer', exists=false}]}]}",
                object.toString());
    }

}