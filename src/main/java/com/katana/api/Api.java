package com.katana.api;

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

    public Api(String path, String name, String version, String platformVersion, Map<String, String> variables, boolean isDebug) {
        this.path = path;
        this.name = name;
        this.version = version;
        this.platformVersion = platformVersion;
        this.variables = variables;
        this.isDebug = isDebug;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public Map<String, String> getVariables() {
        return variables;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public String getVariable(String name){
        return this.variables.get(name);
    }
}
