package com.katana.sdk;

import com.katana.api.commands.ActionCommandPayload;
import com.katana.api.commands.Mapping;
import com.katana.api.Action;
import com.katana.api.Transport;
import com.katana.api.replies.common.CommandReplyResult;
import com.katana.api.replies.TransportReplyPayload;
import com.katana.sdk.common.Callable;
import com.katana.sdk.common.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by juan on 27/08/16.
 */
public class Service extends Component<Action, TransportReplyPayload, Service> {

    private Map<String, Callable<Action>> callables;

    /**
     * Initialize the component with the command line arguments
     *
     * @param args list of command line arguments
     * @throws IllegalArgumentException throws an IllegalArgumentException if any of the REQUIRED arguments is missing,
     *                                  if there is an invalid argument or if there are duplicated arguments
     */
    public Service(String[] args) {
        super(args);
        this.callables = new HashMap<>();
    }

    /**
     * @param callable
     */
    public void action(String action, Callable<Action> callable) {
        this.callables.put(action, callable);
    }

    @Override
    protected Class<ActionCommandPayload> getCommandPayloadClass(String componentType) {
        return ActionCommandPayload.class;
    }

    @Override
    protected CommandReplyResult getReply(String componentType, Action response) {
        return response.getTransport();
    }

    @Override
    protected Callable<Action> getCallable(String componentType) {
        return callables.get(componentType);
    }

    @Override
    protected void setBaseCommandAttrs(String componentType, Mapping mapping, Action command) {
        super.setBaseCommandAttrs(componentType, mapping, command);
        command.setActionName(componentType);
        command.setPath(command.getTransport().getMeta().getGateway().get(1));
    }

    @Override
    protected TransportReplyPayload getCommandReplyPayload(String componentType, Action response) {
        TransportReplyPayload commandReplyPayload = new TransportReplyPayload();
        TransportReplyPayload.TransportCommandReply transportCommandReply = new TransportReplyPayload.TransportCommandReply();
        TransportReplyPayload.TransportResult transportResult = new TransportReplyPayload.TransportResult();
        transportResult.setTransport((Transport) getReply(componentType, response));
        transportCommandReply.setName(getName());
        transportCommandReply.setResult(transportResult);
        commandReplyPayload.setCommandReply(transportCommandReply);
        return commandReplyPayload;
    }

    @Override
    public void run() {
        if (this.startupCallable != null) {
            this.startupCallable.run(this);
        }

        super.run();

        if (this.startupCallable != null) {
            this.startupCallable.run(this);
        }
    }
}
