package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;
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
    private Map<String, List<String>> headers;

    @JsonProperty("b")
    private String body;

    @JsonProperty("f")
    private File[] files;

    /**
     *
     * @return
     */
    public String getVersion() {
        return version;
    }

    /**
     *
     * @param version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     *
     * @return
     */
    public String getMethod() {
        return method;
    }

    /**
     *
     * @param method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     *
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     */
    public Map<String, String> getQuery() {
        return query;
    }

    /**
     *
     * @param query
     */
    public void setQuery(Map<String, String> query) {
        this.query = query;
    }

    /**
     *
     * @return
     */
    public Map<String, String> getPostData() {
        return postData;
    }

    /**
     *
     * @param postData
     */
    public void setPostData(Map<String, String> postData) {
        this.postData = postData;
    }

    /**
     *
     * @return
     */
    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    /**
     *
     * @param headers
     */
    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    /**
     *
     * @return
     */
    public String getBody() {
        return body;
    }

    /**
     *
     * @param body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     *
     * @return
     */
    public File[] getFiles() {
        return files;
    }

    /**
     *
     * @param files
     */
    public void setFiles(File[] files) {
        this.files = files;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HttpRequest)) {
            return false;
        }

        HttpRequest that = (HttpRequest) o;

        if (!getVersion().equals(that.getVersion())) {
            return false;
        }
        if (!getMethod().equals(that.getMethod())) {
            return false;
        }
        if (!getUrl().equals(that.getUrl())) {
            return false;
        }
        if (!getQuery().equals(that.getQuery())) {
            return false;
        }
        if (!getPostData().equals(that.getPostData())) {
            return false;
        }
        if (!getHeaders().equals(that.getHeaders())) {
            return false;
        }
        if (!getBody().equals(that.getBody())) {
            return false;
        }
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

    @Override
    public String toString() {
        return "HttpRequest{" +
                "version='" + version + '\'' +
                ", method='" + method + '\'' +
                ", url='" + url + '\'' +
                ", query=" + query +
                ", postData=" + postData +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                ", files=" + Arrays.toString(files) +
                '}';
    }
}
