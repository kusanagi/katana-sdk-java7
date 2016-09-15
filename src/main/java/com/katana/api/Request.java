package com.katana.api;

import com.katana.api.entity.Call;
import com.katana.api.entity.File;
import com.katana.api.entity.HttpRequest;
import com.katana.api.entity.Meta;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by juan on 27/08/16.
 */
public class Request extends Api {
    private int type;
    private Meta meta;
    private HttpRequest request;
    private Call call;

    public Request(String path, String name, String version, String platformVersion, Map<String, String> variables, boolean isDebug) {
        super(path, name, version, platformVersion, variables, isDebug);
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Meta getMeta() {
        return this.meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public HttpRequest getRequest() {
        return this.request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public Call getCall() {
        return this.call;
    }

    public void setCall(Call call) {
        this.call = call;
    }

    // SDK Methods

    public boolean isMethod(String method){
        return true;
    }

    public String getMethod(){
        return this.request.getMethod();
    }

    public String getUrl(){
        return this.request.getUrl();
    }

    public String getUrlScheme(){
        return "";
    }

    public String getUrlHost(){
        return "";
    }

    public String getUrlPath(){
        return "";
    }

    public boolean hasQueryParam(String name){
        return true;
    }

    public String getQueryParam(String name){
        return "";
    }

    public String[] getQueryParamArray(){
        return new String[0];
    }

    public Map<String, String> getQueryParam(){
        return this.request.getQuery();
    }

    public Map<String, String[]> getQueryParamsArray(){
        return new HashMap<>();
    }

    public boolean hasPostParam(String name){
        return true;
    }

    public String getPostParam(String name){
       return "";
    }

    public String[] getPostParamArray(String name){
        return new String[0];
    }

    public Map<String, String> getPostParams(){
        return this.request.getPostData();
    }

    public Map<String, String[]> getPostParamsArray(){
        return new HashMap<>();
    }

    public boolean isPortocolVersion(String version){
        return true;
    }

    public String getProtocolVersion(){
        return this.request.getVersion();
    }

    public boolean hasHeader(String name){
        return true;
    }

    public String getHeader(String name){
        return this.request.getHeaders().get(name);
    }

    public Map<String, String> getHeaders(){
        return this.request.getHeaders();
    }

    public boolean hasBody(){
        return true;
    }

    public String getBody(){
        return request.getBody();
    }

    public boolean hasFile(String name){
        return true;
    }

    public File getFile(String name){
        return new File();
    }

    public File[] getFiles(){
        return this.request.getFiles();
    }

    public String getServiceName(){
        return this.call.getService();
    }

    public boolean setServiceName(String name){
        this.call.setService(name);
        return true;
    }

    public String getServiceVersion(){
        return this.call.getVersion();
    }

    public boolean setServiceVersion(String version){
        this.call.setVersion(version);
        return true;
    }

    public String getActionName(){
        return this.call.getAction();
    }

    public boolean setActionName(String action){
        this.call.setAction(action);
        return true;
    }

    public Response newResponse(int code, String text) {
        return null;
    }

}
