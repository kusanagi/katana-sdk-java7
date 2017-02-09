package com.katana.api.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Created by juan on 3/01/17.
 */
public class TransportSchema {

    @JsonProperty("p")
    private Map<String, String> properties;

    @JsonProperty("d")
    private List<Map<String, ValueSchema>> data;

    @JsonProperty("r")
    private List<List<Object>> relations;

    @JsonProperty("l")
    private Map<String, String> links;

    @JsonProperty("e")
    private List<List<String>> errors;

    public TransportSchema() {
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public List<Map<String, ValueSchema>> getData() {
        return data;
    }

    public void setData(List<Map<String, ValueSchema>> data) {
        this.data = data;
    }

    public List<List<Object>> getRelations() {
        return relations;
    }

    public void setRelations(List<List<Object>> relations) {
        this.relations = relations;
    }

    public Map<String, String> getLinks() {
        return links;
    }

    public void setLinks(Map<String, String> links) {
        this.links = links;
    }

    public List<List<String>> getErrors() {
        return errors;
    }

    public void setErrors(List<List<String>> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransportSchema)) {
            return false;
        }

        TransportSchema that = (TransportSchema) o;

        if (getProperties() != null ? !getProperties().equals(that.getProperties()) : that.getProperties() != null) {
            return false;
        }
        if (getData() != null ? !getData().equals(that.getData()) : that.getData() != null) {
            return false;
        }
        if (getRelations() != null ? !getRelations().equals(that.getRelations()) : that.getRelations() != null) {
            return false;
        }
        if (getLinks() != null ? !getLinks().equals(that.getLinks()) : that.getLinks() != null) {
            return false;
        }
        return getErrors() != null ? getErrors().equals(that.getErrors()) : that.getErrors() == null;

    }

    @Override
    public int hashCode() {
        int result = getProperties() != null ? getProperties().hashCode() : 0;
        result = 31 * result + (getData() != null ? getData().hashCode() : 0);
        result = 31 * result + (getRelations() != null ? getRelations().hashCode() : 0);
        result = 31 * result + (getLinks() != null ? getLinks().hashCode() : 0);
        result = 31 * result + (getErrors() != null ? getErrors().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TransportSchema{" +
                "properties=" + properties +
                ", data=" + data +
                ", relations=" + relations +
                ", links=" + links +
                ", errors=" + errors +
                '}';
    }

    public TransportSchema(TransportSchema other) {
        this.properties = other.properties;
        this.data = other.data;
        this.relations = other.relations;
        this.links = other.links;
        this.errors = other.errors;
    }
}
