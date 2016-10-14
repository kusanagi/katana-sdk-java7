package com.katana.sdk.components;

import com.katana.api.common.Action;
import com.katana.api.commands.ActionCommandPayload;
import com.katana.api.common.Transport;
import com.katana.api.replies.CommandReplyResult;
import com.katana.sdk.common.Callable;

/**
 * Created by juan on 27/08/16.
 */
public class Service extends Component<Action, Transport> {

    /**
     * Initialize the component with the command line arguments
     *
     * @param args list of command line arguments
     * @throws IllegalArgumentException throws an IllegalArgumentException if any of the REQUIRED arguments is missing,
     *                                  if there is an invalid argument or if there are duplicated arguments
     */
    public Service(String[] args) {
        super(args);
    }

    @Override
    protected Class<ActionCommandPayload> getCommandPayloadClass() {
        return ActionCommandPayload.class;
    }

    @Override
    protected CommandReplyResult getReply(Action response) {
        return response.getTransport();
    }

    public void runAction(Callable<Action> callable){
        run(callable);
    }

}
