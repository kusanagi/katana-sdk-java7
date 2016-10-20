package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.api.Error;
import com.katana.api.replies.CommandReplyResult;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by juan on 14/09/16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transport implements CommandReplyResult {

    @JsonProperty("m")
    private TransportMeta meta;

    @JsonProperty("b")
    private File body;

    @JsonProperty("f")
    private File[] files;

    @JsonProperty("d")
    private Map<String, Map<String, Map<String, Object>>> data;

    @JsonProperty("r")
    private Relation[] relations;

    @JsonProperty("l")
    private Map<String, Map<String, String>> links;

    @JsonProperty("c")
    private Call[] calls;

    @JsonProperty("t")
    private Transaction[] transactions;

    @JsonProperty("e")
    private List<Error> errors;

    public Transport() {
        this.links = new HashMap<>();
    }

    public TransportMeta getMeta() {
        return meta;
    }

    public void setMeta(TransportMeta meta) {
        this.meta = meta;
    }

    public File getBody() {
        return body;
    }

    public void setBody(File body) {
        this.body = body;
    }

    public File[] getFiles() {
        return files;
    }

    public void setFiles(File[] files) {
        this.files = files;
    }

    public Map<String, Map<String, Map<String, Object>>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, Map<String, Object>>> data) {
        this.data = data;
    }

    public Relation[] getRelations() {
        return relations;
    }

    public void setRelations(Relation[] relations) {
        this.relations = relations;
    }

    public Map<String, Map<String, String>> getLinks() {
        return links;
    }

    public void setLinks(Map<String, Map<String, String>> links) {
        this.links = links;
    }

    public Call[] getCalls() {
        return calls;
    }

    public void setCalls(Call[] calls) {
        this.calls = calls;
    }

    public Transaction[] getTransactions() {
        return transactions;
    }

    public void setTransactions(Transaction[] transactions) {
        this.transactions = transactions;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    // SDK Methods

    @JsonIgnore
    public String getRequestId(){
        return this.meta.getId();
    }

    @JsonIgnore
    public String getRequestTimeStamp(){
        return this.meta.getDatetime();
    }

    @JsonIgnore
    public String[] getOrigin(){
        return new String[2];
    }

    public String getProperty(String name, String defaultString){
        String property = this.meta.getProperties().get(name);
        return property == null ? defaultString == null ? "" : defaultString : property;
    }

    public boolean hasDownload(){
        return this.body != null;
    }

    @JsonIgnore
    public File getDownload(){
        return this.body;
    }

    public Object getData(String service, String version, String action){
        return null;
    }

    public Relation[] getRelations(String service){
        return this.relations;
    }

    public boolean addLink(String name, String link, String uri) {
        Map<String, String> linkDic = new HashMap<>();
        linkDic.put(link, uri);
        this.links.put(name, linkDic);
        return true;
    }

    public Map<String, Map<String, String>> getLinks(String service){
        return this.links;
    }

    public Transaction[] getTransactions(String service){
        return this.transactions;
    }

    public List<Error> getErrors(String service){
        return this.errors;
    }

    public boolean addData(String name, String version, String actionName, Object collection) {
        Map<String, Map<String, Object>> versionMap = new HashMap<>();
        Map<String, Object> actionMap = new HashMap<>();
        actionMap.put(actionName, collection);
        versionMap.put(version, actionMap);
        if (this.data == null) this.data = new HashMap<>();
        this.data.put(name, versionMap);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transport)) return false;

        Transport transport = (Transport) o;

        if (getMeta() != null ? !getMeta().equals(transport.getMeta()) : transport.getMeta() != null) return false;
        if (getBody() != null ? !getBody().equals(transport.getBody()) : transport.getBody() != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getFiles(), transport.getFiles())) return false;
        if (getData() != null ? !getData().equals(transport.getData()) : transport.getData() != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getRelations(), transport.getRelations())) return false;
        if (getLinks() != null ? !getLinks().equals(transport.getLinks()) : transport.getLinks() != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getCalls(), transport.getCalls())) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getTransactions(), transport.getTransactions())) return false;
        return getErrors() != null ? getErrors().equals(transport.getErrors()) : transport.getErrors() == null;

    }

    @Override
    public int hashCode() {
        int result = getMeta() != null ? getMeta().hashCode() : 0;
        result = 31 * result + (getBody() != null ? getBody().hashCode() : 0);
        result = 31 * result + Arrays.hashCode(getFiles());
        result = 31 * result + (getData() != null ? getData().hashCode() : 0);
        result = 31 * result + Arrays.hashCode(getRelations());
        result = 31 * result + (getLinks() != null ? getLinks().hashCode() : 0);
        result = 31 * result + Arrays.hashCode(getCalls());
        result = 31 * result + Arrays.hashCode(getTransactions());
        result = 31 * result + (getErrors() != null ? getErrors().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "meta=" + meta +
                ", body=" + body +
                ", files=" + Arrays.toString(files) +
                ", data=" + data +
                ", relations=" + Arrays.toString(relations) +
                ", links=" + links +
                ", calls=" + Arrays.toString(calls) +
                ", transactions=" + Arrays.toString(transactions) +
                ", errors=" + errors +
                '}';
    }
}
