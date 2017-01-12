package com.katana.api.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.api.common.schema.ActionSchema;
import com.katana.api.common.schema.HttpSchema;

/**
 * Created by juan on 3/01/17.
 */
public class ServiceSchema {

    @JsonProperty("a")
    private String address;

    @JsonProperty("f")
    private boolean files;

    @JsonProperty("h")
    private HttpSchema http;

    @JsonProperty("ac")
    private ActionSchema actions;

    public ServiceSchema() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isFiles() {
        return files;
    }

    public void setFiles(boolean files) {
        this.files = files;
    }

    public HttpSchema getHttp() {
        return http;
    }

    public void setHttp(HttpSchema http) {
        this.http = http;
    }

    public ActionSchema getActions() {
        return actions;
    }

    public void setActions(ActionSchema actions) {
        this.actions = actions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceSchema)) {
            return false;
        }

        ServiceSchema that = (ServiceSchema) o;

        if (isFiles() != that.isFiles()) {
            return false;
        }
        if (getAddress() != null ? !getAddress().equals(that.getAddress()) : that.getAddress() != null) {
            return false;
        }
        if (getHttp() != null ? !getHttp().equals(that.getHttp()) : that.getHttp() != null) {
            return false;
        }
        return getActions() != null ? getActions().equals(that.getActions()) : that.getActions() == null;

    }

    @Override
    public int hashCode() {
        int result = getAddress() != null ? getAddress().hashCode() : 0;
        result = 31 * result + (isFiles() ? 1 : 0);
        result = 31 * result + (getHttp() != null ? getHttp().hashCode() : 0);
        result = 31 * result + (getActions() != null ? getActions().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ServiceSchema{" +
                "address='" + address + '\'' +
                ", files=" + files +
                ", http='" + http + '\'' +
                ", actions='" + actions + '\'' +
                '}';
    }

    public ServiceSchema(ServiceSchema other) {
        this.address = other.address;
        this.files = other.files;
        this.http = other.http;
        this.actions = other.actions;
    }
}
