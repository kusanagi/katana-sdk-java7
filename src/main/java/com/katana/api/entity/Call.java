package com.katana.api.entity;

/**
 * Created by juan on 14/09/16.
 * Katana Java SDK
 */
public class Call {
    private String service;

    private String version;

    private String action;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
