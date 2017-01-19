package com.katana.sdk.components;

import com.katana.api.commands.Mapping;
import com.katana.api.commands.RequestCommandPayload;
import com.katana.api.common.Request;
import com.katana.api.common.RequestCall;
import com.katana.api.replies.CallReplyPayload;
import com.katana.api.replies.CommandReplyResult;
import com.katana.sdk.common.Callable;

/**
 * Created by juan on 14/09/16.
 */
public class RequestMiddleware extends Component<Request, CallReplyPayload> {

    private final Callable<Request> callable;

    /**
     * Initialize the component with the command line arguments
     *
     * @param args list of command line arguments
     * @throws IllegalArgumentException throws an IllegalArgumentException if any of the REQUIRED arguments is missing,
     *                                  if there is an invalid argument or if there are duplicated arguments
     */
    RequestMiddleware(String[] args, Callable<Request> callable) {
        super(args);
        this.callable = callable;
    }

    @Override
    protected Class<RequestCommandPayload> getCommandPayloadClass(String componentType) {
        return RequestCommandPayload.class;
    }


    @Override
    protected CommandReplyResult getReply(String componentType, Request response) {
        return response.getRequestCall();
    }

    @Override
    protected Callable<Request> getCallable(String componentType) {
        return callable;
    }

    @Override
    protected void setBaseCommandAttrs(String componentType, Mapping mapping, Request command) {
        super.setBaseCommandAttrs(componentType, mapping, command);
        command.setPath(command.getHttpRequest().getUrlPath());
    }

    @Override
    protected CallReplyPayload getCommandReplyPayload(String componentType, Request response) {
        CallReplyPayload commandReplyPayload = new CallReplyPayload();
        CallReplyPayload.CallCommandReply callCommandReply = new CallReplyPayload.CallCommandReply();
        CallReplyPayload.CallResult callResult = new CallReplyPayload.CallResult();
        callResult.setRequestCall((RequestCall) getReply(componentType, response));
        callCommandReply.setName(getName());
        callCommandReply.setResult(callResult);
        commandReplyPayload.setCommandReply(callCommandReply);
        return commandReplyPayload;
    }


}
