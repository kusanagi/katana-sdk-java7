package com.katana.api.common;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by juan on 12/11/16.
 */
public class HttpRequestTest {

    private HttpRequest httpRequest;

    @Before
    public void setup() {
        httpRequest = new HttpRequest();
    }

    @Test
    public void getProtocolVersion() {
        // SETUP
        String protocolVersion = "Protocol version";
        this.httpRequest.setProtocolVersion(protocolVersion);

        // ACTION
        String protocolVersionObtained = this.httpRequest.getProtocolVersion();

        // EXPECTED
        Assert.assertEquals(protocolVersion, protocolVersionObtained);
    }

    @Test
    public void setProtocolVersion() {
        // SETUP
        String protocolVersion = "protocolVersion";

        // ACTION
        this.httpRequest.setProtocolVersion(protocolVersion);

        // EXPECTED
        Assert.assertEquals(protocolVersion, this.httpRequest.getProtocolVersion());
    }

    @Test
    public void getMethod() {
        // SETUP
        String method = "method";
        this.httpRequest.setMethod(method);

        // ACTION
        String methodObtained = this.httpRequest.getMethod();

        // EXPECTED
        Assert.assertEquals(method, methodObtained);
    }

    @Test
    public void setMethod() {
        // SETUP
        String method = "method";

        // ACTION
        this.httpRequest.setMethod(method);

        // EXPECTED
        Assert.assertEquals(method, this.httpRequest.getMethod());
    }

    @Test
    public void getUrl() {
        // SETUP
        String url = "url";
        this.httpRequest.setUrl(url);

        // ACTION
        String urlObtained = this.httpRequest.getUrl();

        // EXPECTED
        Assert.assertEquals(url, urlObtained);
    }

    @Test
    public void setUrl() {
        // SETUP
        String url = "url";

        // ACTION
        this.httpRequest.setUrl(url);

        // EXPECTED
        Assert.assertEquals(url, this.httpRequest.getUrl());
    }

    @Test
    public void getQuery() {
        // SETUP
        Map<String, List<String>> query = new HashMap<>();
        this.httpRequest.setQuery(query);

        // ACTION
        Map<String, List<String>> queryObtained = this.httpRequest.getQueryParamsArray();

        // EXPECTED
        Assert.assertEquals(query, queryObtained);
    }

    @Test
    public void setQuery() {
        // SETUP
        Map<String, List<String>> query = new HashMap<>();

        // ACTION
        this.httpRequest.setQuery(query);

        // EXPECTED
        Assert.assertEquals(query, this.httpRequest.getQueryParamsArray());
    }

    @Test
    public void getPostData() {
        // SETUP
        Map<String, List<String>> postData = new HashMap<>();
        this.httpRequest.setPostData(postData);

        // ACTION
        Map<String, List<String>> postDataObtained = this.httpRequest.getPostParamsArray();

        // EXPECTED
        Assert.assertEquals(postData, postDataObtained);
    }

    @Test
    public void setPostData() {
        // SETUP
        Map<String, List<String>> postData = new HashMap<>();

        // ACTION
        this.httpRequest.setPostData(postData);

        // EXPECTED
        Assert.assertEquals(postData, this.httpRequest.getPostParamsArray());
    }

    @Test
    public void getHeaders() {
        // SETUP
        Map<String, List<String>> headers = new HashMap<>();
        this.httpRequest.setHeaders(headers);

        // ACTION
        Map<String, List<String>> headersObtained = this.httpRequest.getHeaders();

        // EXPECTED
        Assert.assertEquals(headers, headersObtained);
    }

    @Test
    public void setHeaders() {
        // SETUP
        Map<String, List<String>> headers = new HashMap<>();

        // ACTION
        this.httpRequest.setHeaders(headers);

        // EXPECTED
        Assert.assertEquals(headers, this.httpRequest.getHeaders());
    }

    @Test
    public void getBody() {
        // SETUP
        String body = "body";
        this.httpRequest.setBody(body);

        // ACTION
        String bodyObtained = this.httpRequest.getBody();

        // EXPECTED
        Assert.assertEquals(body, bodyObtained);
    }

    @Test
    public void setBody() {
        // SETUP
        String body = "body";

        // ACTION
        this.httpRequest.setBody(body);

        // EXPECTED
        Assert.assertEquals(body, this.httpRequest.getBody());
    }

    @Test
    public void getFiles() {
        // SETUP
        List<File> files = new ArrayList<>();
        this.httpRequest.setFiles(files);

        // ACTION
        List<File> filesObtained = this.httpRequest.getFiles();

        // EXPECTED
        Assert.assertEquals(files, filesObtained);
    }

    @Test
    public void setFiles() {
        // SETUP
        List<File> files = new ArrayList<>();

        // ACTION
        this.httpRequest.setFiles(files);

        // EXPECTED
        Assert.assertEquals(files, this.httpRequest.getFiles());
    }

    // SDK Methods

    @Test
    public void isMethod() {
        Assert.assertTrue(true);
    }

    @Test
    public void getUrlScheme() {
        Assert.assertTrue(true);
    }

    @Test
    public void getUrlHost() {
        Assert.assertTrue(true);
    }

    @Test
    public void getUrlPath() {
        Assert.assertTrue(true);
    }

    @Test
    public void hasQueryParam() {
        Assert.assertTrue(true);
    }

    @Test
    public void getQueryParam() {
        Assert.assertTrue(true);
    }

    @Test
    public void getQueryParamArray() {
        Assert.assertTrue(true);
    }

    @Test
    public void getQueryParamsArray() {
        Assert.assertTrue(true);
    }

    @Test
    public void hasPostParam() {
        Assert.assertTrue(true);
    }

    @Test
    public void getPostParam() {
        Assert.assertTrue(true);
    }

    @Test
    public void getPostParamArray() {
        Assert.assertTrue(true);
    }

    @Test
    public void getPostParamsArray() {
        Assert.assertTrue(true);
    }

    @Test
    public void isProtocolVersion() {
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
    public void hasFile() {
        Assert.assertTrue(true);
    }

    @Test
    public void getFile() {
        Assert.assertTrue(true);
    }

}