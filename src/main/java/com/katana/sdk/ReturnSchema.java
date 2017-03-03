package com.katana.sdk;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jega on 3/03/17.
 */
public class ReturnSchema {

    @JsonProperty("t")
    private String type;

    @JsonProperty("e")
    private boolean allowEmpty;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAllowEmpty() {
        return allowEmpty;
    }

    public void setAllowEmpty(boolean allowEmpty) {
        this.allowEmpty = allowEmpty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReturnSchema that = (ReturnSchema) o;

        if (allowEmpty != that.allowEmpty) {
            return false;
        }
        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (allowEmpty ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ReturnSchema{" +
                "type='" + type + '\'' +
                ", allowEmpty=" + allowEmpty +
                '}';
    }
}
