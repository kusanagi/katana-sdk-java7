package com.katana.api.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.api.component.Key;
import com.katana.sdk.Request;
import com.katana.api.commands.common.CommandPayload;

/**
 * Created by juan on 26/09/16.
 */
public class RequestCommandPayload extends CommandPayload<Request> {
    @JsonProperty(Key.COMMAND_PAYLOAD_COMMAND)
    private RequestCommand command;

    public RequestCommandPayload() {
        //Empty constructor for serialization
    }

    public RequestCommandPayload(RequestCommandPayload other) {
        super(other);
        this.command = other.command;
    }

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

        @JsonProperty(Key.COMMAND_ARGUMENT)
        private Request argument;

        public RequestCommand() {
            //Empty constructor for serialization
        }

        public RequestCommand(RequestCommand other) {
            super(other);
            this.argument = new Request(other.argument);
        }

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
