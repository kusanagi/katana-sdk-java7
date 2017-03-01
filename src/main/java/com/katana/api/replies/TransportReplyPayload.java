package com.katana.api.replies;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.sdk.Transport;
import com.katana.api.replies.common.CommandReplyResult;

/**
 * Created by juan on 30/09/16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransportReplyPayload implements CommandReplyResult {

    @JsonProperty("cr")
    private TransportCommandReply transportCommandReply;

    public TransportReplyPayload() {
        //Empty constructor for serialization
    }

    public TransportReplyPayload(TransportReplyPayload other) {
        this.transportCommandReply = other.transportCommandReply;
    }

    /**
     * @return
     */
    @JsonIgnore
    public TransportCommandReply getCommandReply() {
        return transportCommandReply;
    }

    /**
     * @param transportCommandReply
     */
    public void setCommandReply(TransportCommandReply transportCommandReply) {
        this.transportCommandReply = transportCommandReply;
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
                "transportCommandReply=" + transportCommandReply +
                '}';
    }

    /**
     * Created by juan on 30/09/16.
     */
    public static class TransportCommandReply {

        @JsonProperty("n")
        private String name;

        @JsonProperty("r")
        private TransportResult transportResult;

        /**
         * @return
         */
        @JsonIgnore
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
        @JsonIgnore
        public TransportResult getResult() {
            return transportResult;
        }

        /**
         * @param commandReplyTransportResult
         */
        public void setResult(TransportResult commandReplyTransportResult) {
            this.transportResult = commandReplyTransportResult;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof TransportCommandReply)) {
                return false;
            }

            TransportCommandReply that = (TransportCommandReply) o;

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
            return "CallResult{" +
                    "name='" + name + '\'' +
                    ", transportResult=" + transportResult +
                    '}';
        }
    }

    /**
     *
     */
    public static class TransportResult {

        @JsonProperty("T")
        private Transport transport;

        /**
         * @return
         */
        @JsonIgnore
        public Transport getTransport() {
            return transport;
        }

        /**
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
            if (!(o instanceof TransportResult)) {
                return false;
            }

            TransportResult transportResult = (TransportResult) o;

            return getTransport().equals(transportResult.getTransport());

        }

        @Override
        public int hashCode() {
            return getTransport().hashCode();
        }

        @Override
        public String toString() {
            return "CallResult{" +
                    "transport=" + transport +
                    '}';
        }
    }
}
