package com.katana.api.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.api.commands.common.CommandPayload;
import com.katana.api.common.Action;

/**
 * Created by juan on 26/09/16.
 */
public class ActionCommandPayload extends CommandPayload<Action> {
    @JsonProperty("c")
    private ActionCommand command;

    @Override
    public ActionCommand getCommand() {
        return command;
    }

    public void setCommand(ActionCommand command) {
        this.command = command;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActionCommandPayload)) return false;
        if (!super.equals(o)) return false;

        ActionCommandPayload that = (ActionCommandPayload) o;

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
    public static class ActionCommand extends Command<Action> {

        @JsonProperty("a")
        private Action argument;

        @Override
        public Action getArgument() {
            return argument;
        }

        public void setArgument(Action argument) {
            this.argument = argument;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ActionCommand)) return false;
            if (!super.equals(o)) return false;

            ActionCommand that = (ActionCommand) o;

            return getArgument().equals(that.getArgument());

        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + getArgument().hashCode();
            return result;
        }
    }
}
