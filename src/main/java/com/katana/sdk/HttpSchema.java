package com.katana.sdk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.api.component.Key;

/**
 * Created by juan on 3/01/17.
 */
public class HttpSchema {

    @JsonProperty(Key.HTTP_SCHEMA_GATEWAY)
    private boolean gateway;

    @JsonProperty(Key.HTTP_SCHEMA_BASE_PATH)
    private String basePath;

    public HttpSchema() {
        gateway = true;
        basePath = "";
    }

    public HttpSchema(HttpSchema other) {
        this.gateway = other.gateway;
        this.basePath = other.basePath;
    }

    public boolean isGateway() {
        return gateway;
    }

    public void setGateway(boolean gateway) {
        this.gateway = gateway;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    //SDK Methods

    @JsonIgnore
    public boolean isAccesible() {
        return isGateway();
    }

    public String getBasePath() {
        return basePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HttpSchema)) {
            return false;
        }

        HttpSchema that = (HttpSchema) o;

        if (isGateway() != that.isGateway()) {
            return false;
        }
        return getBasePath() != null ? getBasePath().equals(that.getBasePath()) : that.getBasePath() == null;

    }

    @Override
    public int hashCode() {
        int result = isGateway() ? 1 : 0;
        result = 31 * result + (getBasePath() != null ? getBasePath().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HttpSchema{" +
                "gateway=" + gateway +
                ", basePath='" + basePath + '\'' +
                '}';
    }
}
