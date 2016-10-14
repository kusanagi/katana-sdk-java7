package com.katana.api.replies;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by juan on 30/09/16.
 */
public class CommandReplyPayload {

    @JsonProperty("cr")
    private CommandReply commandReply;

    public CommandReply getCommandReply() {
        return commandReply;
    }

    public void setCommandReply(CommandReply commandReply) {
        this.commandReply = commandReply;
    }
}
