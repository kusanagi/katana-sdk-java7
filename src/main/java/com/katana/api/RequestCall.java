package com.katana.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.api.replies.common.CommandReplyResult;

import java.util.List;

/**
 * Created by juan on 14/09/16.
 * Katana Java SDK
 */
public class RequestCall implements CommandReplyResult {
    @JsonProperty("s")
    private String service;

    @JsonProperty("v")
    private String version;

    @JsonProperty("a")
    private String action;

    @JsonProperty("p")
    private List<Param> params;

    public RequestCall() {
        // Default constructor to make possible the serialization of this object.
    }

    public RequestCall(RequestCall other) {
        this.service = other.service;
        this.version = other.version;
        this.action = other.action;
        this.params = other.params;
    }

    /**
     * @return
     */
    public String getService() {
        return service;
    }

    /**
     * @param service
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * @return
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action
     */
    public void setAction(String action) {
        this.action = action;
    }

    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        this.params = params;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RequestCall)) {
            return false;
        }

        RequestCall that = (RequestCall) o;

        if (getService() != null ? !getService().equals(that.getService()) : that.getService() != null) {
            return false;
        }
        if (getVersion() != null ? !getVersion().equals(that.getVersion()) : that.getVersion() != null) {
            return false;
        }
        if (getAction() != null ? !getAction().equals(that.getAction()) : that.getAction() != null) {
            return false;
        }
        return getParams() != null ? getParams().equals(that.getParams()) : that.getParams() == null;

    }

    @Override
    public int hashCode() {
        int result = getService() != null ? getService().hashCode() : 0;
        result = 31 * result + (getVersion() != null ? getVersion().hashCode() : 0);
        result = 31 * result + (getAction() != null ? getAction().hashCode() : 0);
        result = 31 * result + (getParams() != null ? getParams().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RequestCall{" +
                "service='" + service + '\'' +
                ", version='" + version + '\'' +
                ", action='" + action + '\'' +
                ", params=" + params +
                '}';
    }
}
