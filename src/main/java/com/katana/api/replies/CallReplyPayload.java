package com.katana.api.replies;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.api.common.RequestCall;

/**
 * Created by juan on 30/09/16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CallReplyPayload implements CommandReplyResult {

    @JsonProperty("cr")
    private CallCommandReply callCommandReply;

    /**
     * @return
     */
    @JsonIgnore
    public CallCommandReply getCommandReply() {
        return callCommandReply;
    }

    /**
     * @param callCommandReply
     */
    public void setCommandReply(CallCommandReply callCommandReply) {
        this.callCommandReply = callCommandReply;
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
                "callCommandReply=" + callCommandReply +
                '}';
    }

    /**
     * Created by juan on 30/09/16.
     */
    public static class CallCommandReply {

        @JsonProperty("n")
        private String name;

        @JsonProperty("r")
        private CallResult callResult;

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
        public CallResult getResult() {
            return callResult;
        }

        /**
         * @param commandReplyCallResult
         */
        public void setResult(CallResult commandReplyCallResult) {
            this.callResult = commandReplyCallResult;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof CallCommandReply)) {
                return false;
            }

            CallCommandReply that = (CallCommandReply) o;

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
                    ", callResult=" + callResult +
                    '}';
        }
    }

    /**
     *
     */
    public static class CallResult {

        @JsonProperty("c")
        private RequestCall requestCall;

        /**
         * @return
         */
        @JsonIgnore
        public RequestCall getRequestCall() {
            return requestCall;
        }

        /**
         * @param requestCall
         */
        public void setRequestCall(RequestCall requestCall) {
            this.requestCall = requestCall;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof CallResult)) {
                return false;
            }

            CallResult callResult = (CallResult) o;

            return getRequestCall().equals(callResult.getRequestCall());

        }

        @Override
        public int hashCode() {
            return getRequestCall().hashCode();
        }

        @Override
        public String toString() {
            return "CallResult{" +
                    "requestCall=" + requestCall +
                    '}';
        }
    }
}
