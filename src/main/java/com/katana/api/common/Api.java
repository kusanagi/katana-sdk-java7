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

    /**
     * Default constructor
     */
    public Api() {
        // Default constructor to make possible the serialization of this object.
    }

    /**
     * Constructor with params
     *
     * @param path            Path of the call
     * @param name            Name of the Service
     * @param version         Version of the Service
     * @param platformVersion Version of the Platform
     * @param variables       Sdk variables
     * @param isDebug         Debug state
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
     * Path setter
     *
     * @param path Path of the call
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Name setter
     *
     * @param name Name of the Service
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Version setter
     *
     * @param version Version of the service
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Platform version setter
     *
     * @param platformVersion Version of the platform
     */
    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    /**
     * Variables setter
     *
     * @param variables Sdk variables
     */
    public void setVariables(Map<String, String> variables) {
        this.variables = variables;
    }

    /**
     * Debug state setter
     *
     * @param debug Debug state
     */
    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    /**
     * @return Return the path to the executing userland source file.
     */
    @JsonIgnore
    public String getPath() {
        return path;
    }

    /**
     * @return Return the unique name of the component.
     */
    @JsonIgnore
    public String getName() {
        return name;
    }

    /**
     * @return Return the version of the component.
     */
    @JsonIgnore
    public String getVersion() {
        return version;
    }

    /**
     * @return Return the version of the platform
     */
    @JsonIgnore
    public String getPlatformVersion() {
        return platformVersion;
    }

    /**
     * @return Return an object, where each property name is the name of the variable, and the value to the variable value.
     */
    @JsonIgnore
    public Map<String, String> getVariables() {
        return variables;
    }

    /**
     * @return Return whether or not the component is currently running in debug mode.
     */
    @JsonIgnore
    public boolean isDebug() {
        return isDebug;
    }

    // SDK Method

    /**
     * Get the variable with the REQUIRED case-sensitive name argument, and which MUST be returned as a string.
     *
     * @param name Name of the variable
     * @return Return the value of the variable
     */
    @JsonIgnore
    public String getVariable(String name) {
        return this.variables.get(name);
    }

    /**
     * @param name Name of the resource
     * @return Determine if a resource has been registered with the component using the REQUIRED case sensitive name argument.
     */
    public boolean hasResource(String name) {
        // TODO do this method
        return false;
    }

    /**
     * @param name Name of the resource
     * @return Return the resource registered with the component using the REQUIRED case sensitive name argument.
     */
    public String getResource(String name) {
        // TODO do this method
        return null;
    }

    /**
     * Return an instance of the ServiceSchema class for the Service defined by the REQUIRED case sensitive name and
     * version arguments, using the stored mapping of schemas.
     * If a Service with the specified name and version does not exist in the stored schema mapping then an exception
     * MUST be thrown with the following message:
     * Cannot resolve schema for Service: %SERVICE% (%VERSION%)
     * Where %SERVICE% is the name and %VERSION% is the version provided for the Service.
     *
     * @return
     */
    public String getServiceSchema(String name, String version) {
        // TODO do this method
        return null;
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
