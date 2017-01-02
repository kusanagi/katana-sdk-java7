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

    private boolean exists;

    public File() {
    }

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

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    //SDK Methods

    public boolean exists() {
        return this.exists;
    }

    public String read() {
        //TODO implement this method
        return "";
    }

    public File copyWithName(String name) {
        File file = new File();
        file.setName(name);
        file.setFilename(this.filename);
        file.setMime(this.mime);
        file.setPath(this.path);
        file.setSize(this.size);
        file.setToken(this.token);
        file.setExists(this.exists);
        return file;
    }

    public File copyWithMime(String mime) {
        File file = new File();
        file.setName(this.name);
        file.setFilename(this.filename);
        file.setMime(mime);
        file.setPath(this.path);
        file.setSize(this.size);
        file.setToken(this.token);
        file.setExists(this.exists);
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

        if (exists != file.exists) {
            return false;
        }
        if (getName() != null ? !getName().equals(file.getName()) : file.getName() != null) {
            return false;
        }
        if (getPath() != null ? !getPath().equals(file.getPath()) : file.getPath() != null) {
            return false;
        }
        if (getToken() != null ? !getToken().equals(file.getToken()) : file.getToken() != null) {
            return false;
        }
        if (getFilename() != null ? !getFilename().equals(file.getFilename()) : file.getFilename() != null) {
            return false;
        }
        if (getSize() != null ? !getSize().equals(file.getSize()) : file.getSize() != null) {
            return false;
        }
        return getMime() != null ? getMime().equals(file.getMime()) : file.getMime() == null;

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getPath() != null ? getPath().hashCode() : 0);
        result = 31 * result + (getToken() != null ? getToken().hashCode() : 0);
        result = 31 * result + (getFilename() != null ? getFilename().hashCode() : 0);
        result = 31 * result + (getSize() != null ? getSize().hashCode() : 0);
        result = 31 * result + (getMime() != null ? getMime().hashCode() : 0);
        result = 31 * result + (exists ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", token='" + token + '\'' +
                ", filename='" + filename + '\'' +
                ", size='" + size + '\'' +
                ", mime='" + mime + '\'' +
                ", exists=" + exists +
                '}';
    }

    public File(File other) {
        this.name = other.name;
        this.path = other.path;
        this.token = other.token;
        this.filename = other.filename;
        this.size = other.size;
        this.mime = other.mime;
        this.exists = other.exists;
    }
}
