package com.katana.sdk.components;

import com.katana.api.commands.ResponseCommandPayload;
import com.katana.api.common.HttpResponse;
import com.katana.api.common.Response;
import com.katana.api.replies.CommandReplyResult;
import com.katana.api.replies.ResponseReplyPayload;
import com.katana.sdk.common.Callable;

/**
 * Created by juan on 14/09/16.
 */
public class ResponseMiddleware extends Component<Response, ResponseReplyPayload> {

    private final Callable<Response> callable;

    /**
     * Initialize the component with the command line arguments
     *
     * @param args list of command line arguments
     * @throws IllegalArgumentException throws an IllegalArgumentException if any of the REQUIRED arguments is missing,
     *                                  if there is an invalid argument or if there are duplicated arguments
     */
    ResponseMiddleware(String[] args, Callable<Response> callable) {
        super(args);
        this.callable = callable;
    }

    @Override
    protected Class<ResponseCommandPayload> getCommandPayloadClass(String componentType) {
        return ResponseCommandPayload.class;
    }

    @Override
    protected CommandReplyResult getReply(String componentType, Response response) {
        return response.getHttpResponse();
    }

    @Override
    protected Callable<Response> getCallable(String componentType) {
        return callable;
    }

    @Override
    protected ResponseReplyPayload getCommandReplyPayload(String componentType, Response response) {
        ResponseReplyPayload commandReplyPayload = new ResponseReplyPayload();
        ResponseReplyPayload.ResponseCommandReply responseCommandReply = new ResponseReplyPayload.ResponseCommandReply();
        ResponseReplyPayload.ResponseResult responseResult = new ResponseReplyPayload.ResponseResult();
        responseResult.setHttpResponse((HttpResponse) getReply(componentType, response));
        responseCommandReply.setName(getName());
        responseCommandReply.setResult(responseResult);
        commandReplyPayload.setCommandReply(responseCommandReply);
        return commandReplyPayload;
    }
}
