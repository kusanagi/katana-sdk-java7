package com.katana.api.replies;

import com.katana.api.RequestCall;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juan on 12/11/16.
 */
public class RequestCallResultTest {

    private CallReplyPayload.CallResult callResult;

    @Before
    public void setup() {
        this.callResult = new CallReplyPayload.CallResult();
    }

    @Test
    public void getCall() {
        // SETUP
        RequestCall requestCall = new RequestCall();
        this.callResult.setRequestCall(requestCall);

        // ACTION
        RequestCall requestCallObtained = this.callResult.getRequestCall();

        // EXPECTED
        Assert.assertEquals(requestCall, requestCallObtained);
    }

    @Test
    public void setCall() {
        // SETUP
        RequestCall requestCall = new RequestCall();

        // ACTION
        this.callResult.setRequestCall(requestCall);

        // EXPECTED
        Assert.assertEquals(requestCall, this.callResult.getRequestCall());
    }

}