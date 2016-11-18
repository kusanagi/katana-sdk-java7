package com.katana.api.replies;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juan on 12/11/16.
 */
public class TransportReplyPayloadTest {

    private TransportReplyPayload transportReplyPayload;

    @Before
    public void setup() {
        this.transportReplyPayload = new TransportReplyPayload();
    }

    @Test
    public void getCommandReply() {
        // SETUP
        TransportReplyPayload.TransportCommandReply command = new TransportReplyPayload.TransportCommandReply();
        this.transportReplyPayload.setCommandReply(command);

        // ACTION
        TransportReplyPayload.TransportCommandReply commandObtained = this.transportReplyPayload.getCommandReply();

        // EXPECTED
        Assert.assertEquals(command, commandObtained);
    }

    @Test
    public void setCommandReply() {
        // SETUP
        TransportReplyPayload.TransportCommandReply command = new TransportReplyPayload.TransportCommandReply();

        // ACTION
        this.transportReplyPayload.setCommandReply(command);

        // EXPECTED
        Assert.assertEquals(command, this.transportReplyPayload.getCommandReply());
    }

}