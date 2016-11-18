package com.katana.api.commands;

import com.katana.api.common.Request;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juan on 12/11/16.
 */
public class RequestCommandTest {

    private RequestCommandPayload.RequestCommand requestCommand;

    @Before
    public void setup() {
        this.requestCommand = new RequestCommandPayload.RequestCommand();
    }

    @Test
    public void getArgument() {
        // SETUP
        Request argument = new Request();
        this.requestCommand.setArgument(argument);

        // ACTION
        Request argumentObtained = this.requestCommand.getArgument();

        // EXPECTED
        Assert.assertEquals(argument, argumentObtained);
    }

    @Test
    public void setArgument() {
        // SETUP
        Request argument = new Request();

        // ACTION
        this.requestCommand.setArgument(argument);

        // EXPECTED
        Assert.assertEquals(argument, this.requestCommand.getArgument());
    }

}