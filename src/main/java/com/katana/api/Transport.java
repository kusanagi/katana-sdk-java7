package com.katana.api;

import com.katana.api.entity.*;

/**
 * Created by juan on 14/09/16.
 */
public class Transport {

    private Meta meta;
    private File body;
    private File[] files;
    private Data data;
    private Relation[] relations;
    private Link[] links;
    private Call[] calls;
    private Transaction[] transactions;
    private Error[] errors;

    public Transport() {

    }

    public Transport(String message) {

    }

    public String getMessage() {
        return null;
    }

//    public Meta getMeta() {
//        return meta;
//    }
//
//    public void setMeta(Meta meta) {
//        this.meta = meta;
//    }
//
//    public File getBody() {
//        return body;
//    }
//
//    public void setBody(File body) {
//        this.body = body;
//    }
//
//    public File[] getFiles() {
//        return files;
//    }
//
//    public void setFiles(File[] files) {
//        this.files = files;
//    }
//
//    public Data getData() {
//        return data;
//    }
//
//    public void setData(Data data) {
//        this.data = data;
//    }
//
//    public Relation[] getRelations() {
//        return relations;
//    }
//
//    public void setRelations(Relation[] relations) {
//        this.relations = relations;
//    }
//
//    public Link[] getLinks() {
//        return links;
//    }
//
//    public void setLinks(Link[] links) {
//        this.links = links;
//    }
//
//    public Call[] getCalls() {
//        return calls;
//    }
//
//    public void setCalls(Call[] calls) {
//        this.calls = calls;
//    }
//
//    public Transaction[] getTransactions() {
//        return transactions;
//    }
//
//    public void setTransactions(Transaction[] transactions) {
//        this.transactions = transactions;
//    }
//
//    public Error[] getErrors() {
//        return errors;
//    }
//
//    public void setErrors(Error[] errors) {
//        this.errors = errors;
//    }

    // SDK Methods

    public String getRequestId(){
        return this.meta.getId();
    }

    public String getRequestTimeStamp(){
        return this.meta.getDatetime();
    }

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

    public File getDownload(){
        return this.body;
    }

    public Object getData(String service, String version, String action){
        return null;
    }

    public Relation[] getRelations(String service){
        return this.relations;
    }

    public Link[] getLinks(String service){
        return this.links;
    }

    public Call[] getCalls(){
        return this.calls;
    }

    public Transaction[] getTransactions(String service){
        return this.transactions;
    }

    public Error[] getErrors(String service){
        return this.errors;
    }

}
