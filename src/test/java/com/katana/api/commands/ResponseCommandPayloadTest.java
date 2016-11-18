package com.katana.api.commands;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juan on 12/11/16.
 */
public class ResponseCommandPayloadTest {

    private ResponseCommandPayload responseCommandPayload;

    @Before
    public void setup() {
        this.responseCommandPayload = new ResponseCommandPayload();
    }

    @Test
    public void getCommand() {
        // SETUP
        ResponseCommandPayload.ResponseCommand command = new ResponseCommandPayload.ResponseCommand();
        this.responseCommandPayload.setCommand(command);

        // ACTION
        ResponseCommandPayload.ResponseCommand commandObtained = this.responseCommandPayload.getCommand();

        // EXPECTED
        Assert.assertEquals(command, commandObtained);
    }

    @Test
    public void setCommand() {
        // SETUP
        ResponseCommandPayload.ResponseCommand command = new ResponseCommandPayload.ResponseCommand();

        // ACTION
        this.responseCommandPayload.setCommand(command);

        // EXPECTED
        Assert.assertEquals(command, this.responseCommandPayload.getCommand());
    }

}