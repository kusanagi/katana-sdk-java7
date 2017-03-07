package com.katana.sdk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.api.component.Key;

/**
 * Created by juan on 3/01/17.
 */
public class FileSchema {

    private String name;

    @JsonProperty(Key.FILE_SCHEMA_MIME)
    private String mime;

    @JsonProperty(Key.FILE_SCHEMA_REQUIRED)
    private boolean required;

    @JsonProperty(Key.FILE_SCHEMA_MAX)
    private int max;

    @JsonProperty(Key.FILE_SCHEMA_EXCLUSIVE_MAX)
    private boolean exclusiveMax;

    @JsonProperty(Key.FILE_SCHEMA_MIN)
    private int min;

    @JsonProperty(Key.FILE_SCHEMA_EXCLUSIVE_MIN)
    private boolean exclusiveMin;

    @JsonProperty(Key.FILE_SCHEMA_HTTP)
    private FileHttpSchema http;

    public FileSchema() {
        this.required = false;
        this.exclusiveMax = false;
        this.exclusiveMax = false;
        this.http = new FileHttpSchema();
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

    @JsonIgnore
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
}
