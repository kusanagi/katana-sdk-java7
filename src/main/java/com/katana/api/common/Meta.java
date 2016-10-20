package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by juan on 14/09/16.
 * Katana Java SDK
 */
public class Meta {

    @JsonProperty("v")
    private String version;

    @JsonProperty("i")
    private String id;

    @JsonProperty("d")
    private String datetime;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meta)) return false;

        Meta meta = (Meta) o;

        if (!getVersion().equals(meta.getVersion())) return false;
        if (!getId().equals(meta.getId())) return false;
        return getDatetime().equals(meta.getDatetime());

    }

    @Override
    public int hashCode() {
        int result = getVersion().hashCode();
        result = 31 * result + getId().hashCode();
        result = 31 * result + getDatetime().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "version='" + version + '\'' +
                ", id='" + id + '\'' +
                ", datetime='" + datetime + '\'' +
                '}';
    }
}
