package com.katana.api.replies;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juan on 12/11/16.
 */
public class CallReplyPayloadTest {

    private CallReplyPayload callReplyPayload;

    @Before
    public void setup() {
        this.callReplyPayload = new CallReplyPayload();
    }

    @Test
    public void getCommandReply() {
        // SETUP
        CallReplyPayload.CallCommandReply command = new CallReplyPayload.CallCommandReply();
        this.callReplyPayload.setCommandReply(command);

        // ACTION
        CallReplyPayload.CallCommandReply commandObtained = this.callReplyPayload.getCommandReply();

        // EXPECTED
        Assert.assertEquals(command, commandObtained);
    }

    @Test
    public void setCommandReply() {
        // SETUP
        CallReplyPayload.CallCommandReply command = new CallReplyPayload.CallCommandReply();

        // ACTION
        this.callReplyPayload.setCommandReply(command);

        // EXPECTED
        Assert.assertEquals(command, this.callReplyPayload.getCommandReply());
    }

}