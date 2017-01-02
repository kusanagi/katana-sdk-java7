package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by juan on 18/11/16.
 */
public class Call {

    @JsonProperty("n")
    private String name;

    @JsonProperty("v")
    private String version;

    @JsonProperty("a")
    private String action;

    @JsonProperty("p")
    private List<Param> params;

    @JsonProperty("g")
    private String gateway;

    @JsonProperty("c")
    private String callback;

    public Call() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        this.params = params;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Call)) {
            return false;
        }

        Call call = (Call) o;

        if (getName() != null ? !getName().equals(call.getName()) : call.getName() != null) {
            return false;
        }
        if (getVersion() != null ? !getVersion().equals(call.getVersion()) : call.getVersion() != null) {
            return false;
        }
        if (getAction() != null ? !getAction().equals(call.getAction()) : call.getAction() != null) {
            return false;
        }
        if (getParams() != null ? !getParams().equals(call.getParams()) : call.getParams() != null) {
            return false;
        }
        if (getGateway() != null ? !getGateway().equals(call.getGateway()) : call.getGateway() != null) {
            return false;
        }
        return getCallback() != null ? getCallback().equals(call.getCallback()) : call.getCallback() == null;

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getVersion() != null ? getVersion().hashCode() : 0);
        result = 31 * result + (getAction() != null ? getAction().hashCode() : 0);
        result = 31 * result + (getParams() != null ? getParams().hashCode() : 0);
        result = 31 * result + (getGateway() != null ? getGateway().hashCode() : 0);
        result = 31 * result + (getCallback() != null ? getCallback().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Call{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", action='" + action + '\'' +
                ", params=" + params +
                ", gateway='" + gateway + '\'' +
                ", callback='" + callback + '\'' +
                '}';
    }

    public Call(Call other) {
        this.name = other.name;
        this.version = other.version;
        this.action = other.action;
        this.params = other.params;
        this.gateway = other.gateway;
        this.callback = other.callback;
    }
}
