package com.katana.sdk;

import com.katana.api.Action;
import com.katana.api.Transport;
import com.katana.api.commands.ActionCommandPayload;
import com.katana.api.commands.Mapping;
import com.katana.api.replies.TransportReplyPayload;
import com.katana.api.replies.common.CommandReplyResult;
import com.katana.common.Constants;
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
    protected byte[] getReplyMetadata(TransportReplyPayload reply) {
        Transport transport = reply.getCommandReply().getResult().getTransport();
        byte callsByte = Constants.VOID_BYTE;
        byte filesByte = Constants.VOID_BYTE;
        byte transactionsByte = Constants.VOID_BYTE;
        byte downloadByte = Constants.VOID_BYTE;
        int byteSize = 0;
        if (transport.getCalls() != null && !transport.getCalls().isEmpty()) {
            callsByte = Constants.CALL_BYTE;
            byteSize++;
        }
        if (transport.getFiles() != null && !transport.getFiles().isEmpty()) {
            filesByte = Constants.FILE_BYTE;
            byteSize++;
        }
        if (transport.getTransactions() != null && !transport.getTransactions().getCommit().isEmpty()) {
            transactionsByte = Constants.TRANSACTION_BYTE;
            byteSize++;
        }
        if (transport.getDownload() != null) {
            downloadByte = Constants.DOWNLOAD_BYTE;
            byteSize++;
        }
        byte[]replyMetaData;
        if (byteSize > 0) {
            replyMetaData = new byte[byteSize];
            int position = 0;
            position = registerByte(callsByte, replyMetaData, position) ? position + 1 : position;
            position = registerByte(filesByte, replyMetaData, position) ? position + 1 : position;
            position = registerByte(transactionsByte, replyMetaData, position) ? position + 1 : position;
            registerByte(downloadByte, replyMetaData, position);
        } else {
            replyMetaData = new byte[]{Constants.VOID_BYTE};
        }
        return replyMetaData;
    }

    private boolean registerByte(byte aByte, byte[] byteArray, int position) {
        if (aByte != Constants.VOID_BYTE){
            byteArray[position] = aByte;
            return true;
        }
        return false;
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
    }

    @Override
    protected void runShutdown() {
        this.shutdownCallable.run(this);
    }
}
