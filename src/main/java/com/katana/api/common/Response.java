package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.api.replies.CommandReplyResult;

import java.util.Map;

/**
 * Created by juan on 27/08/16.
 */
public class Response extends Api {
    @JsonProperty("t")
    private int type;

    @JsonProperty("m")
    private Meta meta;

    @JsonProperty("R")
    private HttpResponse httpResponse;

    @JsonProperty("T")
    private Transport transport;

    public Response() {
        // Default constructor to make possible the serialization of this object.
    }

    /**
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
     * @return
     */
    public int getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return
     */
    public Meta getMeta() {
        return meta;
    }

    /**
     * @param meta
     */
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    /**
     * @return Return the instance of the HttpResponse class which contains the HTTP semantics for the response to be
     * made from the Gateway component.
     */
    public HttpResponse getHttpResponse() {
        return httpResponse;
    }

    /**
     * @param httpResponse
     */
    public void setHttpResponse(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    /**
     * @return Return an instance of the read-only Transport interface.
     */
    public Transport getTransport() {
        return transport;
    }

    /**
     * @param transport
     */
    public void setTransport(Transport transport) {
        this.transport = transport;
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
        result = 31 * result + getType();
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
