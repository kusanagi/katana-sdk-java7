package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.api.replies.CommandReplyResult;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RequestCall)) {
            return false;
        }

        RequestCall requestCall = (RequestCall) o;

        if (!getService().equals(requestCall.getService())) {
            return false;
        }
        if (!getVersion().equals(requestCall.getVersion())) {
            return false;
        }
        return getAction().equals(requestCall.getAction());

    }

    @Override
    public int hashCode() {
        int result = getService().hashCode();
        result = 31 * result + getVersion().hashCode();
        result = 31 * result + getAction().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RequestCall{" +
                "service='" + service + '\'' +
                ", version='" + version + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
