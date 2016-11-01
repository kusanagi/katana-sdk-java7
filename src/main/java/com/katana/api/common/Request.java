package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by juan on 27/08/16.
 */
public class Request extends Api {
    @JsonProperty("T")
    private String type;

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
     *
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
     *
     * @return
     */
    public String getType() {
        return this.type;
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
        return this.meta;
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
    public HttpRequest getHttpRequest() {
        return this.httpRequest;
    }

    /**
     *
     * @param httpRequest
     */
    public void setHttpRequest(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    /**
     *
     * @return
     */
    public Call getCall() {
        return this.call;
    }

    /**
     *
     * @param call
     */
    public void setCall(Call call) {
        this.call = call;
    }

    // SDK Methods

    /**
     *
     * @param method
     * @return
     */
    public boolean isMethod(String method) {
        return true;
    }

    /**
     *
     * @return
     */
    public String getMethod() {
        return this.httpRequest.getMethod();
    }

    /**
     *
     * @return
     */
    public String getUrl() {
        return this.httpRequest.getUrl();
    }

    /**
     *
     * @return
     */
    public String getUrlScheme() {
        return "";
    }

    /**
     *
     * @return
     */
    public String getUrlHost() {
        return "";
    }

    /**
     *
     * @return
     */
    public String getUrlPath() {
        return this.httpRequest.getUrl();
    }

    /**
     *
     * @param name
     * @return
     */
    public boolean hasQueryParam(String name) {
        return true;
    }

    /**
     *
     * @param name
     * @return
     */
    public String getQueryParam(String name) {
        return "";
    }

    /**
     *
     * @return
     */
    public String[] getQueryParamArray() {
        return new String[0];
    }

    /**
     *
     * @return
     */
    public Map<String, String> getQueryParam() {
        return this.httpRequest.getQuery();
    }

    /**
     *
     * @return
     */
    public Map<String, String[]> getQueryParamsArray() {
        return new HashMap<>();
    }

    /**
     *
     * @param name
     * @return
     */
    public boolean hasPostParam(String name) {
        return true;
    }

    /**
     *
     * @param name
     * @return
     */
    public String getPostParam(String name) {
        return "";
    }

    /**
     *
     * @param name
     * @return
     */
    public String[] getPostParamArray(String name) {
        return new String[0];
    }

    /**
     *
     * @return
     */
    public Map<String, String> getPostParams() {
        return this.httpRequest.getPostData();
    }

    /**
     *
     * @return
     */
    public Map<String, String[]> getPostParamsArray() {
        return new HashMap<>();
    }

    /**
     *
     * @param version
     * @return
     */
    public boolean isPortocolVersion(String version) {
        return true;
    }

    /**
     *
     * @return
     */
    public String getProtocolVersion() {
        return this.httpRequest.getVersion();
    }

    /**
     *
     * @param name
     * @return
     */
    public boolean hasHeader(String name) {
        return true;
    }

    /**
     *
     * @param name
     * @return
     */
    public String getHeader(String name) {
        return this.httpRequest.getHeaders().get(name).get(0);
    }

    /**
     *
     * @return
     */
    public Map<String, List<String>> getHeaders() {
        return this.httpRequest.getHeaders();
    }

    /**
     *
     * @return
     */
    public boolean hasBody() {
        return true;
    }

    /**
     *
     * @return
     */
    public String getBody() {
        return httpRequest.getBody();
    }

    /**
     *
     * @param name
     * @return
     */
    public boolean hasFile(String name) {
        return true;
    }

    /**
     *
     * @param name
     * @return
     */
    public File getFile(String name) {
        return new File();
    }

    /**
     *
     * @return
     */
    public File[] getFiles() {
        return this.httpRequest.getFiles();
    }

    /**
     *
     * @return
     */
    public String getServiceName() {
        return this.call.getService();
    }

    /**
     *
     * @param name
     * @return
     */
    public boolean setServiceName(String name) {
        this.call.setService(name);
        return true;
    }

    /**
     *
     * @return
     */
    public String getServiceVersion() {
        return this.call.getVersion();
    }

    /**
     *
     * @param version
     * @return
     */
    public boolean setServiceVersion(String version) {
        this.call.setVersion(version);
        return true;
    }

    /**
     *
     * @return
     */
    public String getActionName() {
        return this.call.getAction();
    }

    /**
     *
     * @param action
     * @return
     */
    public boolean setActionName(String action) {
        this.call.setAction(action);
        return true;
    }

    /**
     *
     * @param code
     * @param text
     * @return
     */
    public Response newResponse(int code, String text) {
        return null;
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
        result = 31 * result + getType().hashCode();
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
