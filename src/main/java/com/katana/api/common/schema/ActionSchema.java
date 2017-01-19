package com.katana.api.common.schema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
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

    @JsonProperty("R")
    private List<RelationSchema> relations;

    public ActionSchema() {
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

    public Object resolveEntity(Object data){
        return null;
    }

    public boolean hasEntity(){
        return this.entity != null;
    }

    public EntitySchema getEntity() {
        return entity;
    }

    public boolean hasRelations(){
        return this.relations != null && !this.relations.isEmpty();
    }

    public List<RelationSchema> getRelations() {
        return relations;
    }

    public boolean hasCall(String name, String version, String action, String callback){
        return false;
    }

    public boolean hasCalls(){
        return this.calls != null && this.calls.length != 0;
    }

    public String[][] getCalls() {
        return calls;
    }

    public boolean hasRemoteCall(String address, String name, String version, String action, String callback){
        return false;
    }

    public boolean hasRemoteCalls(){
        return this.remoteCalls != null && this.remoteCalls.length != 0;
    }

    public String[][] getRemoteCalls() {
        return remoteCalls;
    }

    public Map<String, ActionParamSchema> getParams() {
        return params;
    }

    public boolean hasParam(String name){
        return this.params != null && this.params.containsKey(name);
    }

    public ActionParamSchema getParamSchema(String name){
        return this.params.get(name);
    }

    public Map<String, FileSchema> getFiles() {
        return files;
    }

    public boolean hasFile(String name){
        return this.files != null && this.files.containsKey(name);
    }

    public FileSchema getFileSchema(String name){
        return this.files.get(name);
    }

    public ActionHttpSchema getHttpSchema() {
        return getHttp();
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
