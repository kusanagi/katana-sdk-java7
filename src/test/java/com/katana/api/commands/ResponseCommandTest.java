package com.katana.api.commands;

import com.katana.api.common.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juan on 12/11/16.
 */
public class ResponseCommandTest {

    private ResponseCommandPayload.ResponseCommand responseCommand;

    @Before
    public void setup() {
        this.responseCommand = new ResponseCommandPayload.ResponseCommand();
    }

    @Test
    public void getArgument() {
        // SETUP
        Response argument = new Response();
        this.responseCommand.setArgument(argument);

        // ACTION
        Response argumentObtained = this.responseCommand.getArgument();

        // EXPECTED
        Assert.assertEquals(argument, argumentObtained);
    }

    @Test
    public void setArgument() {
        // SETUP
        Response argument = new Response();

        // ACTION
        this.responseCommand.setArgument(argument);

        // EXPECTED
        Assert.assertEquals(argument, this.responseCommand.getArgument());
    }

}