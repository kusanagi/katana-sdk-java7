package com.katana.api.commands.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by juan on 26/09/16.
 */
public class CommandMeta {

    @JsonProperty("s")
    private String service;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandMeta)) return false;

        CommandMeta that = (CommandMeta) o;

        return getService().equals(that.getService());

    }

    @Override
    public int hashCode() {
        return getService().hashCode();
    }

    @Override
    public String toString() {
        return "CommandMeta{" +
                "service='" + service + '\'' +
                '}';
    }
}
