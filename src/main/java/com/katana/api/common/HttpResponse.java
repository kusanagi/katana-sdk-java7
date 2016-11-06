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

    /**
     * @return Return the value set for the HTTP version of the response.
     */
    public String getProtocolVersion() {
        return version;
    }

    /**
     * Set the value specified by the REQUIRED version argument as the HTTP version of the response.
     * @param version Protocol version to set
     */
    public void setProtocolVersion(String version) {
        this.version = version;
    }

    /**
     * Status getter
     * @return Return the value set for the HTTP status code and text of the response.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the values specified by the REQUIRED code argument as the HTTP status code and the REQUIRED case insensitive
     * text argument as the HTTP status text of the response.
     * @param status HTTP status text of the response.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Headers getter
     * @return Return an object with the HTTP headers defined for the response, where each property name is the header
     * name, and the value the header value as a string.
     */
    public Map<String, String> getHeaders() {
        if (this.headers == null) {
            this.headers = new HashMap<>();
        }
        return headers;
    }

    /**
     * Set a HTTP header for the response, with the name specified by the REQUIRED name argument and the value specified by the REQUIRED value argument.
     * @param headers Headers to set
     */
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    /**
     * Body getter
     * @return Return the content set for the body of the HTTP response.
     */
    public String getBody() {
        return body;
    }

    /**
     * Set the content for the body of the HTTP response. If the content argument is not specified the body of the
     * response SHOULD be reset to an empty string.
     * @param body Body to set
     */
    public void setBody(String body) {
        this.body = body;
    }

    // SDK Methods

    /**
     * Determine if the HTTP version set for the response is equal to that specified by the REQUIRED version argument.
     * @param version Version of the protocol
     * @return Return true if the response has the version specified in the parameter
     */
    public boolean isProtocolVersion(String version){
        return this.version.equals(version);
    }

    /**
     * Determine if the HTTP status code and text set for the response is equal to that specified by the REQUIRED case
     * insensitive status argument.
     * @param status Http status code and text.
     * @return Return true if the status is the same as the one specified in the argument.
     */
    public boolean isStatus(String status){
        return this.status.equals(status);
    }

    /**
     *
     * @return Return the value set for the HTTP status code of the response.
     */
    public int getStatusCode(){
        return Integer.valueOf(status.split(" ")[0]);
    }

    /**
     *
     * @return Return the value set for the HTTP status text of the response.
     */
    public String getStatusText(){
        return status.split(" ")[1];
    }

    /**
     * Determine if the HTTP header specified by the REQUIRED case sensitive name argument has been set for the response.
     * @param name Header name
     * @return Return true if the response contains the header specified in the parameter
     */
    public boolean hasHeader(String name){
        return this.headers.containsKey(name);
    }

    /**
     *
     * @param name Name of the header
     * @return Return the value of the HTTP header with the name specified by the REQUIRED case sensitive name
     * argument, or an empty string if the header does not exist.
     */
    public String getHeader(String name){
        return this.headers.get(name);
    }

    /**
     * Determine if the HTTP response has content defined for the response body.
     * @return Return true if the response has a body
     */
    public boolean hasBody(){
        //TODO review this method
        return this.body != null;
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

        if (!getProtocolVersion().equals(that.getProtocolVersion())) {
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
        int result = getProtocolVersion().hashCode();
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
