package com.katana.sdk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.api.component.Key;

/**
 * Created by juan on 3/01/17.
 */
public class FileHttpSchema {

    @JsonProperty(Key.FILE_HTTP_SCHEMA_GATEWAY)
    private boolean gateway;

    @JsonProperty(Key.FILE_HTTP_SCHEMA_PARAM)
    private String param;

    public FileHttpSchema() {
        this.gateway = true;
    }

    public FileHttpSchema(FileHttpSchema other) {
        this.gateway = other.gateway;
        this.param = other.param;
    }

    public boolean isGateway() {
        return gateway;
    }

    public void setGateway(boolean gateway) {
        this.gateway = gateway;
    }

    public void setParam(String param) {
        this.param = param;
    }

    //SDK Methods

    @JsonIgnore
    public boolean isAccessible() {
        return isGateway();
    }

    public String getParam() {
        return param;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FileHttpSchema)) {
            return false;
        }

        FileHttpSchema that = (FileHttpSchema) o;

        if (isGateway() != that.isGateway()) {
            return false;
        }
        return getParam() != null ? getParam().equals(that.getParam()) : that.getParam() == null;

    }

    @Override
    public int hashCode() {
        int result = isGateway() ? 1 : 0;
        result = 31 * result + (getParam() != null ? getParam().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FileHttpSchema{" +
                "gateway=" + gateway +
                ", param='" + param + '\'' +
                '}';
    }
}
