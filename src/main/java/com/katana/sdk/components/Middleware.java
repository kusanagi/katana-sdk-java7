package com.katana.sdk.components;

import com.katana.api.Error;
import com.katana.api.commands.RequestCommandPayload;
import com.katana.api.commands.ResponseCommandPayload;
import com.katana.api.commands.common.CommandPayload;
import com.katana.api.common.*;
import com.katana.api.replies.CallReplyPayload;
import com.katana.api.replies.CommandReplyResult;
import com.katana.api.replies.ResponseReplyPayload;
import com.katana.sdk.common.Callable;
import com.katana.sdk.common.Logger;

/**
 * Created by juan on 27/08/16.
 */
public class Middleware extends Component {

    private Callable<Request> requestCallable;
    private Callable<Response> responseCallable;
    private boolean isRunning;

    public Middleware(String[] args) {
        super(args);
    }

    /**
     * An instance of the Request class MUST be provided as the first argument of the callback function, while the
     * value returned by the callback function MAY be either the instance of the Request class passed to the function,
     * or an instance of the Response class, generated by the Request.new_response() function. The return of any other
     * value, the absence of a return value or the raising of an exception MUST be treated as an error.
     *
     * @param callable function to process the middleware logic
     * @return Return the instance of the middleware
     */
    public Middleware request(Callable<Request> callable) {
        Logger.log("Registering request middleware");
        this.requestCallable = callable;
        return this;
    }

    /**
     * An instance of the Response class MUST be provided as the first argument of the callback function, while the
     * value returned by the callback function MUST be the instance of the Response class passed to the function. The
     * return of any other value, the absence of a return value or the raising of an exception MUST be treated as an
     * error.
     *
     * @param callable function to process the middleware logic
     * @return Return the instance of the middleware
     */
    public Middleware response(Callable<Response> callable) {
        Logger.log("Registering response middleware");
        this.responseCallable = callable;
        return this;
    }

    @Override
    protected Class<? extends CommandPayload> getCommandPayloadClass(String componentType) {
        return componentType.equals("request") ? RequestCommandPayload.class : ResponseCommandPayload.class;
    }

    @Override
    protected CommandReplyResult getCommandReplyPayload(String componentType, Api response) {
        if (componentType.equals("request")) {
            CallReplyPayload commandReplyPayload = new CallReplyPayload();
            CallReplyPayload.CallCommandReply callCommandReply = new CallReplyPayload.CallCommandReply();
            CallReplyPayload.CallResult callResult = new CallReplyPayload.CallResult();
            callResult.setCall((Call) getReply(componentType, response));
            callCommandReply.setName(getName());
            callCommandReply.setResult(callResult);
            commandReplyPayload.setCommandReply(callCommandReply);
            return commandReplyPayload;
        } else {
            ResponseReplyPayload commandReplyPayload = new ResponseReplyPayload();
            ResponseReplyPayload.ResponseCommandReply responseCommandReply = new ResponseReplyPayload.ResponseCommandReply();
            ResponseReplyPayload.ResponseResult responseResult = new ResponseReplyPayload.ResponseResult();
            responseResult.setHttpResponse((HttpResponse) getReply(componentType, response));
            Error error = new Error();
            error.setCode(((Response)response).getHttpResponse().getStatusCode());
            error.setMessage(((Response)response).getHttpResponse().getStatusText());
            error.setStatus(((Response)response).getHttpResponse().getStatus());
//            responseResult.setError(error);
            responseCommandReply.setName(getName());
            responseCommandReply.setResult(responseResult);
            commandReplyPayload.setCommandReply(responseCommandReply);
            return commandReplyPayload;
        }
    }

    @Override
    protected CommandReplyResult getReply(String componentType, Api response) {
        if (componentType.equals("request")) {
            return ((Request) response).getCall();
        } else {
            return ((Response) response).getHttpResponse();
        }
    }

    @Override
    protected Callable getCallable(String componentType) {
        if (componentType.equals("request")) {
            return requestCallable;
        } else {
            return responseCallable;
        }
    }
}
