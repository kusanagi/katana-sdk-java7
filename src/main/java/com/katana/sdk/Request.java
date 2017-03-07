package com.katana.sdk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.api.Api;
import com.katana.api.component.Component;
import com.katana.api.component.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by juan on 27/08/16.
 */
public class Request extends Api {
    @JsonProperty("m")
    private Meta meta;

    @JsonProperty("r")
    private HttpRequest httpRequest;

    @JsonProperty("c")
    private RequestCall requestCall;

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
    public Request(Component component, String path, String name, String version, String platformVersion, Map<String, String> variables, boolean isDebug) {
        super(component, path, name, version, platformVersion, variables, isDebug);
    }

    public Request(Request other) {
        super(other);
        this.meta = other.meta;
        this.httpRequest = other.httpRequest;
        this.requestCall = other.requestCall;
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
     * @param httpRequest
     */
    public void setHttpRequest(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    /**
     * @return
     */
    public RequestCall getRequestCall() {
        return this.requestCall;
    }

    /**
     * @param requestCall
     */
    public void setRequestCall(RequestCall requestCall) {
        this.requestCall = requestCall;
    }

    // SDK Methods

    @JsonIgnore
    public String getGatewayProtocol() {
        return this.meta.getProtocol();
    }

    @JsonIgnore
    public String getGatewayAddress() {
        return this.meta.getGateway().get(1);
    }

    @JsonIgnore
    public String getClientAddress() {
        return this.meta.getClient();
    }

    /**
     * @return Return the name currently defined for the Service, or an empty string if not defined.
     */
    @JsonIgnore
    public String getServiceName() {
        return this.requestCall.getService();
    }

    /**
     * Set the name for the Service with the value specified by the REQUIRED service argument.
     *
     * @param name Name of the service
     * @return Return true if the operation was successful
     */
    public Request setServiceName(String name) {
        this.requestCall.setService(name);
        return this;
    }

    /**
     * @return Return the version currently defined for the Service, or an empty string if not defined.
     */
    @JsonIgnore
    public String getServiceVersion() {
        return this.requestCall.getVersion();
    }

    /**
     * Set the version for the Service with the value specified by the REQUIRED version argument.
     *
     * @param version Version for the service
     * @return Return true if the operation was successful
     */
    public Request setServiceVersion(String version) {
        this.requestCall.setVersion(version);
        return this;
    }

    /**
     * @return Return the name currently defined for the Service action, or an empty string if not defined.
     */
    @JsonIgnore
    public String getActionName() {
        return this.requestCall.getAction();
    }

    /**
     * Set the name for the Service action with the value specified by the REQUIRED action argument.
     *
     * @param action Action name
     * @return Return true if the operation was successful
     */
    public Request setActionName(String action) {
        this.requestCall.setAction(action);
        return this;
    }

    public boolean hasParam(String name) {
        if (this.requestCall.getParams() == null) {
            return false;
        }
        for (Param param : this.requestCall.getParams()) {
            if (param.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @JsonIgnore
    public Param getParam(String name) {
        if (this.requestCall.getParams() == null) {
            return null;
        }
        for (Param param : this.requestCall.getParams()) {
            if (param.getName().equals(name)) {
                return param;
            }
        }
        return null;
    }

    @JsonIgnore
    public List<Param> getParams() {
        if (this.requestCall.getParams() == null) {
            return new ArrayList<>();
        } else {
            return this.requestCall.getParams();
        }
    }

    public Request setParam(Param param) {
        if (this.requestCall == null) {
            this.requestCall.setParams(new ArrayList<Param>());
        }
        this.requestCall.getParams().add(param);
        return this;
    }

    public Param newParam(String name, String value, String type) {
        Param param = new Param();
        param.setName(name);
        param.setType(type);
        param.setValue(value);
        return param;
    }

    /**
     * @param code Response code
     * @param text Response text
     * @return Return a new instance of the Response class, setting the HTTP status code with the value specified by the
     * REQUIRED code argument and the status text with the REQUIRED text argument.
     */
    public Response newResponse(int code, String text) {
        HttpResponse httpResponse = new HttpResponse();
        if (getGatewayProtocol().equals(Constants.KATANA_PROTOCOL_HTTP)) {
            httpResponse.setStatus(code, text);
        }

        Response response = new Response(this.component, this.path, this.name, this.version, this.platformVersion, this.variables, this.isDebug);
        response.setTransport(new Transport());
        response.setHttpResponse(httpResponse);
        return response;
    }

    /**
     * @return Return the instance of the HttpRequest class which contains the HTTP semantics of the request made to
     * the Gateway component.
     */
    public HttpRequest getHttpRequest() {
        return this.httpRequest;
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

        Request request = (Request) o;

        if (getMeta() != null ? !getMeta().equals(request.getMeta()) : request.getMeta() != null) {
            return false;
        }
        if (getHttpRequest() != null ? !getHttpRequest().equals(request.getHttpRequest()) : request.getHttpRequest() != null) {
            return false;
        }
        return getRequestCall() != null ? getRequestCall().equals(request.getRequestCall()) : request.getRequestCall() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getMeta() != null ? getMeta().hashCode() : 0);
        result = 31 * result + (getHttpRequest() != null ? getHttpRequest().hashCode() : 0);
        result = 31 * result + (getRequestCall() != null ? getRequestCall().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Request{" +
                "meta=" + meta +
                ", httpRequest=" + httpRequest +
                ", requestCall=" + requestCall +
                "} " + super.toString();
    }
}
