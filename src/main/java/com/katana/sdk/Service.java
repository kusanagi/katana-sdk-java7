package com.katana.sdk;

import com.katana.api.commands.ActionCommandPayload;
import com.katana.api.commands.Mapping;
import com.katana.api.replies.TransportReplyPayload;
import com.katana.api.replies.common.CommandReplyResult;
import com.katana.api.component.Constants;
import com.katana.api.component.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        byte[] replyMetaData;
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
        if (aByte != Constants.VOID_BYTE) {
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

        ServiceSchema serviceSchema = response.getServiceSchema(getName(), getVersion());
        String returnType = serviceSchema.getActionSchema(getName()).getReturnType();
        transportResult.setReturnObject(getReturnObject(componentType, response.getReturnObject(), returnType));

        transportCommandReply.setName(getName());
        transportCommandReply.setResult(transportResult);
        commandReplyPayload.setCommandReply(transportCommandReply);
        return commandReplyPayload;
    }

    private Object getReturnObject(String action, Object returnObject, String returnType) {
        if (returnType.equals("boolean")){
            if (returnObject instanceof Boolean) {
                return returnObject;
            } else if (returnObject == null) {
                return false;
            } else {
                throwInvalidTypeException(action);
            }
        } else if (returnType.equals("integer")){
            if (returnObject instanceof Integer){
                return returnObject;
            } else if (returnObject == null) {
                return 0;
            }  else {
                throwInvalidTypeException(action);
            }
        } else if (returnType.equals("float")){
            if (returnObject instanceof Float){
                return returnObject;
            } else if (returnObject == null) {
                return 0.0f;
            }  else {
                throwInvalidTypeException(action);
            }
        } else if (returnType.equals("string")){
            if (returnObject instanceof String){
                return returnObject;
            } else if (returnObject == null) {
                return "";
            }  else {
                throwInvalidTypeException(action);
            }
        } else if (returnType.equals("array")){
            if (returnObject instanceof List){
                return returnObject;
            } else if (returnObject == null) {
                return new ArrayList<>();
            }  else {
                throwInvalidTypeException(action);
            }
        } else if (returnType.equals("object")){
            if (returnObject instanceof Map){
                return returnObject;
            } else if (returnObject == null) {
                return new HashMap();
            }  else {
                throwInvalidTypeException(action);
            }
        }
        return null;
    }

    private Object throwInvalidTypeException(String action) {
        throw new IllegalArgumentException("Invalid return type given in " + getName() + " " + getVersion() + " for action: " + action );
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
