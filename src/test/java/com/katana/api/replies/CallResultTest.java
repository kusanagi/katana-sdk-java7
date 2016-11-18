package com.katana.api.replies;

import com.katana.api.common.Call;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juan on 12/11/16.
 */
public class CallResultTest {

    private CallReplyPayload.CallResult callResult;

    @Before
    public void setup() {
        this.callResult = new CallReplyPayload.CallResult();
    }

    @Test
    public void getCall() {
        // SETUP
        Call call = new Call();
        this.callResult.setCall(call);

        // ACTION
        Call callObtained = this.callResult.getCall();

        // EXPECTED
        Assert.assertEquals(call, callObtained);
    }

    @Test
    public void setCall() {
        // SETUP
        Call call = new Call();

        // ACTION
        this.callResult.setCall(call);

        // EXPECTED
        Assert.assertEquals(call, this.callResult.getCall());
    }

}