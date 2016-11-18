package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created by juan on 27/08/16.
 */
public class Request extends Api {
    @JsonProperty("t")
    private int type;

    @JsonProperty("m")
    private Meta meta;

    @JsonProperty("r")
    private HttpRequest httpRequest;

    @JsonProperty("c")
    private Call call;

    public Request() {
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
    public Request(String path, String name, String version, String platformVersion, Map<String, String> variables, boolean isDebug) {
        super(path, name, version, platformVersion, variables, isDebug);
    }

    /**
     * @return
     */
    public int getType() {
        return this.type;
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
        return this.meta;
    }

    /**
     * @param meta
     */
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    /**
     * @return Return the instance of the HttpRequest class which contains the HTTP semantics of the request made to
     * the Gateway component.
     */
    public HttpRequest getHttpRequest() {
        return this.httpRequest;
    }

    /**
     * @param httpRequest
     */
    public void setHttpRequest(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    /**
     * @return
     */
    public Call getCall() {
        return this.call;
    }

    /**
     * @param call
     */
    public void setCall(Call call) {
        this.call = call;
    }

    // SDK Methods

    /**
     * @return Return the name currently defined for the Service, or an empty string if not defined.
     */
    public String getServiceName() {
        return this.call.getService();
    }

    /**
     * Set the name for the Service with the value specified by the REQUIRED service argument.
     *
     * @param name Name of the service
     * @return Return true if the operation was successful
     */
    public boolean setServiceName(String name) {
        this.call.setService(name);
        return true;
    }

    /**
     * @return Return the version currently defined for the Service, or an empty string if not defined.
     */
    public String getServiceVersion() {
        return this.call.getVersion();
    }

    /**
     * Set the version for the Service with the value specified by the REQUIRED version argument.
     *
     * @param version Version for the service
     * @return Return true if the operation was successful
     */
    public boolean setServiceVersion(String version) {
        this.call.setVersion(version);
        return true;
    }

    /**
     * @return Return the name currently defined for the Service action, or an empty string if not defined.
     */
    public String getActionName() {
        return this.call.getAction();
    }

    /**
     * Set the name for the Service action with the value specified by the REQUIRED action argument.
     *
     * @param action Action name
     * @return Return true if the operation was successful
     */
    public boolean setActionName(String action) {
        this.call.setAction(action);
        return true;
    }

    /**
     * @param code Response code
     * @param text Response text
     * @return Return a new instance of the Response class, setting the HTTP status code with the value specified by the
     * REQUIRED code argument and the status text with the REQUIRED text argument.
     */
    public Response newResponse(int code, String text) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus(code + " " + text);

        Response response = new Response();
        response.setHttpResponse(httpResponse);
        return response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Request)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Request request1 = (Request) o;

        if (getType() != request1.getType()) {
            return false;
        }
        if (!getMeta().equals(request1.getMeta())) {
            return false;
        }
        if (!getHttpRequest().equals(request1.getHttpRequest())) {
            return false;
        }
        return getCall().equals(request1.getCall());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getType();
        result = 31 * result + getMeta().hashCode();
        result = 31 * result + getHttpRequest().hashCode();
        result = 31 * result + getCall().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Request{" +
                "type=" + type +
                ", meta=" + meta +
                ", httpRequest=" + httpRequest +
                ", call=" + call +
                "} " + super.toString();
    }
}
