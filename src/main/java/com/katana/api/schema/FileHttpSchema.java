package com.katana.api.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by juan on 3/01/17.
 */
public class FileHttpSchema {

    @JsonProperty("g")
    private boolean gateway;

    @JsonProperty("p")
    private String param;

    public FileHttpSchema() {
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
        int result = (isGateway() ? 1 : 0);
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

    public FileHttpSchema(FileHttpSchema other) {
        this.gateway = other.gateway;
        this.param = other.param;
    }
}
