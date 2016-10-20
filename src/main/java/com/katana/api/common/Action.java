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
    }

    public Action(String path, String name, String version, String platformVersion, Map<String, String> variables, boolean isDebug) {
        super(path, name, version, platformVersion, variables, isDebug);
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public Map<String, Map<String, Map<String, String>>> getParams() {
        return params;
    }

    public void setParams(Map<String, Map<String, Map<String, String>>> params) {
        this.params = params;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    // SDK Methods

    @JsonIgnore
    public boolean isOrigin(){
        return true;
    }

    @JsonIgnore
    public String getActionName(){
        return this.actionName;
    }

    public boolean setProperty(String name, String value){
        TransportMeta meta = this.transport.getMeta();
        Map<String, String> properties = meta.getProperties();
        properties.put(name, value);
        return true;
    }

    public Map<String, String> getParam(String location, String name){
        return params.get(location).get(name);
    }

    public Map<String, String> newParam(String location, String name, String value, String type){
        return null;
    }

    public boolean hasFile(String name){
        return true;
    }

    public File newFile(String name, String path, String mime){
        return new File();
    }

    public boolean setDownload(File file){
        return true;
    }

    public boolean setEntity(Object entity){
        this.transport.addData(getName(), getVersion(), actionName, entity);
        return true;
    }

    public boolean setCollection(Object collection){
        return this.transport.addData(getName(), getVersion(), getActionName(), collection);
    }

    public boolean relateOne(String primaryKey, String service, String forignKey){
        return true;
    }

    public boolean relateMany(String primaryKey, String service, String[] foreignKey){
        return true;
    }

    public boolean setLink(String link, String uri){
        return this.transport.addLink(getName(), link, uri);
    }

    public boolean transaction(String action, Map<String, String>[] params){
        return true;
    }

    public boolean call(String service, String version, String action, Map<String, String>[] params, File[] files){
        return true;
    }

    public boolean error(String message, int code, String status){
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Action)) return false;

        Action action = (Action) o;

        if (!getParams().equals(action.getParams())) return false;
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
