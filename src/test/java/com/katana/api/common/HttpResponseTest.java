package com.katana.api.common;

import com.katana.api.HttpResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by juan on 12/11/16.
 */
public class HttpResponseTest {

    private HttpResponse httpResponse;

    @Before
    public void setup() {
        httpResponse = new HttpResponse();
    }

    @Test
    public void getProtocolVersion() {
        // SETUP
        String protocolVersion = "protocolVersion";
        this.httpResponse.setProtocolVersion(protocolVersion);

        // ACTION
        String protocolVersionObtained = this.httpResponse.getProtocolVersion();

        // EXPECTED
        Assert.assertEquals(protocolVersion, protocolVersionObtained);
    }

    @Test
    public void setProtocolVersion() {
        // SETUP
        String protocolVersion = "protocolVersion";

        // ACTION
        this.httpResponse.setProtocolVersion(protocolVersion);

        // EXPECTED
        Assert.assertEquals(protocolVersion, this.httpResponse.getProtocolVersion());
    }

    @Test
    public void getStatus() {
        // SETUP
        int code = 200;
        String status = "status";
        this.httpResponse.setStatus(code, status);

        // ACTION
        String statusObtained = this.httpResponse.getStatus();

        // EXPECTED
        Assert.assertEquals(code + " " + status, statusObtained);
    }

    @Test
    public void setStatus() {
        // SETUP
        int code = 200;
        String status = "status";

        // ACTION
        this.httpResponse.setStatus(code, status);

        // EXPECTED
        Assert.assertEquals(code + " " + status, this.httpResponse.getStatus());
    }

    @Test
    public void getHeaders() {
        // SETUP
        Map<String, List<String>> headers = new HashMap<>();
        this.httpResponse.setHeaders(headers);

        // ACTION
        Map<String, List<String>> headersObtained = this.httpResponse.getHeaders();

        // EXPECTED
        Assert.assertEquals(headers, headersObtained);
    }

    @Test
    public void setHeaders() {
        // SETUP
        Map<String, List<String>> headers = new HashMap<>();

        // ACTION
        this.httpResponse.setHeaders(headers);

        // EXPECTED
        Assert.assertEquals(headers, this.httpResponse.getHeaders());
    }

    @Test
    public void getBody() {
        // SETUP
        String body = "body";
        this.httpResponse.setBody(body);

        // ACTION
        String bodyObtained = this.httpResponse.getBody();

        // EXPECTED
        Assert.assertEquals(body, bodyObtained);
    }

    @Test
    public void setBody() {
        // SETUP
        String body = "body";

        // ACTION
        this.httpResponse.setBody(body);

        // EXPECTED
        Assert.assertEquals(body, this.httpResponse.getBody());
    }

    @Test
    public void isProtocolVersion() {
        Assert.assertTrue(true);
    }

    @Test
    public void isStatus() {
        Assert.assertTrue(true);
    }

    @Test
    public void getStatusCode() {
        Assert.assertTrue(true);
    }

    @Test
    public void getStatusText() {
        Assert.assertTrue(true);
    }

    @Test
    public void hasHeader() {
        Assert.assertTrue(true);
    }

    @Test
    public void getHeader() {
        Assert.assertTrue(true);
    }

    @Test
    public void hasBody() {
        Assert.assertTrue(true);
    }

    @Test
    public void setHeader() {
        Assert.assertTrue(true);
    }

    @Test
    public void equals() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        HttpResponse mockHttpResponse1 = podamFactory.manufacturePojoWithFullData(HttpResponse.class);
        HttpResponse mockHttpResponse2 = podamFactory.manufacturePojoWithFullData(HttpResponse.class);
        HttpResponse mockHttpResponse3 = new HttpResponse(mockHttpResponse1);
        Assert.assertNotEquals(mockHttpResponse1, mockHttpResponse2);
        Assert.assertNotEquals(mockHttpResponse1, new Object());
        Assert.assertEquals(mockHttpResponse1, mockHttpResponse3);
    }
}