package com.katana.api.replies;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.api.common.Call;

/**
 * Created by juan on 30/09/16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CallReplyPayload implements CommandReplyResult {

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
        if (!(o instanceof CallReplyPayload)) {
            return false;
        }

        CallReplyPayload that = (CallReplyPayload) o;

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

        @JsonProperty("c")
        private Call call;

        /**
         *
         * @return
         */
        public Call getCall() {
            return call;
        }

        /**
         *
         * @param call
         */
        public void setCall(Call call) {
            this.call = call;
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

            return getCall().equals(result.getCall());

        }

        @Override
        public int hashCode() {
            return getCall().hashCode();
        }

        @Override
        public String toString() {
            return "Result{" +
                    "call=" + call +
                    '}';
        }
    }
}
