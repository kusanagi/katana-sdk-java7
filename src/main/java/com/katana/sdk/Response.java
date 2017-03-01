package com.katana.sdk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.api.Api;
import com.katana.api.component.Component;

import java.util.Map;

/**
 * Created by juan on 27/08/16.
 */
public class Response extends Api {

    @JsonProperty("m")
    private Meta meta;

    @JsonProperty("r")
    private HttpRequest httpRequest;

    @JsonProperty("R")
    private HttpResponse httpResponse;

    @JsonProperty("T")
    private Transport transport;

    public Response() {
        // Default constructor to make possible the serialization of this object.
    }

    public Response(Response other) {
        super(other);
        this.meta = other.meta;
        this.httpRequest = other.httpRequest;
        this.httpResponse = other.httpResponse;
        this.transport = other.transport;
    }

    /**
     * @param path
     * @param name
     * @param version
     * @param platformVersion
     * @param variables
     * @param isDebug
     */
    public Response(Component component, String path, String name, String version, String platformVersion, Map<String, String> variables, boolean isDebug) {
        super(component, path, name, version, platformVersion, variables, isDebug);
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
     * @param httpResponse
     */
    public void setHttpResponse(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    /**
     * @param transport
     */
    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    // SDK METHODS

    @JsonIgnore
    public String getGatewayProtocol() {
        return this.meta.getProtocol();
    }

    @JsonIgnore
    public String getGatewayAddress() {
        return this.meta.getGateway().get(1);
    }

    public HttpRequest getHttpRequest() {
        return this.httpRequest;
    }

    /**
     * @return Return the instance of the HttpResponse class which contains the HTTP semantics for the response to be
     * made from the Gateway component.
     */
    public HttpResponse getHttpResponse() {
        return httpResponse;
    }

    /**
     * @return Return an instance of the deserialize-only Transport interface.
     */
    public Transport getTransport() {
        return transport;
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

        Response response = (Response) o;

        if (getMeta() != null ? !getMeta().equals(response.getMeta()) : response.getMeta() != null) {
            return false;
        }
        if (getHttpRequest() != null ? !getHttpRequest().equals(response.getHttpRequest()) : response.getHttpRequest() != null) {
            return false;
        }
        if (getHttpResponse() != null ? !getHttpResponse().equals(response.getHttpResponse()) : response.getHttpResponse() != null) {
            return false;
        }
        return getTransport() != null ? getTransport().equals(response.getTransport()) : response.getTransport() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getMeta() != null ? getMeta().hashCode() : 0);
        result = 31 * result + (getHttpRequest() != null ? getHttpRequest().hashCode() : 0);
        result = 31 * result + (getHttpResponse() != null ? getHttpResponse().hashCode() : 0);
        result = 31 * result + (getTransport() != null ? getTransport().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "meta=" + meta +
                ", httpRequest=" + httpRequest +
                ", httpResponse=" + httpResponse +
                ", transport=" + transport +
                "} " + super.toString();
    }
}
