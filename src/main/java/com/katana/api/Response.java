package com.katana.api;

import com.katana.api.entity.HttpResponse;
import com.katana.api.entity.Meta;

import java.util.Map;

/**
 * Created by juan on 27/08/16.
 */
public class Response extends Api{
    private int type;
    private Meta meta;
    private HttpResponse response;
    private Transport transport;

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

}
