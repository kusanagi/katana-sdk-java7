package com.katana.sdk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

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
    private String[] body;

    public ActionHttpSchema() {
        this.gateway = true;
        this.path = "/";
        this.method = "get";
        this.input = "query";
        this.body = new String[]{"text/plain"};
    }

    public ActionHttpSchema(ActionHttpSchema other) {
        this.gateway = other.gateway;
        this.path = other.path;
        this.method = other.method;
        this.input = other.input;
        this.body = other.body;
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

    public void setBody(String[] body) {
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

    public String[] getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ActionHttpSchema that = (ActionHttpSchema) o;

        if (gateway != that.gateway) {
            return false;
        }
        if (path != null ? !path.equals(that.path) : that.path != null) {
            return false;
        }
        if (method != null ? !method.equals(that.method) : that.method != null) {
            return false;
        }
        if (input != null ? !input.equals(that.input) : that.input != null) {
            return false;
        }
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        int result = gateway ? 1 : 0;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (input != null ? input.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(body);
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
}
