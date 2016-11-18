package com.katana.api.commands;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juan on 12/11/16.
 */
public class ActionCommandPayloadTest {

    private ActionCommandPayload actionCommandPayload;

    @Before
    public void setup() {
        this.actionCommandPayload = new ActionCommandPayload();
    }

    @Test
    public void getCommand() {
        // SETUP
        ActionCommandPayload.ActionCommand command = new ActionCommandPayload.ActionCommand();
        this.actionCommandPayload.setCommand(command);

        // ACTION
        ActionCommandPayload.ActionCommand commandObtained = this.actionCommandPayload.getCommand();

        // EXPECTED
        Assert.assertEquals(command, commandObtained);
    }

    @Test
    public void setCommand() {
        // SETUP
        ActionCommandPayload.ActionCommand command = new ActionCommandPayload.ActionCommand();

        // ACTION
        this.actionCommandPayload.setCommand(command);

        // EXPECTED
        Assert.assertEquals(command, this.actionCommandPayload.getCommand());
    }

}