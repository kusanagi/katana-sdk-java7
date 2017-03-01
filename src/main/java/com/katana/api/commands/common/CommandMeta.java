package com.katana.api.commands.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by juan on 26/09/16.
 */
public class CommandMeta {

    @JsonProperty("s")
    private String scope;

    public CommandMeta() {
        //Empty constructor for serialization
    }

    public CommandMeta(CommandMeta other) {
        this.scope = other.scope;
    }

    /**
     * @return
     */
    public String getScope() {
        return scope;
    }

    /**
     * @param scope
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommandMeta)) {
            return false;
        }

        CommandMeta that = (CommandMeta) o;

        return getScope().equals(that.getScope());

    }

    @Override
    public int hashCode() {
        return getScope().hashCode();
    }

    @Override
    public String toString() {
        return "CommandMeta{" +
                "scope='" + scope + '\'' +
                '}';
    }
}
