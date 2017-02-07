package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Map;

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
    private Object[][] fallback;

    @JsonProperty("p")
    private String properties;

    public TransportMeta() {
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

    public Object[][] getFallback() {
        return fallback;
    }

    public void setFallback(Object[][] fallback) {
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
        if (!(o instanceof TransportMeta)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        TransportMeta that = (TransportMeta) o;

        if (getLevel() != that.getLevel()) {
            return false;
        }
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getOrigin(), that.getOrigin())) {
            return false;
        }
        if (!Arrays.deepEquals(getFallback(), that.getFallback())) {
            return false;
        }
        return getProperties() != null ? getProperties().equals(that.getProperties()) : that.getProperties() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(getOrigin());
        result = 31 * result + getLevel();
        result = 31 * result + Arrays.deepHashCode(getFallback());
        result = 31 * result + (getProperties() != null ? getProperties().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TransportMeta{" +
                "origin=" + Arrays.toString(origin) +
                ", level=" + level +
                ", fallback=" + Arrays.toString(fallback) +
                ", properties=" + properties +
                "} " + super.toString();
    }

    public TransportMeta(TransportMeta other) {
        super(other);
        this.origin = other.origin;
        this.level = other.level;
        this.fallback = other.fallback;
        this.properties = other.properties;
    }
}
