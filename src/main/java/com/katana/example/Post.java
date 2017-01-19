package com.katana.example;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by juan on 13/01/17.
 */
public class Post {

    @JsonProperty("id")
    private int id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("user_id")
    private int userId;

    public Post(int id, String title, int userId) {
        this.id = id;
        this.title = title;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
