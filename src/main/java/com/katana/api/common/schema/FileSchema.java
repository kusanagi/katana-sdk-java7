package com.katana.api.common.schema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by juan on 3/01/17.
 */
public class FileSchema {

    private String name;

    @JsonProperty("m")
    private String mime;

    @JsonProperty("r")
    private boolean required;

    @JsonProperty("mx")
    private int max;

    @JsonProperty("ex")
    private boolean exclusiveMax;

    @JsonProperty("mn")
    private int min;

    @JsonProperty("en")
    private boolean exclusiveMin;

    @JsonProperty("h")
    private FileHttpSchema http;

    public FileSchema() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setExclusiveMax(boolean exclusiveMax) {
        this.exclusiveMax = exclusiveMax;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setExclusiveMin(boolean exclusiveMin) {
        this.exclusiveMin = exclusiveMin;
    }

    public FileHttpSchema getHttp() {
        return http;
    }

    public void setHttp(FileHttpSchema http) {
        this.http = http;
    }

    //SDK Methods

    @JsonIgnore
    public String getName() {
        return name;
    }

    public String getMime() {
        return mime;
    }

    public boolean isRequired() {
        return required;
    }

    public int getMax() {
        return max;
    }

    public boolean isExclusiveMax() {
        return exclusiveMax;
    }

    public int getMin() {
        return min;
    }

    public boolean isExclusiveMin() {
        return exclusiveMin;
    }

    public FileHttpSchema getHttpSchema() {
        return getHttp();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FileSchema)) {
            return false;
        }

        FileSchema that = (FileSchema) o;

        if (isRequired() != that.isRequired()) {
            return false;
        }
        if (getMax() != that.getMax()) {
            return false;
        }
        if (isExclusiveMax() != that.isExclusiveMax()) {
            return false;
        }
        if (getMin() != that.getMin()) {
            return false;
        }
        if (isExclusiveMin() != that.isExclusiveMin()) {
            return false;
        }
        if (getMime() != null ? !getMime().equals(that.getMime()) : that.getMime() != null) {
            return false;
        }
        return getHttp() != null ? getHttp().equals(that.getHttp()) : that.getHttp() == null;

    }

    @Override
    public int hashCode() {
        int result = getMime() != null ? getMime().hashCode() : 0;
        result = 31 * result + (isRequired() ? 1 : 0);
        result = 31 * result + getMax();
        result = 31 * result + (isExclusiveMax() ? 1 : 0);
        result = 31 * result + getMin();
        result = 31 * result + (isExclusiveMin() ? 1 : 0);
        result = 31 * result + (getHttp() != null ? getHttp().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FileSchema{" +
                "mime='" + mime + '\'' +
                ", required=" + required +
                ", max=" + max +
                ", exclusiveMax=" + exclusiveMax +
                ", min=" + min +
                ", exclusiveMin=" + exclusiveMin +
                ", http=" + http +
                '}';
    }

    public FileSchema(FileSchema other) {
        this.mime = other.mime;
        this.required = other.required;
        this.max = other.max;
        this.exclusiveMax = other.exclusiveMax;
        this.min = other.min;
        this.exclusiveMin = other.exclusiveMin;
        this.http = other.http;
    }
}
