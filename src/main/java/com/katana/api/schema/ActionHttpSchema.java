package com.katana.api.schema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by juan on 3/01/17.
 */
public class ActionHttpSchema {

    @JsonProperty("g")
    private boolean gateway;

    @JsonProperty("p")
    private String path;

    @JsonProperty("m")
    private String method;

    @JsonProperty("i")
    private String input;

    @JsonProperty("b")
    private String body;

    public ActionHttpSchema() {
        this.gateway = true;
        this.path = "/";
        this.method = "get";
        this.input = "query";
        this.body = "text/plain";
    }

    public boolean isGateway() {
        return gateway;
    }

    public void setGateway(boolean gateway) {
        this.gateway = gateway;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void setBody(String body) {
        this.body = body;
    }

    //SDK Methods

    @JsonIgnore
    public boolean isAccesible() {
        return isGateway();
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getInput() {
        return input;
    }

    public String getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActionHttpSchema)) {
            return false;
        }

        ActionHttpSchema that = (ActionHttpSchema) o;

        if (isGateway() != that.isGateway()) {
            return false;
        }
        if (getPath() != null ? !getPath().equals(that.getPath()) : that.getPath() != null) {
            return false;
        }
        if (getMethod() != null ? !getMethod().equals(that.getMethod()) : that.getMethod() != null) {
            return false;
        }
        if (getInput() != null ? !getInput().equals(that.getInput()) : that.getInput() != null) {
            return false;
        }
        return getBody() != null ? getBody().equals(that.getBody()) : that.getBody() == null;

    }

    @Override
    public int hashCode() {
        int result = (isGateway() ? 1 : 0);
        result = 31 * result + (getPath() != null ? getPath().hashCode() : 0);
        result = 31 * result + (getMethod() != null ? getMethod().hashCode() : 0);
        result = 31 * result + (getInput() != null ? getInput().hashCode() : 0);
        result = 31 * result + (getBody() != null ? getBody().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ActionHttpSchema{" +
                "gateway=" + gateway +
                ", path='" + path + '\'' +
                ", method='" + method + '\'' +
                ", input='" + input + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public ActionHttpSchema(ActionHttpSchema other) {
        this.gateway = other.gateway;
        this.path = other.path;
        this.method = other.method;
        this.input = other.input;
        this.body = other.body;
    }
}
