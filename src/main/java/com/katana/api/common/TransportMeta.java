package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by juan on 26/09/16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransportMeta {

    @JsonProperty("v")
    private String version;

    @JsonProperty("i")
    private String id;

    @JsonProperty("d")
    private String datetime;

    @JsonProperty("o")
    private String[] origin;

    @JsonProperty("l")
    private int level;

    @JsonProperty("p")
    private Map<String, String> properties;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
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

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransportMeta)) return false;

        TransportMeta that = (TransportMeta) o;

        if (getLevel() != that.getLevel()) return false;
        if (getVersion() != null ? !getVersion().equals(that.getVersion()) : that.getVersion() != null) return false;
        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getDatetime() != null ? !getDatetime().equals(that.getDatetime()) : that.getDatetime() != null)
            return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getOrigin(), that.getOrigin())) return false;
        return getProperties() != null ? getProperties().equals(that.getProperties()) : that.getProperties() == null;

    }

    @Override
    public int hashCode() {
        int result = getVersion() != null ? getVersion().hashCode() : 0;
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getDatetime() != null ? getDatetime().hashCode() : 0);
        result = 31 * result + Arrays.hashCode(getOrigin());
        result = 31 * result + getLevel();
        result = 31 * result + (getProperties() != null ? getProperties().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TransportMeta{" +
                "version='" + version + '\'' +
                ", id='" + id + '\'' +
                ", datetime='" + datetime + '\'' +
                ", origin=" + Arrays.toString(origin) +
                ", level=" + level +
                ", properties=" + properties +
                '}';
    }
}
