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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandReplyPayload)) return false;

        CommandReplyPayload that = (CommandReplyPayload) o;

        return getCommandReply().equals(that.getCommandReply());

    }

    @Override
    public int hashCode() {
        return getCommandReply().hashCode();
    }

    @Override
    public String toString() {
        return "CommandReplyPayload{" +
                "commandReply=" + commandReply +
                '}';
    }

    /**
     * Created by juan on 30/09/16.
     */
    public static class CommandReply {

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CommandReply)) return false;

            CommandReply that = (CommandReply) o;

            if (!getName().equals(that.getName())) return false;
            return getCommandReplyResult().equals(that.getCommandReplyResult());

        }

        @Override
        public int hashCode() {
            int result = getName().hashCode();
            result = 31 * result + getCommandReplyResult().hashCode();
            return result;
        }
    }
}
