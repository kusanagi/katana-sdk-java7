package com.katana.sdk.components;

import com.katana.api.commands.ActionCommandPayload;
import com.katana.api.common.Action;
import com.katana.api.common.Transport;
import com.katana.api.replies.CommandReplyResult;
import com.katana.api.replies.TransportReplyPayload;
import com.katana.sdk.common.Callable;

/**
 * Created by juan on 27/08/16.
 */
public class Service extends Component<Action, TransportReplyPayload> {

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

    /**
     *
     * @param callable
     */
    public void runAction(Callable<Action> callable) {
        run(callable);
    }

    @Override
    protected Class<ActionCommandPayload> getCommandPayloadClass() {
        return ActionCommandPayload.class;
    }

    @Override
    protected CommandReplyResult getReply(Action response) {
        return response.getTransport();
    }

    @Override
    protected void setBaseCommandAttrs(Action command) {
        super.setBaseCommandAttrs(command);
        command.setActionName(getAction());
    }

    @Override
    protected TransportReplyPayload getCommandReplyPayload(Action response) {
        TransportReplyPayload commandReplyPayload = new TransportReplyPayload();
        TransportReplyPayload.CommandReply commandReply = new TransportReplyPayload.CommandReply();
        TransportReplyPayload.Result result = new TransportReplyPayload.Result();
        result.setTransport((Transport) getReply(response));
        commandReply.setName(getName());
        commandReply.setResult(result);
        commandReplyPayload.setCommandReply(commandReply);
        return commandReplyPayload;
    }
}
