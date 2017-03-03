package com.katana.sdk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by juan on 3/01/17.
 */
public class ActionSchema {

    private String name;

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

    @JsonProperty("dc")
    private String[][] deferredCalls;

    @JsonProperty("rc")
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

    @JsonProperty("R")
    private ReturnSchema returnObject;

    public ActionSchema() {
        timeout = 1000;
        pathDelimiter = "/";
        primaryKey = "id";
        collection = false;
        deprecated = false;
        files = new HashMap<>();
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

    public void setName(String name) {
        this.name = name;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setEntityPath(String entityPath) {
        this.entityPath = entityPath;
    }

    public void setPathDelimiter(String pathDelimiter) {
        this.pathDelimiter = pathDelimiter;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void setCollection(boolean collection) {
        this.collection = collection;
    }

    public void setCalls(String[][] calls) {
        this.calls = calls;
    }

    public String[][] getDeferredCalls() {
        return deferredCalls;
    }

    public void setDeferredCalls(String[][] deferredCalls) {
        this.deferredCalls = deferredCalls;
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

    public void setDeprecated(boolean deprecated) {
        this.deprecated = deprecated;
    }

    public ActionHttpSchema getHttp() {
        return http;
    }

    public void setHttp(ActionHttpSchema http) {
        this.http = http;
    }

    public void setParams(Map<String, ActionParamSchema> params) {
        this.params = params;
    }

    public void setFiles(Map<String, FileSchema> files) {
        this.files = files;
    }

    public void setEntity(EntitySchema entity) {
        this.entity = entity;
    }

    public void setRelations(List<RelationSchema> relations) {
        this.relations = relations;
    }

    //SDM Methods

    public boolean isDeprecated() {
        return deprecated;
    }

    public boolean isCollection() {
        return collection;
    }

    @JsonIgnore
    public String getName() {
        return name;
    }

    public String getEntityPath() {
        return entityPath;
    }

    public String getPathDelimiter() {
        return pathDelimiter;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public Object resolveEntity(Map<String, Object> data) {
        if (this.entityPath == null || this.entityPath.isEmpty()) {
            return data;
        }
        String[] keys = this.entityPath.split(this.pathDelimiter);
        for (String key : keys) {
            if (!data.containsKey(key) && data.get(key) instanceof Map) {
                throw new IllegalArgumentException("Cannot resolve entity");
            }
            data = (Map) data.get(key);
        }
        return data;
    }

    public boolean hasEntity() {
        return this.entity != null;
    }

    public EntitySchema getEntity() {
        return entity;
    }

    public boolean hasRelations() {
        return this.relations != null && !this.relations.isEmpty();
    }

    public List<RelationSchema> getRelations() {
        return relations;
    }

    public boolean hasCall(String name, String version, String action) {
        for (String[] call : this.calls) {
            if (call[0].equals(name) && call[1].equals(version) && call[2].equals(action)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCalls() {
        return this.calls != null && this.calls.length != 0;
    }

    public String[][] getCalls() {
        return calls;
    }

    public boolean hasDeferCall(String name, String version, String action) {
        for (String[] call : this.deferredCalls) {
            if (call[0].equals(name) && call[1].equals(version) && call[2].equals(action)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasDeferCalls() {
        return this.deferredCalls != null && this.deferredCalls.length != 0;
    }

    public String[][] getDeferCalls() {
        return deferredCalls;
    }

    public boolean hasRemoteCall(String address, String name, String version, String action) {
        for (String[] call : this.remoteCalls) {
            if (call[0].equals(address) && call[1].equals(name) &&
                    call[2].equals(version) && call[3].equals(action)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasRemoteCalls() {
        return this.remoteCalls != null && this.remoteCalls.length != 0;
    }

    public String[][] getRemoteCalls() {
        return remoteCalls;
    }

    public boolean hasReturn() {
        return this.returnObject != null || this.returnObject.getType() == null;
    }

    public String getReturnType() {
        return this.returnObject.getType();
    }

    public Map<String, ActionParamSchema> getParams() {
        return params;
    }

    public boolean hasParam(String name) {
        return this.params != null && this.params.containsKey(name);
    }

    public ActionParamSchema getParamSchema(String name) {
        return this.params.get(name);
    }

    public Map<String, FileSchema> getFiles() {
        return files;
    }

    public boolean hasFile(String name) {
        return this.files != null && this.files.containsKey(name);
    }

    public FileSchema getFileSchema(String name) {
        if (this.files.containsKey(name)) {
            FileSchema fileSchema = this.files.get(name);
            fileSchema.setName(name);
            return fileSchema;
        }
        return null;
    }

    @JsonIgnore
    public ActionHttpSchema getHttpSchema() {
        return getHttp();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ActionSchema that = (ActionSchema) o;

        if (timeout != that.timeout) {
            return false;
        }
        if (collection != that.collection) {
            return false;
        }
        if (deprecated != that.deprecated) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (entityPath != null ? !entityPath.equals(that.entityPath) : that.entityPath != null) {
            return false;
        }
        if (pathDelimiter != null ? !pathDelimiter.equals(that.pathDelimiter) : that.pathDelimiter != null) {
            return false;
        }
        if (primaryKey != null ? !primaryKey.equals(that.primaryKey) : that.primaryKey != null) {
            return false;
        }
        if (!Arrays.deepEquals(calls, that.calls)) {
            return false;
        }
        if (!Arrays.deepEquals(remoteCalls, that.remoteCalls)) {
            return false;
        }
        if (fallbacks != null ? !fallbacks.equals(that.fallbacks) : that.fallbacks != null) {
            return false;
        }
        if (http != null ? !http.equals(that.http) : that.http != null) {
            return false;
        }
        if (params != null ? !params.equals(that.params) : that.params != null) {
            return false;
        }
        if (files != null ? !files.equals(that.files) : that.files != null) {
            return false;
        }
        if (entity != null ? !entity.equals(that.entity) : that.entity != null) {
            return false;
        }
        return relations != null ? relations.equals(that.relations) : that.relations == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + timeout;
        result = 31 * result + (entityPath != null ? entityPath.hashCode() : 0);
        result = 31 * result + (pathDelimiter != null ? pathDelimiter.hashCode() : 0);
        result = 31 * result + (primaryKey != null ? primaryKey.hashCode() : 0);
        result = 31 * result + (collection ? 1 : 0);
        result = 31 * result + Arrays.deepHashCode(calls);
        result = 31 * result + Arrays.deepHashCode(remoteCalls);
        result = 31 * result + (fallbacks != null ? fallbacks.hashCode() : 0);
        result = 31 * result + (deprecated ? 1 : 0);
        result = 31 * result + (http != null ? http.hashCode() : 0);
        result = 31 * result + (params != null ? params.hashCode() : 0);
        result = 31 * result + (files != null ? files.hashCode() : 0);
        result = 31 * result + (entity != null ? entity.hashCode() : 0);
        result = 31 * result + (relations != null ? relations.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ActionSchema{" +
                "name='" + name + '\'' +
                ", timeout=" + timeout +
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
}
