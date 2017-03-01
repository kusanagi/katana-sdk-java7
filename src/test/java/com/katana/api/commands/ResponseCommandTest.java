package com.katana.api.commands;

import com.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by juane on 2/11/17.
 */
@RunWith(JUnit4.class)
public class ResponseCommandTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        ResponseCommandPayload.ResponseCommand object = mockFactory.getResponseCommand();
        Assert.assertEquals(object, new ResponseCommandPayload.ResponseCommand(object));
        Assert.assertEquals(1937360609, object.hashCode());
    }

}