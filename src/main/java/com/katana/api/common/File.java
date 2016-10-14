package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by juan on 14/09/16.
 * Katana Java SDK
 */
public class File {

    @JsonProperty("n")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof File)) return false;

        File file = (File) o;

        return getName().equals(file.getName());

    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
