package com.katana.api.commands.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.api.Api;

/**
 * Created by juan on 26/09/16.
 */

/**
 * @param <T>
 */
public class CommandPayload<T extends Api> {

    @JsonProperty("m")
    private CommandMeta commandMeta;

    /**
     * @return
     */
    public CommandMeta getCommandMeta() {
        return commandMeta;
    }

    /**
     * @param commandMeta
     */
    public void setCommandMeta(CommandMeta commandMeta) {
        this.commandMeta = commandMeta;
    }

    /**
     * @return
     */
    public Command<T> getCommand() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommandPayload)) {
            return false;
        }

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

    /**
     * @param <T>
     */
    public static class Command<T extends Api> {

        @JsonProperty("n")
        private String name;

        /**
         * @return
         */
        public String getName() {
            return name;
        }

        /**
         * @param name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return
         */
        public T getArgument() {
            return null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Command)) {
                return false;
            }

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
