package com.katana.api.replies;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.api.Error;

/**
 * Created by juan on 26/09/16.
 */
public class ErrorPayload {

    @JsonProperty("E")
    private Error error;

    public ErrorPayload() {
        //Empty constructor for serialization
    }

    /**
     * @return
     */
    public Error getError() {
        return error;
    }

    /**
     * @param error
     */
    public void setError(Error error) {
        this.error = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ErrorPayload)) {
            return false;
        }

        ErrorPayload that = (ErrorPayload) o;

        return getError().equals(that.getError());

    }

    @Override
    public int hashCode() {
        return getError().hashCode();
    }

    @Override
    public String toString() {
        return "ErrorPayload{" +
                "error=" + error +
                '}';
    }
}
