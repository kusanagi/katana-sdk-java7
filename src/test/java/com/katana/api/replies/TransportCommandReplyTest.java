package com.katana.api.replies;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juan on 12/11/16.
 */
public class TransportCommandReplyTest {

    private TransportReplyPayload.TransportCommandReply transportCommandReply;

    @Before
    public void setup() {
        this.transportCommandReply = new TransportReplyPayload.TransportCommandReply();
    }

    @Test
    public void getName() {
        // SETUP
        String name = "Name";
        this.transportCommandReply.setName(name);

        // ACTION
        String nameObtained = this.transportCommandReply.getName();

        // EXPECTED
        Assert.assertEquals(name, nameObtained);
    }

    @Test
    public void setName() {
        // SETUP
        String name = "Name";

        // ACTION
        this.transportCommandReply.setName(name);

        // EXPECTED
        Assert.assertEquals(name, this.transportCommandReply.getName());
    }

    @Test
    public void getResult() {
        // SETUP
        TransportReplyPayload.TransportResult result = new TransportReplyPayload.TransportResult();
        this.transportCommandReply.setResult(result);

        // ACTION
        TransportReplyPayload.TransportResult resultObtained = this.transportCommandReply.getResult();

        // EXPECTED
        Assert.assertEquals(result, resultObtained);
    }

    @Test
    public void setResult() {
        // SETUP
        TransportReplyPayload.TransportResult result = new TransportReplyPayload.TransportResult();

        // ACTION
        this.transportCommandReply.setResult(result);

        // EXPECTED
        Assert.assertEquals(result, this.transportCommandReply.getResult());
    }

}