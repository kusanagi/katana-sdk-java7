package com.katana.api;

/**
 * Created by juan on 14/09/16.
 */
public class Transport {

    private Meta meta;
    private Object body;
    private Object files;
    private Object data;
    private Object relations;
    private Object links;
    private Object calls;
    private Object transactions;
    private Object errors;

    public Transport(String message) {

    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Object getFiles() {
        return files;
    }

    public void setFiles(Object files) {
        this.files = files;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getRelations() {
        return relations;
    }

    public void setRelations(Object relations) {
        this.relations = relations;
    }

    public Object getLinks() {
        return links;
    }

    public void setLinks(Object links) {
        this.links = links;
    }

    public Object getCalls() {
        return calls;
    }

    public void setCalls(Object calls) {
        this.calls = calls;
    }

    public Object getTransactions() {
        return transactions;
    }

    public void setTransactions(Object transactions) {
        this.transactions = transactions;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }
}
