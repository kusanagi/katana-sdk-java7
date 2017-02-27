package com.katana.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by juan on 2/01/17.
 */
public class ServiceTransaction {

    @JsonProperty("n")
    private String name;

    @JsonProperty("v")
    private String version;

    @JsonProperty("a")
    private String action;

    @JsonProperty("c")
    private String callee;

    @JsonProperty("p")
    private List<Param> params;

    public ServiceTransaction() {
        // Default constructor to make possible the serialization of this object.
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

    public String getCallee() {
        return callee;
    }

    public void setCallee(String callee) {
        this.callee = callee;
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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ServiceTransaction that = (ServiceTransaction) o;

        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (version != null ? !version.equals(that.version) : that.version != null) {
            return false;
        }
        if (action != null ? !action.equals(that.action) : that.action != null) {
            return false;
        }
        if (callee != null ? !callee.equals(that.callee) : that.callee != null) {
            return false;
        }
        return params != null ? params.equals(that.params) : that.params == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (callee != null ? callee.hashCode() : 0);
        result = 31 * result + (params != null ? params.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ServiceTransaction{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", action='" + action + '\'' +
                ", callee='" + callee + '\'' +
                ", params=" + params +
                '}';
    }

    public ServiceTransaction(ServiceTransaction other) {
        this.name = other.name;
        this.version = other.version;
        this.action = other.action;
        this.callee = other.callee;
        this.params = other.params;
    }
}
