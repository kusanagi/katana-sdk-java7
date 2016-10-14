package com.katana.api.commands.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by juan on 26/09/16.
 */
public class CommandPayload<T extends CommandArgument> {

    @JsonProperty("m")
    private CommandMeta commandMeta;

    public CommandMeta getCommandMeta() {
        return commandMeta;
    }

    public void setCommandMeta(CommandMeta commandMeta) {
        this.commandMeta = commandMeta;
    }

    public Command<T> getCommand(){
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandPayload)) return false;

        CommandPayload<?> that = (CommandPayload<?>) o;

        return getCommandMeta().equals(that.getCommandMeta());

    }

    @Override
    public int hashCode() {
        return getCommandMeta().hashCode();
    }

    @Override
    public String toString() {
        return "CommandPayload{" +
                "commandMeta=" + commandMeta +
                '}';
    }

    /**
     * Created by juan on 30/09/16.
     */
    public static class Command<T extends CommandArgument> {

        @JsonProperty("n")
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public T getArgument() {
            return null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Command)) return false;

            Command<?> command = (Command<?>) o;

            return getName().equals(command.getName());

        }

        @Override
        public int hashCode() {
            return getName().hashCode();
        }

        @Override
        public String toString() {
            return "Command{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
