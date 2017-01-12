package com.katana.api.common.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by juan on 3/01/17.
 */
public class ActionSchema {

    @JsonProperty("x")
    private int timeout;

    @JsonProperty("e")
    private String entityPath;

    @JsonProperty("d")
    private String pathDelimiter;

    @JsonProperty("k")
    private String primaryKey;

    @JsonProperty("c")
    private boolean collection;

    @JsonProperty("C")
    private String[][] calls;

    @JsonProperty("r")
    private String[][] remoteCalls;

    @JsonProperty("F")
    private Map<String, TransportSchema> fallbacks;

    @JsonProperty("D")
    private boolean deprecated;

    @JsonProperty("h")
    private ActionHttpSchema http;

    @JsonProperty("p")
    private Map<String, ActionParamSchema> params;

    @JsonProperty("f")
    private Map<String, FileSchema> files;

    @JsonProperty("E")
    private EntitySchema entity;

    @JsonProperty("r")
    private List<RelationSchema> relations;

    public ActionSchema() {
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getEntityPath() {
        return entityPath;
    }

    public void setEntityPath(String entityPath) {
        this.entityPath = entityPath;
    }

    public String getPathDelimiter() {
        return pathDelimiter;
    }

    public void setPathDelimiter(String pathDelimiter) {
        this.pathDelimiter = pathDelimiter;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public boolean isCollection() {
        return collection;
    }

    public void setCollection(boolean collection) {
        this.collection = collection;
    }

    public String[][] getCalls() {
        return calls;
    }

    public void setCalls(String[][] calls) {
        this.calls = calls;
    }

    public String[][] getRemoteCalls() {
        return remoteCalls;
    }

    public void setRemoteCalls(String[][] remoteCalls) {
        this.remoteCalls = remoteCalls;
    }

    public Map<String, TransportSchema> getFallbacks() {
        return fallbacks;
    }

    public void setFallbacks(Map<String, TransportSchema> fallbacks) {
        this.fallbacks = fallbacks;
    }

    public boolean isDeprecated() {
        return deprecated;
    }

    public void setDeprecated(boolean deprecated) {
        this.deprecated = deprecated;
    }

    public ActionHttpSchema getHttp() {
        return http;
    }

    public void setHttp(ActionHttpSchema http) {
        this.http = http;
    }

    public Map<String, ActionParamSchema> getParams() {
        return params;
    }

    public void setParams(Map<String, ActionParamSchema> params) {
        this.params = params;
    }

    public Map<String, FileSchema> getFiles() {
        return files;
    }

    public void setFiles(Map<String, FileSchema> files) {
        this.files = files;
    }

    public EntitySchema getEntity() {
        return entity;
    }

    public void setEntity(EntitySchema entity) {
        this.entity = entity;
    }

    public List<RelationSchema> getRelations() {
        return relations;
    }

    public void setRelations(List<RelationSchema> relations) {
        this.relations = relations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActionSchema)) {
            return false;
        }

        ActionSchema that = (ActionSchema) o;

        if (getTimeout() != that.getTimeout()) {
            return false;
        }
        if (isCollection() != that.isCollection()) {
            return false;
        }
        if (isDeprecated() != that.isDeprecated()) {
            return false;
        }
        if (getEntityPath() != null ? !getEntityPath().equals(that.getEntityPath()) : that.getEntityPath() != null) {
            return false;
        }
        if (getPathDelimiter() != null ? !getPathDelimiter().equals(that.getPathDelimiter()) : that.getPathDelimiter() != null) {
            return false;
        }
        if (getPrimaryKey() != null ? !getPrimaryKey().equals(that.getPrimaryKey()) : that.getPrimaryKey() != null) {
            return false;
        }
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getCalls(), that.getCalls())) {
            return false;
        }
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getRemoteCalls(), that.getRemoteCalls())) {
            return false;
        }
        if (getFallbacks() != null ? !getFallbacks().equals(that.getFallbacks()) : that.getFallbacks() != null) {
            return false;
        }
        if (getHttp() != null ? !getHttp().equals(that.getHttp()) : that.getHttp() != null) {
            return false;
        }
        if (getParams() != null ? !getParams().equals(that.getParams()) : that.getParams() != null) {
            return false;
        }
        if (getFiles() != null ? !getFiles().equals(that.getFiles()) : that.getFiles() != null) {
            return false;
        }
        if (getEntity() != null ? !getEntity().equals(that.getEntity()) : that.getEntity() != null) {
            return false;
        }
        return getRelations() != null ? getRelations().equals(that.getRelations()) : that.getRelations() == null;

    }

    @Override
    public int hashCode() {
        int result = getTimeout();
        result = 31 * result + (getEntityPath() != null ? getEntityPath().hashCode() : 0);
        result = 31 * result + (getPathDelimiter() != null ? getPathDelimiter().hashCode() : 0);
        result = 31 * result + (getPrimaryKey() != null ? getPrimaryKey().hashCode() : 0);
        result = 31 * result + (isCollection() ? 1 : 0);
        result = 31 * result + Arrays.hashCode(getCalls());
        result = 31 * result + Arrays.hashCode(getRemoteCalls());
        result = 31 * result + (getFallbacks() != null ? getFallbacks().hashCode() : 0);
        result = 31 * result + (isDeprecated() ? 1 : 0);
        result = 31 * result + (getHttp() != null ? getHttp().hashCode() : 0);
        result = 31 * result + (getParams() != null ? getParams().hashCode() : 0);
        result = 31 * result + (getFiles() != null ? getFiles().hashCode() : 0);
        result = 31 * result + (getEntity() != null ? getEntity().hashCode() : 0);
        result = 31 * result + (getRelations() != null ? getRelations().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ActionSchema{" +
                "timeout=" + timeout +
                ", entityPath='" + entityPath + '\'' +
                ", pathDelimiter='" + pathDelimiter + '\'' +
                ", primaryKey='" + primaryKey + '\'' +
                ", collection=" + collection +
                ", calls=" + Arrays.toString(calls) +
                ", remoteCalls=" + Arrays.toString(remoteCalls) +
                ", fallbacks=" + fallbacks +
                ", deprecated=" + deprecated +
                ", http=" + http +
                ", params=" + params +
                ", files=" + files +
                ", entity=" + entity +
                ", relations=" + relations +
                '}';
    }

    public ActionSchema(ActionSchema other) {
        this.timeout = other.timeout;
        this.entityPath = other.entityPath;
        this.pathDelimiter = other.pathDelimiter;
        this.primaryKey = other.primaryKey;
        this.collection = other.collection;
        this.calls = other.calls;
        this.remoteCalls = other.remoteCalls;
        this.fallbacks = other.fallbacks;
        this.deprecated = other.deprecated;
        this.http = other.http;
        this.params = other.params;
        this.files = other.files;
        this.entity = other.entity;
        this.relations = other.relations;
    }
}
