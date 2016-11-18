package com.katana.example;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by juan on 20/10/16.
 */

public class Record {

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    public Record(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}