package com.katana.sdk.components;

import com.katana.api.commands.RequestCommandPayload;
import com.katana.api.common.Call;
import com.katana.api.common.Request;
import com.katana.api.replies.CallReplyPayload;
import com.katana.api.replies.CommandReplyResult;

/**
 * Created by juan on 14/09/16.
 */
public class RequestMiddleware extends Component<Request, CallReplyPayload> {

    /**
     * Initialize the component with the command line arguments
     *
     * @param args list of command line arguments
     * @throws IllegalArgumentException throws an IllegalArgumentException if any of the REQUIRED arguments is missing,
     *                                  if there is an invalid argument or if there are duplicated arguments
     */
    RequestMiddleware(String[] args) {
        super(args);
    }

    @Override
    protected Class<RequestCommandPayload> getCommandPayloadClass() {
        return RequestCommandPayload.class;
    }


    @Override
    protected CommandReplyResult getReply(Request response) {
        return response.getCall();
    }

    @Override
    protected void setBaseCommandAttrs(Request command) {
        super.setBaseCommandAttrs(command);
        command.setPath(command.getHttpRequest().getUrlPath());
    }

    @Override
    protected CallReplyPayload getCommandReplyPayload(Request response) {
        CallReplyPayload commandReplyPayload = new CallReplyPayload();
        CallReplyPayload.CommandReply commandReply = new CallReplyPayload.CommandReply();
        CallReplyPayload.Result result = new CallReplyPayload.Result();
        result.setCall((Call) getReply(response));
        commandReply.setName(getName());
        commandReply.setResult(result);
        commandReplyPayload.setCommandReply(commandReply);
        return commandReplyPayload;
    }


}
