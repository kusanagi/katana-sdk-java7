package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created by juan on 27/08/16.
 */
public class Action extends Api {
    @JsonProperty("p")
    private Map<String, Map<String, Map<String, String>>> params;

    @JsonProperty("t")
    private Transport transport;

    private String actionName;

    public Action() {
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
    public Action(String path, String name, String version, String platformVersion, Map<String, String> variables, boolean isDebug) {
        super(path, name, version, platformVersion, variables, isDebug);
    }

    /**
     *
     * @param actionName
     */
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    /**
     *
     * @return
     */
    public Map<String, Map<String, Map<String, String>>> getParams() {
        return params;
    }

    /**
     *
     * @param params
     */
    public void setParams(Map<String, Map<String, Map<String, String>>> params) {
        this.params = params;
    }

    /**
     *
     * @return
     */
    public Transport getTransport() {
        return transport;
    }

    /**
     *
     * @param transport
     */
    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    // SDK Methods

    /**
     *
     * @return
     */
    @JsonIgnore
    public boolean isOrigin() {
        return true;
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public String getActionName() {
        return this.actionName;
    }

    /**
     *
     * @param name
     * @param value
     * @return
     */
    public boolean setProperty(String name, String value) {
        TransportMeta meta = this.transport.getMeta();
        Map<String, String> properties = meta.getProperties();
        properties.put(name, value);
        return true;
    }

    /**
     *
     * @param location
     * @param name
     * @return
     */
    public String getParam(String location, String name) {
        return params.get(location).get(name).get("v");
    }

    /**
     *
     * @param location
     * @param name
     * @param value
     * @param type
     * @return
     */
    public Map<String, String> newParam(String location, String name, String value, String type) {
        return null;
    }

    /**
     *
     * @param name
     * @return
     */
    public boolean hasFile(String name) {
        return true;
    }

    /**
     *
     * @param name
     * @param path
     * @param mime
     * @return
     */
    public File newFile(String name, String path, String mime) {
        return new File();
    }

    /**
     *
     * @param file
     * @return
     */
    public boolean setDownload(File file) {
        return true;
    }

    /**
     *
     * @param entity
     * @return
     */
    public boolean setEntity(Object entity) {
        this.transport.addData(getName(), getVersion(), actionName, entity);
        return true;
    }

    /**
     *
     * @param collection
     * @return
     */
    public boolean setCollection(Object collection) {
        return this.transport.addData(getName(), getVersion(), getActionName(), collection);
    }

    /**
     *
     * @param primaryKey
     * @param service
     * @param forignKey
     * @return
     */
    public boolean relateOne(String primaryKey, String service, String forignKey) {
        return true;
    }

    /**
     *
     * @param primaryKey
     * @param service
     * @param foreignKey
     * @return
     */
    public boolean relateMany(String primaryKey, String service, String[] foreignKey) {
        return true;
    }

    /**
     *
     * @param link
     * @param uri
     * @return
     */
    public boolean setLink(String link, String uri) {
        return this.transport.addLink(getName(), link, uri);
    }

    /**
     *
     * @param action
     * @param params
     * @return
     */
    public boolean transaction(String action, Map<String, String>[] params) {
        return true;
    }

    /**
     *
     * @param service
     * @param version
     * @param action
     * @param params
     * @param files
     * @return
     */
    public boolean call(String service, String version, String action, Map<String, String>[] params, File[] files) {
        return true;
    }

    /**
     *
     * @param message
     * @param code
     * @param status
     * @return
     */
    public boolean error(String message, int code, String status) {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Action)) {
            return false;
        }

        Action action = (Action) o;

        if (!getParams().equals(action.getParams())) {
            return false;
        }
        return getTransport().equals(action.getTransport());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getParams().hashCode();
        result = 31 * result + getTransport().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Action{" +
                "params=" + params +
                ", transport=" + transport +
                "} " + super.toString();
    }
}
