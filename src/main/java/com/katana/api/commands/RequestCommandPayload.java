package com.katana.api.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.api.commands.common.CommandPayload;
import com.katana.api.Request;

/**
 * Created by juan on 26/09/16.
 */
public class RequestCommandPayload extends CommandPayload<Request> {
    @JsonProperty("c")
    private RequestCommand command;

    /**
     * @return
     */
    @Override
    public RequestCommand getCommand() {
        return command;
    }

    /**
     * @param command
     */
    public void setCommand(RequestCommand command) {
        this.command = command;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RequestCommandPayload)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        RequestCommandPayload that = (RequestCommandPayload) o;

        return getCommand().equals(that.getCommand());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getCommand().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RequestCommandPayload{" +
                "command=" + command +
                "} " + super.toString();
    }

    /**
     * Created by juan on 26/09/16.
     */
    public static class RequestCommand extends Command<Request> {

        @JsonProperty("a")
        private Request argument;

        /**
         * @return
         */
        @Override
        public Request getArgument() {
            return argument;
        }

        /**
         * @param argument
         */
        public void setArgument(Request argument) {
            this.argument = argument;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof RequestCommand)) {
                return false;
            }
            if (!super.equals(o)) {
                return false;
            }

            RequestCommand that = (RequestCommand) o;

            return getArgument().equals(that.getArgument());

        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + getArgument().hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "RequestCommand{" +
                    "argument=" + argument +
                    "} " + super.toString();
        }
    }
}
