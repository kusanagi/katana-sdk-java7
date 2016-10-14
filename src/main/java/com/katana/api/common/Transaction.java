package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by juan on 15/09/16.
 * Katana Java SDK
 */
public class Transaction {

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
        if (!(o instanceof Transaction)) return false;

        Transaction that = (Transaction) o;

        return getName().equals(that.getName());

    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
