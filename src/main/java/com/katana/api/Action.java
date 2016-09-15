package com.katana.api;

import com.katana.api.entity.File;
import com.katana.api.entity.Meta;

import java.util.Map;

/**
 * Created by juan on 27/08/16.
 */
public class Action extends Api {
    private Map<String, Map<String, Map<String, String>>> params;
    private Transport transport;

    public Action(String path, String name, String version, String platformVersion, Map<String, String> variables, boolean isDebug) {
        super(path, name, version, platformVersion, variables, isDebug);
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

    public boolean isOrigin(){
        return true;
    }

    public String getActionName(){
        return "";
    }

    public boolean setProperty(String name, String value){
        Meta meta = this.transport.getMeta();
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
        return true;
    }

    public boolean setCollection(Object[] collection){
        return true;
    }

    public boolean relateOne(String primaryKey, String service, String forignKey){
        return true;
    }

    public boolean relateMany(String primaryKey, String service, String[] foreignKey){
        return true;
    }

    public boolean setLink(String link, String uri){
        return true;
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
}
