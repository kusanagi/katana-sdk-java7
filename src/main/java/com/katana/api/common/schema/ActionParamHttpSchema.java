package com.katana.api.common.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by juan on 3/01/17.
 */
public class ActionParamHttpSchema {

    @JsonProperty("g")
    private boolean gateway;

    @JsonProperty("i")
    private String input;

    @JsonProperty("p")
    private String param;

    public ActionParamHttpSchema() {
    }

    public boolean isGateway() {
        return gateway;
    }

    public void setGateway(boolean gateway) {
        this.gateway = gateway;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActionParamHttpSchema)) {
            return false;
        }

        ActionParamHttpSchema that = (ActionParamHttpSchema) o;

        if (isGateway() != that.isGateway()) {
            return false;
        }
        if (getInput() != null ? !getInput().equals(that.getInput()) : that.getInput() != null) {
            return false;
        }
        return getParam() != null ? getParam().equals(that.getParam()) : that.getParam() == null;

    }

    @Override
    public int hashCode() {
        int result = (isGateway() ? 1 : 0);
        result = 31 * result + (getInput() != null ? getInput().hashCode() : 0);
        result = 31 * result + (getParam() != null ? getParam().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ActionParamHttpSchema{" +
                "gateway=" + gateway +
                ", input='" + input + '\'' +
                ", param='" + param + '\'' +
                '}';
    }

    public ActionParamHttpSchema(ActionParamHttpSchema other) {
        this.gateway = other.gateway;
        this.input = other.input;
        this.param = other.param;
    }
}
