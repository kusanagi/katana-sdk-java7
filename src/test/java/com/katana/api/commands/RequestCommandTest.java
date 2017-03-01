package com.katana.api.commands;

import com.katana.api.commands.common.CommandMeta;
import com.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by juane on 2/11/17.
 */
@RunWith(JUnit4.class)
public class RequestCommandTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        RequestCommandPayload.RequestCommand object = mockFactory.getRequestCommand();
        Assert.assertEquals(object, new RequestCommandPayload.RequestCommand(object));
        Assert.assertEquals(1904559845, object.hashCode());
    }

}