package com.katana.sdk.components;

import com.katana.api.Error;
import com.katana.api.commands.ResponseCommandPayload;
import com.katana.api.common.HttpResponse;
import com.katana.api.common.Response;
import com.katana.api.replies.CommandReplyResult;
import com.katana.api.replies.ResponseReplyPayload;

/**
 * Created by juan on 14/09/16.
 */
public class ResponseMiddleware extends Component<Response, ResponseReplyPayload> {

    /**
     * Initialize the component with the command line arguments
     *
     * @param args list of command line arguments
     * @throws IllegalArgumentException throws an IllegalArgumentException if any of the REQUIRED arguments is missing,
     *                                  if there is an invalid argument or if there are duplicated arguments
     */
    ResponseMiddleware(String[] args) {
        super(args);
    }

    @Override
    protected Class<ResponseCommandPayload> getCommandPayloadClass() {
        return ResponseCommandPayload.class;
    }

    @Override
    protected CommandReplyResult getReply(Response response) {
        return response.getHttpResponse();
    }

    @Override
    protected ResponseReplyPayload getCommandReplyPayload(Response response) {
        ResponseReplyPayload commandReplyPayload = new ResponseReplyPayload();
        ResponseReplyPayload.CommandReply commandReply = new ResponseReplyPayload.CommandReply();
        ResponseReplyPayload.Result result = new ResponseReplyPayload.Result();
        result.setResponse((HttpResponse) getReply(response));
        Error error = new Error();
        error.setCode(response.getHttpResponse().getStatusCode());
        error.setMessage(response.getHttpResponse().getStatusText());
        error.setStatus(response.getHttpResponse().getStatus());
//        result.setError(error);
        commandReply.setName(getName());
        commandReply.setResult(result);
        commandReplyPayload.setCommandReply(commandReply);
        return commandReplyPayload;
    }
}
