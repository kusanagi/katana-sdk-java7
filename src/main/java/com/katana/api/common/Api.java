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
    public int hashCode() {
        int result = getPath() != null ? getPath().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getVersion() != null ? getVersion().hashCode() : 0);
        result = 31 * result + (getPlatformVersion() != null ? getPlatformVersion().hashCode() : 0);
        result = 31 * result + (getVariables() != null ? getVariables().hashCode() : 0);
        result = 31 * result + (isDebug() ? 1 : 0);
        return result;
    }
}
