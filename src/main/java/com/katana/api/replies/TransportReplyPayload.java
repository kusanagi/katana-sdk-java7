package com.katana.api.replies;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.api.common.Transport;

/**
 * Created by juan on 30/09/16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransportReplyPayload {

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
        if (!(o instanceof TransportReplyPayload)) return false;

        TransportReplyPayload that = (TransportReplyPayload) o;

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
        private Result result;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Result getResult() {
            return result;
        }

        public void setResult(Result commandReplyResult) {
            this.result = commandReplyResult;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CommandReply)) return false;

            CommandReply that = (CommandReply) o;

            if (!getName().equals(that.getName())) return false;
            return getResult().equals(that.getResult());

        }

        @Override
        public int hashCode() {
            int result = getName().hashCode();
            result = 31 * result + getResult().hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "name='" + name + '\'' +
                    ", result=" + result +
                    '}';
        }
    }

    public static class Result{

        @JsonProperty("t")
        private Transport transport;

        public Transport getTransport() {
            return transport;
        }

        public void setTransport(Transport transport) {
            this.transport = transport;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Result)) return false;

            Result result = (Result) o;

            return getTransport().equals(result.getTransport());

        }

        @Override
        public int hashCode() {
            return getTransport().hashCode();
        }

        @Override
        public String toString() {
            return "Result{" +
                    "transport=" + transport +
                    '}';
        }
    }
}
