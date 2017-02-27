package com.katana.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;

/**
 * Created by juan on 26/09/16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransportMeta extends Meta {

    @JsonProperty("o")
    private String[] origin;

    @JsonProperty("l")
    private int level;

    @JsonProperty("f")
    private List<List<Object>> fallback;

    @JsonProperty("p")
    private String properties;

    public TransportMeta() {
        // Default constructor to make possible the serialization of this object.
    }

    public String[] getOrigin() {
        return origin;
    }

    public void setOrigin(String[] origin) {
        this.origin = origin;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<List<Object>> getFallback() {
        return fallback;
    }

    public void setFallback(List<List<Object>> fallback) {
        this.fallback = fallback;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        TransportMeta that = (TransportMeta) o;

        if (level != that.level) {
            return false;
        }
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(origin, that.origin)) {
            return false;
        }
        if (fallback != null ? !fallback.equals(that.fallback) : that.fallback != null) {
            return false;
        }
        return properties != null ? properties.equals(that.properties) : that.properties == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(origin);
        result = 31 * result + level;
        result = 31 * result + (fallback != null ? fallback.hashCode() : 0);
        result = 31 * result + (properties != null ? properties.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TransportMeta{" +
                "origin=" + Arrays.toString(origin) +
                ", level=" + level +
                ", fallback=" + fallback +
                ", properties='" + properties + '\'' +
                '}';
    }

    public TransportMeta(TransportMeta other) {
        super(other);
        this.origin = other.origin;
        this.level = other.level;
        this.fallback = other.fallback;
        this.properties = other.properties;
    }
}
