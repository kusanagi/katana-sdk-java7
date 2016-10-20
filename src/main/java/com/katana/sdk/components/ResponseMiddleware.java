package com.katana.sdk.components;

import com.katana.api.commands.ResponseCommandPayload;
import com.katana.api.common.Response;
import com.katana.api.replies.CommandReplyResult;

/**
 * Created by juan on 14/09/16.
 */
public class ResponseMiddleware extends Component<Response, Response> {

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
        return response;
    }
}
