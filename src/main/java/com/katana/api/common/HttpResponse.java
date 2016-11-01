package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.api.replies.CommandReplyResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by juan on 14/09/16.
 * Katana Java SDK
 */
public class HttpResponse implements CommandReplyResult {
    @JsonProperty("v")
    private String version;

    @JsonProperty("s")
    private String status;

    @JsonProperty("h")
    private Map<String, String> headers;

    @JsonProperty("b")
    private String body;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, String> getHeaders() {
        if (this.headers == null) {
            this.headers = new HashMap<>();
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HttpResponse)) {
            return false;
        }

        HttpResponse that = (HttpResponse) o;

        if (!getVersion().equals(that.getVersion())) {
            return false;
        }
        if (!getStatus().equals(that.getStatus())) {
            return false;
        }
        if (!getHeaders().equals(that.getHeaders())) {
            return false;
        }
        return getBody() != null ? getBody().equals(that.getBody()) : that.getBody() == null;

    }

    @Override
    public int hashCode() {
        int result = getVersion().hashCode();
        result = 31 * result + getStatus().hashCode();
        result = 31 * result + getHeaders().hashCode();
        result = 31 * result + getBody().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "version='" + version + '\'' +
                ", status='" + status + '\'' +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                '}';
    }
}
