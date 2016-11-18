package com.katana.api.commands;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juan on 12/11/16.
 */
public class RequestCommandPayloadTest {

    private RequestCommandPayload requestCommandPayload;

    @Before
    public void setup() {
        this.requestCommandPayload = new RequestCommandPayload();
    }

    @Test
    public void getCommand() {
        // SETUP
        RequestCommandPayload.RequestCommand command = new RequestCommandPayload.RequestCommand();
        this.requestCommandPayload.setCommand(command);

        // ACTION
        RequestCommandPayload.RequestCommand commandObtained = this.requestCommandPayload.getCommand();

        // EXPECTED
        Assert.assertEquals(command, commandObtained);
    }

    @Test
    public void setCommand() {
        // SETUP
        RequestCommandPayload.RequestCommand command = new RequestCommandPayload.RequestCommand();

        // ACTION
        this.requestCommandPayload.setCommand(command);

        // EXPECTED
        Assert.assertEquals(command, this.requestCommandPayload.getCommand());
    }

}