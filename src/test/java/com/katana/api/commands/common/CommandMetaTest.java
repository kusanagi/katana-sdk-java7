package com.katana.api.commands.common;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class CommandMetaTest {

    private CommandMeta object;

    @Before
    public void setup() {
        this.object = new CommandMeta();
    }

    @Test
    public void getAndSet() {
        String scope = "";
        this.object.setScope(scope);
        Assert.assertEquals(scope, this.object.getScope());
    }

}