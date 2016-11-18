package com.katana.api.common;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juan on 12/11/16.
 */
public class RequestTest {

    private Request request;

    @Before
    public void setup() {
        this.request = new Request();
    }

    @Test
    public void getType() {
        // SETUP
        String type = "Type";
        this.request.setType(type);

        // ACTION
        String typeObtained = this.request.getType();

        // EXPECTED
        Assert.assertEquals(type, typeObtained);
    }

    @Test
    public void setType() {
        // SETUP
        String type = "Type";

        // ACTION
        this.request.setType(type);

        // EXPECTED
        Assert.assertEquals(type, this.request.getType());
    }

    @Test
    public void getMeta() {
        // SETUP
        Meta meta = new Meta();
        this.request.setMeta(meta);

        // ACTION
        Meta metaObtained = this.request.getMeta();

        // EXPECTED
        Assert.assertEquals(meta, metaObtained);
    }

    @Test
    public void setMeta() {
        // SETUP
        Meta meta = new Meta();

        // ACTION
        this.request.setMeta(meta);

        // EXPECTED
        Assert.assertEquals(meta, this.request.getMeta());
    }

    @Test
    public void getHttpRequest() {
        // SETUP
        HttpRequest httpRequest = new HttpRequest();
        this.request.setHttpRequest(httpRequest);

        // ACTION
        HttpRequest httpRequestObtained = this.request.getHttpRequest();

        // EXPECTED
        Assert.assertEquals(httpRequest, httpRequestObtained);
    }

    @Test
    public void setHttpRequest() {
        // SETUP
        HttpRequest httpRequest = new HttpRequest();

        // ACTION
        this.request.setHttpRequest(httpRequest);

        // EXPECTED
        Assert.assertEquals(httpRequest, this.request.getHttpRequest());
    }

    @Test
    public void getCall() {
        // SETUP
        RequestCall requestCall = new RequestCall();
        this.request.setRequestCall(requestCall);

        // ACTION
        RequestCall requestCallObtained = this.request.getRequestCall();

        // EXPECTED
        Assert.assertEquals(requestCall, requestCallObtained);
    }

    @Test
    public void setCall() {
        // SETUP
        RequestCall requestCall = new RequestCall();

        // ACTION
        this.request.setRequestCall(requestCall);

        // EXPECTED
        Assert.assertEquals(requestCall, this.request.getRequestCall());
    }

    @Test
    public void getServiceName() {
        Assert.assertTrue(true);
    }

    @Test
    public void setServiceNameName() {
        Assert.assertTrue(true);
    }

    @Test
    public void getServiceVersion() {
        Assert.assertTrue(true);
    }

    @Test
    public void setServiceVersionVersion() {
        Assert.assertTrue(true);
    }

    @Test
    public void getActionName() {
        Assert.assertTrue(true);
    }

    @Test
    public void setActionName() {
        Assert.assertTrue(true);
    }

    @Test
    public void newResponse() {
        Assert.assertTrue(true);
    }
}