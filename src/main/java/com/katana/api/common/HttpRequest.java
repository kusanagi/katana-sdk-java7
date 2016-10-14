package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by juan on 14/09/16.
 * Katana Java SDK
 */
public class HttpRequest {
    @JsonProperty("v")
    private String version;

    @JsonProperty("m")
    private String method;

    @JsonProperty("u")
    private String url;

    @JsonProperty("q")
    private Map<String, String> query;

    @JsonProperty("p")
    private Map<String, String> postData;

    @JsonProperty("h")
    private Map<String, String> headers;

    @JsonProperty("b")
    private String body;

    @JsonProperty("f")
    private File[] files;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getQuery() {
        return query;
    }

    public void setQuery(Map<String, String> query) {
        this.query = query;
    }

    public Map<String, String> getPostData() {
        return postData;
    }

    public void setPostData(Map<String, String> postData) {
        this.postData = postData;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public File[] getFiles() {
        return files;
    }

    public void setFiles(File[] files) {
        this.files = files;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HttpRequest)) return false;

        HttpRequest that = (HttpRequest) o;

        if (!getVersion().equals(that.getVersion())) return false;
        if (!getMethod().equals(that.getMethod())) return false;
        if (!getUrl().equals(that.getUrl())) return false;
        if (!getQuery().equals(that.getQuery())) return false;
        if (!getPostData().equals(that.getPostData())) return false;
        if (!getHeaders().equals(that.getHeaders())) return false;
        if (!getBody().equals(that.getBody())) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(getFiles(), that.getFiles());

    }

    @Override
    public int hashCode() {
        int result = getVersion().hashCode();
        result = 31 * result + getMethod().hashCode();
        result = 31 * result + getUrl().hashCode();
        result = 31 * result + getQuery().hashCode();
        result = 31 * result + getPostData().hashCode();
        result = 31 * result + getHeaders().hashCode();
        result = 31 * result + getBody().hashCode();
        result = 31 * result + Arrays.hashCode(getFiles());
        return result;
    }
}
