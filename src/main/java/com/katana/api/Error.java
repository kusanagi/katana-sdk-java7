package com.katana.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by juan on 26/09/16.
 */
public class Error {

    @JsonProperty("m")
    private String message;

    @JsonProperty("c")
    private int code;

    @JsonProperty("s")
    private String status;

    public Error() {
    }

    /**
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Error)) {
            return false;
        }

        Error error = (Error) o;

        if (getCode() != error.getCode()) {
            return false;
        }
        if (!getMessage().equals(error.getMessage())) {
            return false;
        }
        return getStatus().equals(error.getStatus());

    }

    @Override
    public int hashCode() {
        int result = getMessage().hashCode();
        result = 31 * result + getCode();
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

    public Error(Error other) {
        this.message = other.message;
        this.code = other.code;
        this.status = other.status;
    }
}
