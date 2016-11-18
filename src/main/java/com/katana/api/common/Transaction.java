package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by juan on 15/09/16.
 * Katana Java SDK
 */
public class Transaction {

    @JsonProperty("a")
    private String action;

    @JsonProperty("p")
    private List<Param> params;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        this.params = params;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transaction)) {
            return false;
        }

        Transaction that = (Transaction) o;

        if (getAction() != null ? !getAction().equals(that.getAction()) : that.getAction() != null) {
            return false;
        }
        return getParams() != null ? getParams().equals(that.getParams()) : that.getParams() == null;

    }

    @Override
    public int hashCode() {
        int result = getAction() != null ? getAction().hashCode() : 0;
        result = 31 * result + (getParams() != null ? getParams().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "action='" + action + '\'' +
                ", params=" + params +
                '}';
    }
}
