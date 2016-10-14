package com.katana.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by juan on 26/09/16.
 */
public class Error {

    @JsonProperty("m")
    private String message;

    @JsonProperty("c")
    private String code;

    @JsonProperty("s")
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Error)) return false;

        Error error = (Error) o;

        if (!getMessage().equals(error.getMessage())) return false;
        if (!getCode().equals(error.getCode())) return false;
        return getStatus().equals(error.getStatus());

    }

    @Override
    public int hashCode() {
        int result = getMessage().hashCode();
        result = 31 * result + getCode().hashCode();
        result = 31 * result + getStatus().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Error{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
