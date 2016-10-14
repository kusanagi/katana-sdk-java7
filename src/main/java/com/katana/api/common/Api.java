package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Created by juan on 28/08/16.
 */
public class Api {

    private String path;

    private String name;

    private String version;

    private String platformVersion;

    private Map<String, String> variables;

    private boolean isDebug;

    public Api() {
    }

    public Api(String path, String name, String version, String platformVersion, Map<String, String> variables, boolean isDebug) {
        this.path = path;
        this.name = name;
        this.version = version;
        this.platformVersion = platformVersion;
        this.variables = variables;
        this.isDebug = isDebug;
    }

    @JsonIgnore
    public String getPath() {
        return path;
    }

    @JsonIgnore
    public String getName() {
        return name;
    }

    @JsonIgnore
    public String getVersion() {
        return version;
    }

    @JsonIgnore
    public String getPlatformVersion() {
        return platformVersion;
    }

    @JsonIgnore
    public Map<String, String> getVariables() {
        return variables;
    }

    @JsonIgnore
    public boolean isDebug() {
        return isDebug;
    }

    @JsonIgnore
    public String getVariable(String name){
        return this.variables.get(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Api)) return false;

        Api api = (Api) o;

        if (isDebug() != api.isDebug()) return false;
        if (!getPath().equals(api.getPath())) return false;
        if (!getName().equals(api.getName())) return false;
        if (!getVersion().equals(api.getVersion())) return false;
        if (!getPlatformVersion().equals(api.getPlatformVersion())) return false;
        return getVariables().equals(api.getVariables());

    }

    @Override
    public int hashCode() {
        int result = getPath().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getVersion().hashCode();
        result = 31 * result + getPlatformVersion().hashCode();
        result = 31 * result + getVariables().hashCode();
        result = 31 * result + (isDebug() ? 1 : 0);
        return result;
    }
}
