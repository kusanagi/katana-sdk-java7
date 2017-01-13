package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import sun.misc.IOUtils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by juan on 14/09/16.
 * Katana Java SDK
 */
public class File {

    private String name;

    @JsonProperty("p")
    private String path;

    @JsonProperty("m")
    private String mime;

    @JsonProperty("f")
    private String filename;

    @JsonProperty("s")
    private String size;

    @JsonProperty("t")
    private String token;

    private boolean exists;

    public File() {
    }

    public File(String name, String path, String mime, String filename, String size, String token, boolean exists) {
        this.name = name;
        this.path = path;
        this.mime = mime;
        this.filename = filename;
        this.size = size;
        this.token = token;
        this.exists = exists;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    //SDK Methods

    @JsonIgnore
    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getMime() {
        return mime;
    }

    public String getFilename() {
        return filename;
    }

    public String getSize() {
        return size;
    }

    public String getToken() {
        return token;
    }

    public boolean exists() {
        return this.exists;
    }

    public boolean isLocal() {
        try {
            URI uri = new URI(path);
            if (uri.getScheme().equals("file")) {
                return true;
            }
            return false;
        } catch (URISyntaxException e) {
            return false;
        }
    }

    public String read() {
        try {
            return new String(Files.readAllBytes(Paths.get(this.path)));
        } catch (IOException e) {
            return null;
        }
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
