package com.katana.api.commands;

import com.katana.api.commands.common.CommandMeta;
import com.katana.api.commands.common.CommandPayload;
import com.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by juane on 2/11/17.
 */
@RunWith(JUnit4.class)
public class ActionCommandTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        ActionCommandPayload.ActionCommand object = mockFactory.getActionCommand();
        Assert.assertEquals(object, new ActionCommandPayload.ActionCommand(object));
        Assert.assertEquals(-1569720193, object.hashCode());
    }

}