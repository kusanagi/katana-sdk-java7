package com.katana.api.replies;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by juan on 30/09/16.
 */
public class CommandReply {

    @JsonProperty("n")
    private String name;

    @JsonProperty("r")
    private CommandReplyResult commandReplyResult;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CommandReplyResult getCommandReplyResult() {
        return commandReplyResult;
    }

    public void setCommandReplyResult(CommandReplyResult commandReplyResult) {
        this.commandReplyResult = commandReplyResult;
    }
}
