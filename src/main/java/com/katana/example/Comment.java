package com.katana.example;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by juan on 13/01/17.
 */
public class Comment {

    @JsonProperty("id")
    private int id;

    @JsonProperty("post_id")
    private int postId;

    @JsonProperty("text")
    private String text;

    @JsonProperty("user_id")
    private int userId;

    public Comment(int id, int postId, String text, int userId) {
        this.id = id;
        this.postId = postId;
        this.text = text;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
