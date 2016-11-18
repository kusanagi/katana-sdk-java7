package com.katana.api.replies;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juan on 12/11/16.
 */
public class CallCommandReplyTest {

    private CallReplyPayload.CallCommandReply callCommandReply;

    @Before
    public void setup() {
        this.callCommandReply = new CallReplyPayload.CallCommandReply();
    }

    @Test
    public void getName() {
        // SETUP
        String name = "Name";
        this.callCommandReply.setName(name);

        // ACTION
        String nameObtained = this.callCommandReply.getName();

        // EXPECTED
        Assert.assertEquals(name, nameObtained);
    }

    @Test
    public void setName() {
        // SETUP
        String name = "Name";

        // ACTION
        this.callCommandReply.setName(name);

        // EXPECTED
        Assert.assertEquals(name, this.callCommandReply.getName());
    }

    @Test
    public void getResult() {
        // SETUP
        CallReplyPayload.CallResult result = new CallReplyPayload.CallResult();
        this.callCommandReply.setResult(result);

        // ACTION
        CallReplyPayload.CallResult resultObtained = this.callCommandReply.getResult();

        // EXPECTED
        Assert.assertEquals(result, resultObtained);
    }

    @Test
    public void setResult() {
        // SETUP
        CallReplyPayload.CallResult result = new CallReplyPayload.CallResult();

        // ACTION
        this.callCommandReply.setResult(result);

        // EXPECTED
        Assert.assertEquals(result, this.callCommandReply.getResult());
    }

}