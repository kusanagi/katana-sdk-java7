package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by juan on 14/09/16.
 * Katana Java SDK
 */
public class File {

    private String name;

    @JsonProperty("p")
    private String path;

    @JsonProperty("t")
    private String token;

    @JsonProperty("f")
    private String filename;

    @JsonProperty("s")
    private String size;

    @JsonProperty("m")
    private String mime;

    @JsonIgnore
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    //SDK Methods

    public boolean exists(){
        //TODO Review
        return path != null && !path.isEmpty();
    }

    public String read(){
        //TODO implement this method
        return "";
    }

    public File copyWithName(String name){
        File file = new File();
        file.setName(name);
        file.setFilename(this.filename);
        file.setMime(this.mime);
        file.setPath(this.path);
        file.setSize(this.size);
        file.setToken(this.token);
        return file;
    }

    public File copyWithMime(String mime){
        File file = new File();
        file.setName(this.name);
        file.setFilename(this.filename);
        file.setMime(mime);
        file.setPath(this.path);
        file.setSize(this.size);
        file.setToken(this.token);
        return file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof File)) {
            return false;
        }

        File file = (File) o;

        if (!getPath().equals(file.getPath())) {
            return false;
        }
        if (!getToken().equals(file.getToken())) {
            return false;
        }
        if (!getFilename().equals(file.getFilename())) {
            return false;
        }
        if (!getSize().equals(file.getSize())) {
            return false;
        }
        return getMime().equals(file.getMime());

    }

    @Override
    public int hashCode() {
        int result = getPath().hashCode();
        result = 31 * result + getToken().hashCode();
        result = 31 * result + getFilename().hashCode();
        result = 31 * result + getSize().hashCode();
        result = 31 * result + getMime().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "File{" +
                "path='" + path + '\'' +
                ", token='" + token + '\'' +
                ", filename='" + filename + '\'' +
                ", size='" + size + '\'' +
                ", mime='" + mime + '\'' +
                '}';
    }
}
