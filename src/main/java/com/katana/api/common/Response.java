package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.api.replies.CommandReplyResult;

import java.util.Map;

/**
 * Created by juan on 27/08/16.
 */
public class Response extends Api implements CommandReplyResult {
    @JsonProperty("T")
    private int type;

    @JsonProperty("m")
    private Meta meta;

    @JsonProperty("R")
    private HttpResponse response;

    @JsonProperty("t")
    private Transport transport;

    public Response() {
    }

    public Response(String path, String name, String version, String platformVersion, Map<String, String> variables, boolean isDebug) {
        super(path, name, version, platformVersion, variables, isDebug);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public HttpResponse getResponse() {
        return response;
    }

    public void setResponse(HttpResponse response) {
        this.response = response;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    // SDK Methods

    public boolean isProtocolVersion(String version){
        return true;
    }

    public String getProtocolVersion(){
        return this.response.getVersion();
    }

    public boolean setProtocolVersion(String version){
        this.response.setVersion(version);
        return true;
    }

    public boolean isStatus(String status){
        return true;
    }

    public String getStatus(){
        return this.response.getStatus();
    }

    public int getStatusCode(){
        String statusCode = this.response.getStatus().split(" ")[0];
        return Integer.valueOf(statusCode);
    }

    public String getStatusText(){
        return this.response.getStatus().split(" ")[1];
    }

    public boolean setStatus(int code, String text){
       this.response.setStatus(code + " " + text);
        return true;
    }

    public boolean hasHeader(String name){
        return this.response.getHeaders().get(name) != null;
    }

    public String getHeader(String name){
        return this.response.getHeaders().get(name);
    }

    public void setHeader(String key, String value) {
        this.response.getHeaders().put(key, value);
    }

    public boolean hasBody(){
        return this.response.getBody() != null;
    }

    public String getBody(){
        return this.response.getBody();
    }

    public boolean setBody(String body){
        this.response.setBody(body);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Response)) return false;
        if (!super.equals(o)) return false;

        Response response1 = (Response) o;

        if (getType() != response1.getType()) return false;
        if (!getMeta().equals(response1.getMeta())) return false;
        if (!getResponse().equals(response1.getResponse())) return false;
        return getTransport().equals(response1.getTransport());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getType();
        result = 31 * result + getMeta().hashCode();
        result = 31 * result + getResponse().hashCode();
        result = 31 * result + getTransport().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "type=" + type +
                ", meta=" + meta +
                ", response=" + response +
                ", transport=" + transport +
                "} " + super.toString();
    }
}
