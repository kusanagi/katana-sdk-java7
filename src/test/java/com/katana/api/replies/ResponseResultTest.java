package com.katana.api.replies;

import com.katana.api.HttpResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juan on 12/11/16.
 */
public class ResponseResultTest {

    private ResponseReplyPayload.ResponseResult responseResult;

    @Before
    public void setup() {
        this.responseResult = new ResponseReplyPayload.ResponseResult();
    }

    @Test
    public void getResponse() {
        // SETUP
        HttpResponse response = new HttpResponse();
        this.responseResult.setHttpResponse(response);

        // ACTION
        HttpResponse responseObtained = this.responseResult.getHttpResponse();

        // EXPECTED
        Assert.assertEquals(response, responseObtained);
    }

    @Test
    public void setResponse() {
        // SETUP
        HttpResponse response = new HttpResponse();

        // ACTION
        this.responseResult.setHttpResponse(response);

        // EXPECTED
        Assert.assertEquals(response, this.responseResult.getHttpResponse());
    }

}