package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
        // Default constructor to make possible the serialization of this object.
    }

    /**
     *
     * @param path
     * @param name
     * @param version
     * @param platformVersion
     * @param variables
     * @param isDebug
     */
    public Api(String path, String name, String version, String platformVersion, Map<String, String> variables, boolean isDebug) {
        this.path = path;
        this.name = name;
        this.version = version;
        this.platformVersion = platformVersion;
        this.variables = variables;
        this.isDebug = isDebug;
    }

    /**
     *
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     *
     * @param platformVersion
     */
    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    /**
     *
     * @param variables
     */
    public void setVariables(Map<String, String> variables) {
        this.variables = variables;
    }

    /**
     *
     * @param debug
     */
    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public String getPath() {
        return path;
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public String getVersion() {
        return version;
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public String getPlatformVersion() {
        return platformVersion;
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public Map<String, String> getVariables() {
        return variables;
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public boolean isDebug() {
        return isDebug;
    }

    /**
     *
     * @param name
     * @return
     */
    @JsonIgnore
    public String getVariable(String name) {
        return this.variables.get(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Api)) {
            return false;
        }

        Api api = (Api) o;

        if (isDebug() != api.isDebug()) {
            return false;
        }
        if (getPath() != null ? !getPath().equals(api.getPath()) : api.getPath() != null) {
            return false;
        }
        if (getName() != null ? !getName().equals(api.getName()) : api.getName() != null) {
            return false;
        }
        if (getVersion() != null ? !getVersion().equals(api.getVersion()) : api.getVersion() != null) {
            return false;
        }
        if (getPlatformVersion() != null ? !getPlatformVersion().equals(api.getPlatformVersion()) : api.getPlatformVersion() != null) {
            return false;
        }
        return getVariables() != null ? getVariables().equals(api.getVariables()) : api.getVariables() == null;

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

    @Override
    public String toString() {
        return "Api{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", platformVersion='" + platformVersion + '\'' +
                ", variables=" + variables +
                ", isDebug=" + isDebug +
                '}';
    }
}
