package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by juan on 18/11/16.
 */
public class Param {

    @JsonProperty("n")
    private String name;

    @JsonProperty("v")
    private String value;

    @JsonProperty("t")
    private String type;

    private boolean exists;

    public Param() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean exists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    // SDK Methods
    public Param copyWithName(String name) {
        Param param = new Param();
        param.setName(name);
        param.setType(this.type);
        param.setValue(this.value);
        param.setExists(this.exists);
        return param;
    }

    public Param copyWithValue(String value) {
        Param param = new Param();
        param.setName(this.name);
        param.setType(this.type);
        param.setValue(value);
        param.setExists(this.exists);
        return param;
    }

    public Param copyWithType(String type) {
        Param param = new Param();
        param.setName(this.name);
        param.setType(type);
        param.setValue(this.value);
        param.setExists(this.exists);
        return param;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Param)) {
            return false;
        }

        Param param = (Param) o;

        if (exists() != param.exists()) {
            return false;
        }
        if (getName() != null ? !getName().equals(param.getName()) : param.getName() != null) {
            return false;
        }
        if (getValue() != null ? !getValue().equals(param.getValue()) : param.getValue() != null) {
            return false;
        }
        return getType() != null ? getType().equals(param.getType()) : param.getType() == null;

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (exists() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Param{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", type='" + type + '\'' +
                ", exists=" + exists +
                '}';
    }

    public Param(Param other) {
        this.name = other.name;
        this.value = other.value;
        this.type = other.type;
        this.exists = other.exists;
    }
}
