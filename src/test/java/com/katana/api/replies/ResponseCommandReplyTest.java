package com.katana.api.replies;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juan on 12/11/16.
 */
public class ResponseCommandReplyTest {

    private ResponseReplyPayload.ResponseCommandReply responseCommandReply;

    @Before
    public void setup() {
        this.responseCommandReply = new ResponseReplyPayload.ResponseCommandReply();
    }

    @Test
    public void getName() {
        // SETUP
        String name = "Name";
        this.responseCommandReply.setName(name);

        // ACTION
        String nameObtained = this.responseCommandReply.getName();

        // EXPECTED
        Assert.assertEquals(name, nameObtained);
    }

    @Test
    public void setName() {
        // SETUP
        String name = "Name";

        // ACTION
        this.responseCommandReply.setName(name);

        // EXPECTED
        Assert.assertEquals(name, this.responseCommandReply.getName());
    }

    @Test
    public void getResult() {
        // SETUP
        ResponseReplyPayload.ResponseResult result = new ResponseReplyPayload.ResponseResult();
        this.responseCommandReply.setResult(result);

        // ACTION
        ResponseReplyPayload.ResponseResult resultObtained = this.responseCommandReply.getResult();

        // EXPECTED
        Assert.assertEquals(result, resultObtained);
    }

    @Test
    public void setResult() {
        // SETUP
        ResponseReplyPayload.ResponseResult result = new ResponseReplyPayload.ResponseResult();

        // ACTION
        this.responseCommandReply.setResult(result);

        // EXPECTED
        Assert.assertEquals(result, this.responseCommandReply.getResult());
    }

}