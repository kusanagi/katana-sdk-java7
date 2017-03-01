package com.katana.api.commands.common;

import com.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class CommandMetaTest {

    private MockFactory mockFactory;

    @Before
    public void setup() {
        mockFactory = new MockFactory();
    }

    @Test
    public void getAndSet() {
        String scope = "";
        CommandMeta commandMeta = new CommandMeta();
        commandMeta.setScope(scope);
        Assert.assertEquals(scope, commandMeta.getScope());
    }

}