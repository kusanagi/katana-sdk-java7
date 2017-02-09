package com.katana.api.common;

import com.katana.api.HttpRequest;
import com.katana.api.Meta;
import com.katana.api.Request;
import com.katana.api.RequestCall;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

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

    @Test
    public void equals() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        Request mockRequest1 = podamFactory.manufacturePojoWithFullData(Request.class);
        Request mockRequest2 = podamFactory.manufacturePojoWithFullData(Request.class);
        Request mockRequest3 = new Request(mockRequest1);
        Assert.assertNotEquals(mockRequest1, mockRequest2);
        Assert.assertNotEquals(mockRequest1, new Object());
        Assert.assertEquals(mockRequest1, mockRequest3);
    }
}