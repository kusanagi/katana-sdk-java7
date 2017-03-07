package com.katana.sdk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.api.component.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by juan on 13/01/17.
 */
public class ServiceSchema {

    private String name;

    private String version;

    @JsonProperty("a")
    private String address;

    @JsonProperty("f")
    private boolean files;

    @JsonProperty("h")
    private HttpSchema httpSchema;

    @JsonProperty("ac")
    private Map<String, ActionSchema> actionSchemas;

    public ServiceSchema() {
        files = false;
        httpSchema = new HttpSchema();
        actionSchemas = new HashMap<>();
    }

    public ServiceSchema(ServiceSchema other) {
        this.address = other.address;
        this.files = other.files;
        this.httpSchema = other.httpSchema;
        this.actionSchemas = other.actionSchemas;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFiles(boolean files) {
        this.files = files;
    }

    public boolean isFiles() {
        return files;
    }

    public void setHttpSchema(HttpSchema httpSchema) {
        this.httpSchema = httpSchema;
    }

    public Map<String, ActionSchema> getActionSchemas() {
        return actionSchemas;
    }

    public void setActionSchemas(Map<String, ActionSchema> actionSchemas) {
        this.actionSchemas = actionSchemas;
    }

    //SDK Methods

    @JsonIgnore
    public String getName() {
        return this.name;
    }

    @JsonIgnore
    public String getVersion() {
        return this.version;
    }

    public boolean hasFileServer() {
        return isFiles();
    }

    @JsonIgnore
    public List<String> getActions() {
        List<String> actions = new ArrayList<>();

        for (String s : this.actionSchemas.keySet()) {
            actions.add(s);
        }
        return actions;
    }

    public boolean hasAction(String name) {
        return this.actionSchemas.containsKey(name);
    }

    public ActionSchema getActionSchema(String name) {
        if (!this.actionSchemas.containsKey(name)){
            throw new IllegalArgumentException(String.format(Constants.CANNOT_RESOLVE_SCHEMA_FOR_ACTION, name));
        }

        return this.actionSchemas.get(name);
    }

    public HttpSchema getHttpSchema() {
        return this.httpSchema;
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
        if (getHttpSchema() != null ? !getHttpSchema().equals(that.getHttpSchema()) : that.getHttpSchema() != null) {
            return false;
        }
        return getActions() != null ? getActions().equals(that.getActions()) : that.getActions() == null;

    }

    @Override
    public int hashCode() {
        int result = getAddress() != null ? getAddress().hashCode() : 0;
        result = 31 * result + (isFiles() ? 1 : 0);
        result = 31 * result + (getHttpSchema() != null ? getHttpSchema().hashCode() : 0);
        result = 31 * result + (getActions() != null ? getActions().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ServiceSchema{" +
                "address='" + address + '\'' +
                ", files=" + files +
                ", httpSchema=" + httpSchema +
                ", actionSchemas=" + actionSchemas +
                '}';
    }
}
