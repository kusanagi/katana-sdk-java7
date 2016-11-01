package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created by juan on 27/08/16.
 */
public class Response extends Api {
    @JsonProperty("T")
    private String type;

    @JsonProperty("m")
    private Meta meta;

    @JsonProperty("R")
    private HttpResponse httpResponse;

    @JsonProperty("t")
    private Transport transport;

    public Response() {
        // Default constructor to make possible the serialization of this object.
    }

    /**
     *
     * @param path
     * @param name
     * @param version
     * @param platformVersion
     * @param variables
     * @param isDebug
     */
    public Response(String path, String name, String version, String platformVersion, Map<String, String> variables, boolean isDebug) {
        super(path, name, version, platformVersion, variables, isDebug);
    }

    /**
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     */
    public Meta getMeta() {
        return meta;
    }

    /**
     *
     * @param meta
     */
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    /**
     *
     * @return
     */
    public HttpResponse getHttpResponse() {
        return httpResponse;
    }

    /**
     *
     * @param httpResponse
     */
    public void setHttpResponse(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    /**
     *
     * @return
     */
    public Transport getTransport() {
        return transport;
    }

    /**
     *
     * @param transport
     */
    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    // SDK Methods

    /**
     *
     * @param version
     * @return
     */
    public boolean isProtocolVersion(String version) {
        return true;
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public String getProtocolVersion() {
        return this.httpResponse.getVersion();
    }

    /**
     *
     * @param version
     * @return
     */
    public boolean setProtocolVersion(String version) {
        this.httpResponse.setVersion(version);
        return true;
    }

    /**
     *
     * @param status
     * @return
     */
    public boolean isStatus(String status) {
        return true;
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public String getStatus() {
        return this.httpResponse.getStatus();
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public int getStatusCode() {
        String statusCode = this.httpResponse.getStatus().split(" ")[0];
        return Integer.valueOf(statusCode);
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public String getStatusText() {
        return this.httpResponse.getStatus().split(" ")[1];
    }

    /**
     *
     * @param code
     * @param text
     * @return
     */
    public boolean setStatus(int code, String text) {
        this.httpResponse.setStatus(code + " " + text);
        return true;
    }

    /**
     *
     * @param name
     * @return
     */
    public boolean hasHeader(String name) {
        return this.httpResponse.getHeaders().get(name) != null;
    }

    /**
     *
     * @param name
     * @return
     */
    @JsonIgnore
    public String getHeader(String name) {
        return this.httpResponse.getHeaders().get(name);
    }

    /**
     *
     * @param key
     * @param value
     */
    public void setHeader(String key, String value) {
        this.httpResponse.getHeaders().put(key, value);
    }

    /**
     *
     * @return
     */
    public boolean hasBody() {
        return this.httpResponse.getBody() != null;
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public String getBody() {
        return this.httpResponse.getBody();
    }

    /**
     *
     * @param body
     * @return
     */
    public boolean setBody(String body) {
        this.httpResponse.setBody(body);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Response)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Response response1 = (Response) o;

        if (getType() != response1.getType()) {
            return false;
        }
        if (!getMeta().equals(response1.getMeta())) {
            return false;
        }
        if (!getHttpResponse().equals(response1.getHttpResponse())) {
            return false;
        }
        return getTransport().equals(response1.getTransport());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getType().hashCode();
        result = 31 * result + getMeta().hashCode();
        result = 31 * result + getHttpResponse().hashCode();
        result = 31 * result + getTransport().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "type=" + type +
                ", meta=" + meta +
                ", httpResponse=" + httpResponse +
                ", transport=" + transport +
                "} " + super.toString();
    }
}
