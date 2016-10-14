package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.api.replies.CommandReplyResult;

/**
 * Created by juan on 14/09/16.
 * Katana Java SDK
 */
public class Call implements CommandReplyResult {
    @JsonProperty("s")
    private String service;

    @JsonProperty("v")
    private String version;

    @JsonProperty("a")
    private String action;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Call)) return false;

        Call call = (Call) o;

        if (!getService().equals(call.getService())) return false;
        if (!getVersion().equals(call.getVersion())) return false;
        return getAction().equals(call.getAction());

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
        return "Call{" +
                "service='" + service + '\'' +
                ", version='" + version + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
