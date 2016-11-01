package com.katana.api.replies;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.api.common.Transport;

/**
 * Created by juan on 30/09/16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransportReplyPayload implements CommandReplyResult {

    @JsonProperty("cr")
    private CommandReply commandReply;

    /**
     *
     * @return
     */
    public CommandReply getCommandReply() {
        return commandReply;
    }

    /**
     *
     * @param commandReply
     */
    public void setCommandReply(CommandReply commandReply) {
        this.commandReply = commandReply;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransportReplyPayload)) {
            return false;
        }

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

        /**
         *
         * @return
         */
        public String getName() {
            return name;
        }

        /**
         *
         * @param name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         *
         * @return
         */
        public Result getResult() {
            return result;
        }

        /**
         *
         * @param commandReplyResult
         */
        public void setResult(Result commandReplyResult) {
            this.result = commandReplyResult;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof CommandReply)) {
                return false;
            }

            CommandReply that = (CommandReply) o;

            if (!getName().equals(that.getName())) {
                return false;
            }
            return getResult().equals(that.getResult());

        }

        @Override
        public int hashCode() {
            int code = getName().hashCode();
            code = 31 * code + getResult().hashCode();
            return code;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "name='" + name + '\'' +
                    ", result=" + result +
                    '}';
        }
    }

    /**
     *
     */
    public static class Result {

        @JsonProperty("t")
        private Transport transport;

        /**
         *
         * @return
         */
        public Transport getTransport() {
            return transport;
        }

        /**
         *
         * @param transport
         */
        public void setTransport(Transport transport) {
            this.transport = transport;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Result)) {
                return false;
            }

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
