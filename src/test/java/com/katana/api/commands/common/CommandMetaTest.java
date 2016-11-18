package com.katana.api.commands.common;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juan on 12/11/16.
 */
public class CommandMetaTest {

    private CommandMeta commandMeta;

    @Before
    public void setup() {
        this.commandMeta = new CommandMeta();
    }

    @Test
    public void getService() {
        // SETUP
        String service = "Service";
        this.commandMeta.setService(service);

        // ACTION
        String serviceObtained = this.commandMeta.getService();

        // EXPECTED
        Assert.assertEquals(service, serviceObtained);
    }

    @Test
    public void setService() {
        // SETUP
        String service = "Service";

        // ACTION
        this.commandMeta.setService(service);

        // EXPECTED
        Assert.assertEquals(service, this.commandMeta.getService());
    }

}