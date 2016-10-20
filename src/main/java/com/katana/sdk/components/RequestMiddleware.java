package com.katana.sdk.components;

import com.katana.api.commands.RequestCommandPayload;
import com.katana.api.common.Call;
import com.katana.api.common.Request;
import com.katana.api.replies.CommandReplyResult;

/**
 * Created by juan on 14/09/16.
 */
public class RequestMiddleware extends Component<Request, Call>{

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
}
