package com.katana.api.commands;

import com.katana.api.Action;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juan on 12/11/16.
 */
public class ActionCommandTest {

    private ActionCommandPayload.ActionCommand actionCommand;

    @Before
    public void setup() {
        this.actionCommand = new ActionCommandPayload.ActionCommand();
    }

    @Test
    public void getArgument() {
        // SETUP
        Action argument = new Action();
        this.actionCommand.setArgument(argument);

        // ACTION
        Action argumentObtained = this.actionCommand.getArgument();

        // EXPECTED
        Assert.assertEquals(argument, argumentObtained);
    }

    @Test
    public void setArgument() {
        // SETUP
        Action argument = new Action();

        // ACTION
        this.actionCommand.setArgument(argument);

        // EXPECTED
        Assert.assertEquals(argument, this.actionCommand.getArgument());
    }

}