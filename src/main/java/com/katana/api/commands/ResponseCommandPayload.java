package com.katana.api.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.api.commands.common.CommandPayload;
import com.katana.api.common.Response;

/**
 * Created by juan on 26/09/16.
 */
public class ResponseCommandPayload extends CommandPayload<Response> {

    @JsonProperty("c")
    private ResponseCommand command;

    @Override
    public ResponseCommand getCommand() {
        return command;
    }

    public void setCommand(ResponseCommand command) {
        this.command = command;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResponseCommandPayload)) return false;
        if (!super.equals(o)) return false;

        ResponseCommandPayload that = (ResponseCommandPayload) o;

        return getCommand().equals(that.getCommand());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getCommand().hashCode();
        return result;
    }

    /**
     * Created by juan on 26/09/16.
     */
    public static class ResponseCommand extends Command<Response> {

        @JsonProperty("a")
        private Response argument;

        @Override
        public Response getArgument() {
            return argument;
        }

        public void setArgument(Response argument) {
            this.argument = argument;
        }
    }
}
