package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by juan on 18/11/16.
 */
public class Param {

    @JsonProperty("l")
    private String location;

    @JsonProperty("n")
    private String name;

    @JsonProperty("v")
    private String value;

    @JsonProperty("t")
    private String type;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    // SDK Methods
    public Param copyWithName(String name){
        Param param = new Param();
        param.setName(name);
        param.setLocation(this.location);
        param.setType(this.type);
        param.setValue(this.value);
        return param;
    }

    public Param copyWithLocation(String location){
        Param param = new Param();
        param.setName(this.name);
        param.setLocation(location);
        param.setType(this.type);
        param.setValue(this.value);
        return param;
    }

    public Param copyWithValue(String value){
        Param param = new Param();
        param.setName(this.name);
        param.setLocation(this.location);
        param.setType(this.type);
        param.setValue(value);
        return param;
    }

    public Param copyWithType(String location){
        Param param = new Param();
        param.setName(this.name);
        param.setLocation(this.location);
        param.setType(type);
        param.setValue(this.value);
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

        if (getLocation() != null ? !getLocation().equals(param.getLocation()) : param.getLocation() != null) {
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
        int result = getLocation() != null ? getLocation().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Param{" +
                "location='" + location + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
