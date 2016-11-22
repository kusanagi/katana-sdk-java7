package com.katana.api.common;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Created by juan on 12/11/16.
 */
public class ResponseTest {

    private Response response;

    @Before
    public void setup() {
        this.response = new Response();
    }

    @Test
    public void getType() {
        // SETUP
        int type = 2;
        this.response.setType(type);

        // ACTION
        int typeObtained = this.response.getType();

        // EXPECTED
        Assert.assertEquals(type, typeObtained);
    }

    @Test
    public void setType() {
        // SETUP
        int type = 2;

        // ACTION
        this.response.setType(type);

        // EXPECTED
        Assert.assertEquals(type, this.response.getType());
    }

    @Test
    public void getMeta() {
        // SETUP
        Meta meta = new Meta();
        this.response.setMeta(meta);

        // ACTION
        Meta metaObtained = this.response.getMeta();

        // EXPECTED
        Assert.assertEquals(meta, metaObtained);
    }

    @Test
    public void setMeta() {
        // SETUP
        Meta meta = new Meta();

        // ACTION
        this.response.setMeta(meta);

        // EXPECTED
        Assert.assertEquals(meta, this.response.getMeta());
    }

    @Test
    public void getHttpResponse() {
        // SETUP
        HttpResponse httpResponse = new HttpResponse();
        this.response.setHttpResponse(httpResponse);

        // ACTION
        HttpResponse httpResponseObtained = this.response.getHttpResponse();

        // EXPECTED
        Assert.assertEquals(httpResponse, httpResponseObtained);
    }

    @Test
    public void setHttpResponse() {
        // SETUP
        HttpResponse httpResponse = new HttpResponse();

        // ACTION
        this.response.setHttpResponse(httpResponse);

        // EXPECTED
        Assert.assertEquals(httpResponse, this.response.getHttpResponse());
    }

    @Test
    public void getTransport() {
        // SETUP
        Transport transport = new Transport();
        this.response.setTransport(transport);

        // ACTION
        Transport transportObtained = this.response.getTransport();

        // EXPECTED
        Assert.assertEquals(transport, transportObtained);
    }

    @Test
    public void setTransport() {
        // SETUP
        Transport transport = new Transport();

        // ACTION
        this.response.setTransport(transport);

        // EXPECTED
        Assert.assertEquals(transport, this.response.getTransport());
    }

    @Test
    public void equals() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        Response mockResponse1 = podamFactory.manufacturePojoWithFullData(Response.class);
        Response mockResponse2 = podamFactory.manufacturePojoWithFullData(Response.class);
        Response mockResponse3 = new Response(mockResponse1);
        Assert.assertNotEquals(mockResponse1, mockResponse2);
        Assert.assertNotEquals(mockResponse1, new Object());
        Assert.assertEquals(mockResponse1, mockResponse3);
    }
}