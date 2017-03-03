package com.katana.sdk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.katana.api.Api;
import com.katana.api.commands.CallCommandPayload;
import com.katana.api.component.Serializer;
import com.katana.api.component.utils.MessagePackSerializer;
import com.katana.api.replies.ErrorPayload;
import com.katana.api.replies.ReturnReplyPayload;
import org.zeromq.ZMQ;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by juan on 27/08/16.
 */
public class Action extends Api {
    @JsonProperty("p")
    private List<Param> params;

    @JsonProperty("T")
    private Transport transport;

    @JsonProperty("R")
    private Object returnObject;

    private String actionName;

    /**
     * Default constructor
     */
    public Action() {
        // Default constructor to make possible the serialization of this object.
    }

    public Action(Action other) {
        super(other);
        this.params = other.params;
        this.transport = other.transport;
        this.actionName = other.actionName;
    }

    /**
     * Action name setter
     *
     * @param actionName Name of the Action
     */
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    /**
     * Params setter
     *
     * @param params Params object
     */
    public void setParams(List<Param> params) {
        this.params = params;
    }

    /**
     * Transport getter
     *
     * @return Return the transport object
     */
    public Transport getTransport() {
        return transport;
    }

    /**
     * Transport setter
     *
     * @param transport Transport object
     */
    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public Object getReturnObject() {
//        ActionSchema actionSchema = getServiceSchema(getName(), getVersion()).getActionSchema(getActionName());
//
//        if (returnType.equals("boolean") && !(returnObject instanceof Boolean)) {
//            throwInvalidTypeException();
//        } else if (returnType.equals("integer") && !(returnObject instanceof Integer)){
//            throwInvalidTypeException();
//        } else if (returnType.equals("float") && !(returnObject instanceof Float)){
//            throwInvalidTypeException();
//        } else if (returnType.equals("string") && !(returnObject instanceof String)){
//            throwInvalidTypeException();
//        } else if (returnType.equals("array") && !(returnObject instanceof List)){
//            throwInvalidTypeException();
//        } else if (returnType.equals("object") && !(returnObject instanceof Map)){
//            throwInvalidTypeException();
//        }

        return returnObject;
    }

    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    // SDK Methods

    /**
     * Determine whether or not the current Service is the initial Service called in a request.
     *
     * @return Return true if the Service is the initial Service called in a request
     */
    @JsonIgnore
    public boolean isOrigin() {
        return this.transport.getOriginService().length != 0 && this.transport.getOriginService()[0].equals(getName());
    }

    /**
     * @return Return the unique name of the action called.
     */
    @JsonIgnore
    public String getActionName() {
        return this.actionName;
    }

    /**
     * Register a custom userland property in the Transport with the REQUIRED name and REQUIRED value arguments.
     * If a property with the specified name already exists the value MUST be replaced with value. The function MUST
     * NOT accept any other data type for a value other than string.
     *
     * @param name  Name of the property
     * @param value Value of the property
     * @return Return true if the operation was successful
     */
    public Action setProperty(String name, String value) {
        TransportMeta meta = this.transport.getMeta();
        Map<String, String> properties = meta.getProperties();
        properties.put(name, value);
        return this;
    }

    /**
     * Determine if a parameter with the name specified by the REQUIRED case sensitive name argument
     * was provided for the action.
     *
     * @param name Name of the Param
     * @return Return true if the Action has the param
     */
    public boolean hasParam(String name) {
        for (Param param : params) {
            if (param.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the parameter with the REQUIRED case sensitive name argument which MUST be returned as a Param object.
     * To deserialize a file provided with the request the getFile() function MUST be used to access the File object.
     *
     * @param name Name of the param
     * @return Return the value of the param
     */
    @JsonIgnore
    public Param getParam(String name) {
        for (Param param : params) {
            if (param.getName().equals(name)) {
                return param;
            }
        }

        Param param = new Param();
        param.setName(name);
        param.setExists(false);
        return param;
    }

    /**
     * Param getter
     *
     * @return Return all the params of the action
     */
    public List<Param> getParams() {
        return params;
    }

    /**
     * Create a new parameter with the REQUIRED name argument
     * If the OPTIONAL value or type arguments are specified these MUST also be applied to the Param object. The value
     * of the type argument MUST be either "null", "boolean", "integer", "float", "string", "array" or "object", where
     * any other value MUST be accepted as "string".
     * When creating a new Param object the value of the exists property MUST be false.
     *
     * @param name  Name of the new param
     * @param value Value of the new param
     * @param type  Data type of the new param, MUST be either "null", "boolean", "integer", "float", "string", "array" or "object"
     * @return Return the new param
     */
    public Param newParam(String name, String value, String type) {
        Param param = new Param();
        param.setName(name);
        param.setValue(value);
        param.setType(type);
        return param;
    }

    /**
     * Determine if a file with the parameter name specified by the REQUIRED case sensitive
     * name argument was provided for the action.
     *
     * @param name Name of the file
     * @return Return true if the action has the file
     */
    public boolean hasFile(String name) {
        Map<String, Map<String, Map<String, Map<String, Map<String, File>>>>> pathFiles = this.transport.getFiles();
        for (Map.Entry path : pathFiles.entrySet()) {
            Map<String, Map<String, Map<String, Map<String, File>>>> serviceFiles = pathFiles.get((String) path.getKey());
            for (Map.Entry service : serviceFiles.entrySet()) {
                Map<String, Map<String, Map<String, File>>> versionFiles = serviceFiles.get((String) service.getKey());
                for (Map.Entry version : versionFiles.entrySet()) {
                    Map<String, Map<String, File>> actionFiles = versionFiles.get((String) version.getKey());
                    for (Map.Entry action : actionFiles.entrySet()) {
                        Map<String, File> nameFiles = actionFiles.get((String) action.getKey());
                        if (nameFiles.containsKey(name)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Get the file with the REQUIRED case sensitive name argument, which MUST be returned as a File object. If the
     * file is not found, a File object with the REQUIRED name as first argument and an empty path as second argument
     * MUST be returned.
     *
     * @param name Name of the file
     * @return Return the File
     */
    @JsonIgnore
    public File getFile(String name) {
        Map<String, Map<String, Map<String, Map<String, Map<String, File>>>>> pathFiles = this.transport.getFiles();
        for (Map.Entry path : pathFiles.entrySet()) {
            Map<String, Map<String, Map<String, Map<String, File>>>> serviceFiles = pathFiles.get((String) path.getKey());
            for (Map.Entry service : serviceFiles.entrySet()) {
                Map<String, Map<String, Map<String, File>>> versionFiles = serviceFiles.get((String) service.getKey());
                for (Map.Entry version : versionFiles.entrySet()) {
                    Map<String, Map<String, File>> actionFiles = versionFiles.get((String) version.getKey());
                    for (Map.Entry action : actionFiles.entrySet()) {
                        Map<String, File> nameFiles = actionFiles.get((String) action.getKey());
                        if (nameFiles.containsKey(name)) {
                            return nameFiles.get(name);
                        }
                    }
                }
            }
        }
        File file = new File();
        file.setName(name);
        file.setPath("");
        return file;
    }

    /**
     * Get all the files, which MUST be returned as an array of File objects. If no files are found an empty array
     * MUST be returned.
     *
     * @return Return all the files
     */
    @JsonIgnore
    public List<File> getFiles() {
        List<File> files = new ArrayList<>();
        Map<String, Map<String, Map<String, Map<String, Map<String, File>>>>> pathFiles = this.transport.getFiles();
        for (Map.Entry path : pathFiles.entrySet()) {
            Map<String, Map<String, Map<String, Map<String, File>>>> serviceFiles = pathFiles.get((String) path.getKey());
            for (Map.Entry service : serviceFiles.entrySet()) {
                Map<String, Map<String, Map<String, File>>> versionFiles = serviceFiles.get((String) service.getKey());
                for (Map.Entry version : versionFiles.entrySet()) {
                    Map<String, Map<String, File>> actionFiles = versionFiles.get((String) version.getKey());
                    for (Map.Entry action : actionFiles.entrySet()) {
                        Map<String, File> nameFiles = actionFiles.get((String) action.getKey());
                        for (Map.Entry name : nameFiles.entrySet()) {
                            files.add(nameFiles.get((String) name.getKey()));
                        }
                    }
                }
            }
        }
        return files;
    }

    /**
     * Create a new file object from the REQUIRED name argument as name of the parameter, the REQUIRED path argument
     * as the file system path, which MUST be returned as a File object. If the OPTIONAL mime argument is specified it
     * MUST also be applied to the File object.
     *
     * @param name Name of the File
     * @param path Path of the file
     * @param mime Mime type of the file
     * @return Return the new file
     */
    public File newFile(String name, String path, String mime) {
        File file = new File();
        file.setName(name);
        file.setPath(path);
        file.setMime(mime);
        return file;
    }

    /**
     * Register a file to be downloaded by the Gateway to the client with the REQUIRED file argument, which MUST be an
     * instance of a File object. A File object returned by the getFile() function MAY be used, to register a file
     * provided to this Service.
     * If the configuration of the Service did not include a file server this function MUST return false.
     * Only one file MAY be defined, and which MUST be overwritten if another is defined. Providing a file for download
     * MUST take preference over content for the HTTP response body, and replace it if already defined.
     *
     * @param file File object to register
     * @return Return false if the Service did not include a file server
     */
    public Action setDownload(File file) {
        if (!getServiceSchema(getName(), getVersion()).hasFileServer()){
            throw new IllegalArgumentException("File server not configured: \"" + getName() + "\" (" + getVersion() + ")");
        }
        this.transport.setBody(file);
        return this;
    }

    /**
     * Register an entity object with the REQUIRED entity argument under the current Service namespace, version and
     * action name.
     * If data already exists it MUST be replaced with the given entity.
     *
     * @param entity Entity to be registered
     * @return Return true if the operation was successful
     */
    public Action setEntity(Object entity) {
        Map<String, Map<String, Map<String, Map<String, Object>>>> pathData = this.transport.getData();

        if (pathData == null) {
            pathData = new HashMap<>();
            this.transport.setData(pathData);
        }

        Map<String, Map<String, Map<String, Object>>> serviceData = new HashMap<>();
        Map<String, Map<String, Object>> versionData = new HashMap<>();
        Map<String, Object> actionData = new HashMap<>();
        if (pathData.containsKey(getPath())) {
            serviceData = pathData.get(getPath());
            if (serviceData.containsKey(getName())) {
                versionData = serviceData.get(getName());
                if (versionData.containsKey(getVersion())) {
                    actionData = versionData.get(getVersion());
                } else {
                    versionData.put(getVersion(), actionData);
                }
            } else {
                versionData.put(getVersion(), actionData);
                serviceData.put(getName(), versionData);
            }
        } else {
            versionData.put(getVersion(), actionData);
            serviceData.put(getName(), versionData);
            pathData.put(getPath(), serviceData);
        }
        if (!(entity instanceof List)) {
            List<Object> entities = new ArrayList<>();
            entities.add(entity);
            actionData.put(getActionName(), entities);
        } else {
            actionData.put(getActionName(), entity);
        }
        return this;
    }

    /**
     * Register an array of entity objects with the REQUIRED collection argument under the current Service namespace,
     * version and action name.
     * If data already exists it MUST be replaced with the given collection.
     *
     * @param collection Collection array to be registered
     * @return Return true if the operation was successful
     */
    public Action setCollection(List<?> collection) {
        setEntity(collection);
        return this;
    }

    /**
     * Register a "one-to-one" relation between the entity with the REQUIRED primary_key argument and the foreign
     * Service, defined by the REQUIRED case sensitive service argument, and the foreign key on the entity from that
     * Service, defined by the REQUIRED foreign_key argument.
     * If the relation already exists it MUST be replaced with the given foreign_key.
     *
     * @param primaryKey Primary key argument
     * @param service    Foreign Service
     * @param foreignKey Foreign key argument
     * @return Return true if the operation was successful
     */
    public Action relateOne(String primaryKey, String service, String foreignKey) {
        Map<String, Map<String, Map<String, Map<String, Map<String, Object>>>>> relations = this.transport.getRelations();

        Map<String, Object> relation = new HashMap<>();
        relation.put(service, foreignKey);

        Map<String, Map<String, Object>> primaryKeyRelation = new HashMap<>();
        primaryKeyRelation.put(path, relation);

        Map<String, Map<String, Map<String, Object>>> nameRelation = new HashMap<>();
        nameRelation.put(name, primaryKeyRelation);

        if (relations.containsKey(path)) {
            if (relations.get(path).containsKey(name)) {
                if (relations.get(path).get(name).containsKey(primaryKey)) {
                    if (relations.get(path).get(name).get(primaryKey).containsKey(path)) {
                        relations.get(path).get(name).get(primaryKey).get(path).put(service, foreignKey);
                    } else {
                        relations.get(path).get(name).get(primaryKey).put(path, relation);
                    }
                } else {
                    relations.get(path).get(name).put(primaryKey, primaryKeyRelation);
                }
            } else {
                relations.get(path).put(name, nameRelation);
            }
        } else {
            Map<String, Map<String, Map<String, Map<String, Object>>>> pathRelation = new HashMap<>();
            pathRelation.put(path, nameRelation);
            relations.put(path, pathRelation);
        }

        return this;
    }

    /**
     * Register a "one-to-many" relation between the entity with the REQUIRED primary_key argument and the foreign
     * Service, defined by the REQUIRED case sensitive service argument, and the foreign keys of the entities from
     * that Service, defined by the REQUIRED foreign_keys argument.
     * If the relation already exists it MUST be replaced with the given foreign_keys.
     *
     * @param primaryKey Primary key argument
     * @param service    Foreign Service
     * @param foreignKey Foreign key argument
     * @return Return true if the operation was successful
     */
    public Action relateMany(String primaryKey, String service, List<String> foreignKey) {
        Map<String, Map<String, Map<String, Map<String, Map<String, Object>>>>> relations = this.transport.getRelations();

        Map<String, Object> relation = new HashMap<>();
        relation.put(service, foreignKey);

        Map<String, Map<String, Object>> primaryKeyRelation = new HashMap<>();
        primaryKeyRelation.put(path, relation);

        Map<String, Map<String, Map<String, Object>>> nameRelation = new HashMap<>();
        nameRelation.put(name, primaryKeyRelation);

        if (relations.containsKey(path)) {
            if (relations.get(path).containsKey(name)) {
                if (relations.get(path).get(name).containsKey(primaryKey)) {
                    if (relations.get(path).get(name).get(primaryKey).containsKey(path)) {
                        relations.get(path).get(name).get(primaryKey).get(path).put(service, foreignKey);
                    } else {
                        relations.get(path).get(name).get(primaryKey).put(path, relation);
                    }
                } else {
                    relations.get(path).get(name).put(primaryKey, primaryKeyRelation);
                }
            } else {
                relations.get(path).put(name, nameRelation);
            }
        } else {
            Map<String, Map<String, Map<String, Map<String, Object>>>> pathRelation = new HashMap<>();
            pathRelation.put(path, nameRelation);
            relations.put(path, pathRelation);
        }

        return this;
    }

    public Action relateOneRemote(String primaryKey, String address, String service, String foreignKey) {
        Map<String, Map<String, Map<String, Map<String, Map<String, Object>>>>> relations = this.transport.getRelations();

        Map<String, Object> relation = new HashMap<>();
        relation.put(service, foreignKey);

        Map<String, Map<String, Object>> primaryKeyRelation = new HashMap<>();
        primaryKeyRelation.put(path, relation);

        Map<String, Map<String, Map<String, Object>>> nameRelation = new HashMap<>();
        nameRelation.put(name, primaryKeyRelation);

        if (relations.containsKey(path)) {
            if (relations.get(path).containsKey(name)) {
                if (relations.get(path).get(name).containsKey(primaryKey)) {
                    if (relations.get(path).get(name).get(primaryKey).containsKey(address)) {
                        relations.get(path).get(name).get(primaryKey).get(address).put(service, foreignKey);
                    } else {
                        relations.get(path).get(name).get(primaryKey).put(address, relation);
                    }
                } else {
                    relations.get(path).get(name).put(primaryKey, primaryKeyRelation);
                }
            } else {
                relations.get(path).put(name, nameRelation);
            }
        } else {
            Map<String, Map<String, Map<String, Map<String, Object>>>> pathRelation = new HashMap<>();
            pathRelation.put(path, nameRelation);
            relations.put(path, pathRelation);
        }
        return this;
    }

    public Action relateManyRemote(String primaryKey, String address, String service, List<String> foreignKey) {
        Map<String, Map<String, Map<String, Map<String, Map<String, Object>>>>> relations = this.transport.getRelations();

        Map<String, Object> relation = new HashMap<>();
        relation.put(service, foreignKey);

        Map<String, Map<String, Object>> primaryKeyRelation = new HashMap<>();
        primaryKeyRelation.put(path, relation);

        Map<String, Map<String, Map<String, Object>>> nameRelation = new HashMap<>();
        nameRelation.put(name, primaryKeyRelation);

        if (relations.containsKey(path)) {
            if (relations.get(path).containsKey(name)) {
                if (relations.get(path).get(name).containsKey(primaryKey)) {
                    if (relations.get(path).get(name).get(primaryKey).containsKey(address)) {
                        relations.get(path).get(name).get(primaryKey).get(address).put(service, foreignKey);
                    } else {
                        relations.get(path).get(name).get(primaryKey).put(address, relation);
                    }
                } else {
                    relations.get(path).get(name).put(primaryKey, primaryKeyRelation);
                }
            } else {
                relations.get(path).put(name, nameRelation);
            }
        } else {
            Map<String, Map<String, Map<String, Map<String, Object>>>> pathRelation = new HashMap<>();
            pathRelation.put(path, nameRelation);
            relations.put(path, pathRelation);
        }
        return this;
    }

    /**
     * Register a link with the REQUIRED link argument as the name, and REQUIRED uri argument as the value, under the
     * current Service namespace.
     * If the link name specified with link is already defined it MUST be replaced with the given uri.
     *
     * @param link Link name
     * @param uri  Uri of the link
     * @return Return true if the operation was successful
     */
    public Action setLink(String link, String uri) {
        Map<String, Map<String, Map<String, String>>> pathLink = this.transport.getLinks();
        Map<String, Map<String, String>> serviceLink = new HashMap<>();
        Map<String, String> linkMap = new HashMap<>();
        if (pathLink.containsKey(getPath())) {
            serviceLink = pathLink.get(getPath());
            if (serviceLink.containsKey(getName())) {
                linkMap = serviceLink.get(getName());
            } else {
                serviceLink.put(getName(), linkMap);
            }
        } else {
            serviceLink.put(getName(), linkMap);
            pathLink.put(getPath(), serviceLink);
        }
        linkMap.put(link, uri);
        return this;
    }

    /**
     * Register a "commit" transaction call with the REQUIRED case sensitive action argument as the name of the action
     * to call.
     * The OPTIONAL params argument MUST allow parameters to be specified, where each item in the array MUST be a valid
     * Param object.
     * If a commit transaction call has already been defined for the given action it MUST be aggregated to the array of
     * actions to call on commit. The order of this registry is relevant.
     *
     * @param action Action argument name
     * @param params Params argument
     * @return Return true if the operation was successful
     */
    public Action commit(String action, List<Param> params) {
        List<ServiceTransaction> commit = this.transport.getTransactions().getCommit();
        if (commit == null) {
            commit = new ArrayList<>();
        }
        ServiceTransaction serviceTransaction = new ServiceTransaction();
        serviceTransaction.setName(name);
        serviceTransaction.setVersion(version);
        serviceTransaction.setAction(action);
        serviceTransaction.setParams(params);
        commit.add(serviceTransaction);
        return this;
    }

    /**
     * Register a "rollback" transaction call with the REQUIRED case sensitive action argument as the name of the
     * action to call.
     * The OPTIONAL params argument MUST allow parameters to be specified, where each item in the array MUST be a valid
     * Param object.
     * If a rollback transaction call has already been defined for the given action it MUST be aggregated to the array
     * of actions to call on rollback. The order of this registry is relevant.
     *
     * @param action Action argument name
     * @param params Params argument
     * @return Return true if the operation was successful
     */
    public Action rollback(String action, List<Param> params) {
        List<ServiceTransaction> rollback = this.transport.getTransactions().getRollback();
        if (rollback == null) {
            rollback = new ArrayList<>();
        }
        ServiceTransaction serviceTransaction = new ServiceTransaction();
        serviceTransaction.setName(name);
        serviceTransaction.setVersion(version);
        serviceTransaction.setAction(action);
        serviceTransaction.setParams(params);
        rollback.add(serviceTransaction);
        return this;
    }

    /**
     * Register a "complete" transaction call with the REQUIRED case sensitive action argument as the name of the
     * action to call.
     * The OPTIONAL params argument MUST allow parameters to be specified, where each item in the array MUST be a valid
     * Param object.
     * If a complete transaction call has already been defined for the given action it MUST be aggregated to the array
     * of actions to call on complete. The order of this registry is relevant.
     *
     * @param action Action argument name
     * @param params Params argument
     * @return Return true if the operation was successful
     */
    public Action complete(String action, List<Param> params) {
        List<ServiceTransaction> complete = this.transport.getTransactions().getComplete();
        if (complete == null) {
            complete = new ArrayList<>();
        }
        ServiceTransaction serviceTransaction = new ServiceTransaction();
        serviceTransaction.setName(name);
        serviceTransaction.setVersion(version);
        serviceTransaction.setAction(action);
        serviceTransaction.setParams(params);
        complete.add(serviceTransaction);
        return this;
    }

    public Object call(String service, String version, String action, List<Param> params, List<File> files) {
        ServiceSchema serviceSchema = getServiceSchema(this.name, this.version);

        // Validate is there are local files
        if (files != null) {
            for (File file : files) {
                if (file.isLocal()) {
                    throw new IllegalArgumentException("File " + file.getName() + " is local");
                }
            }
        }

        // Build the payload
        Callee callee = new Callee();
        callee.setAction(getActionName());
        callee.setCalleeInfo(new String[]{service, version, action});
        callee.setTransport(getTransport());
        callee.setParam(params);
        callee.setFiles(files);

        CallCommandPayload.CallCommand callCommand = new CallCommandPayload.CallCommand();
        callCommand.setArgument(callee);

        CallCommandPayload payload = new CallCommandPayload();
        payload.setCommand(callCommand);

        // Send Payload
        Serializer serializer = new MessagePackSerializer();
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket requester = context.socket(ZMQ.REQ);
        requester.connect("tcp://" + serviceSchema.getAddress());
        requester.send(new byte[]{0x01}, zmq.ZMQ.ZMQ_SNDMORE);
        try {
            requester.send(serializer.serializeInBytes(payload), 0);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        // Receive Reply
        ZMQ.Socket receiver = context.socket(ZMQ.REP);
        byte[] response = receiver.recv();

        // Close sockets and terminate context
        receiver.close();
        requester.close();
        context.term();

        // Parse Reply
        ReturnReplyPayload.ReturnCommandReply returnCommandReply;
        ErrorPayload errorPayload;
        try {
            // Extract return and return it
            returnCommandReply = serializer.deserialize(response, ReturnReplyPayload.ReturnCommandReply.class);
            return returnCommandReply.getResult().getReturnObject();
        } catch (IOException e) {
            try {
                // Throw Error Payload as exception
                errorPayload = serializer.deserialize(response, ErrorPayload.class);
                throw new IllegalArgumentException(errorPayload.getError().getMessage());
            } catch (IOException e1) {
                // Throw serialization exception
                throw new IllegalArgumentException(e.getMessage());
            }
        }
    }

    /**
     * Register a Service call with the REQUIRED service argument as the name of the Service to call, the REQUIRED
     * version argument as the version of the given Service, and the REQUIRED action argument as the name of the action
     * to call.
     * <p>
     * The version argument MAY use the * character as a wildcard to filter and select from the registered Service
     * components, following the precedence order defined in item 11 of the semantic versioning 2.0.0 [17]
     * specification, for example:
     * <p>
     * A.*.C
     * <p>
     * The wildcard character here SHOULD match any sequence of characters which are not * or ".". There MUST be at
     * least 1 character within that range to match, so "A..C" MUST NOT not be a valid match.
     * <p>
     * Any version which matches this pattern MUST be considered a valid candidate. If only 1 component matches, then
     * that component MUST be selected. If more than 1 component matches, then the previously defined order of
     * precedence MUST be applied, with the first component in the resulting sort being selected. If no matches are
     * found the Service MUST NOT resolve, and an error MUST be registered with the following message:
     * <p>
     * Service not found for version: "%VERSION%"
     * <p>
     * Where %VERSION% is the value provided in the version argument.
     * <p>
     * If a wildcard character is provided at the beginning of the value to match, it MUST consider the beginning of
     * the value of the version until the first character other than *, for example:
     * <p>
     * .B.C
     * <p>
     * This would match "A.B.C" and "123.B.C", and would resolve "123.B.C" as the selected version. If a wildcard
     * character is provided at the end of the value to match, it MUST consider the point at which it's at until the
     * end of any value being checked, for example:
     * <p>
     * A.B.*
     * <p>
     * This would match "A.B.C" and "A.B.321", and would resolve "A.B.321" as the selected version. If 2 or more
     * wildcard characters are provided in a sequence, every * character after the first MUST be ignored, for example:
     * <p>
     * A.***.C
     * <p>
     * This would be interpreted as "A.*.C". If the value to match is only the wildcard character, every registered
     * version SHOULD be checked.
     * <p>
     * The OPTIONAL params argument MUST allow parameters to be specified, where each item in the array MUST be a valid
     * Param object.
     * <p>
     * The OPTIONAL files argument MUST allow files to be specified, where each item in the array MUST be a valid File
     * object. A File object returned by the get_file() function MAY be used, to propagate a file provided to this
     * Service.
     * <p>
     * If the configuration of the Service did not include a file server this function MUST return false.
     * <p>
     * If a call has already been defined for the given service, version and action it MUST be aggregated to the array
     * of actions to call. The order of this registry is relevant.
     *
     * @param service Service name
     * @param version Version of the service
     * @param action  Action name
     * @param params  Params argument
     * @param files   array of files
     * @return Return true if the operation was successful
     */
    public Action deferCall(String service, String version, String action, List<Param> params, List<File> files) {
        ServiceSchema serviceSchema = getServiceSchema(this.name, this.version);

        if (files != null) {
            for (File file : files) {
                if (file.isLocal() && !serviceSchema.hasFileServer()) {
                    throw new IllegalArgumentException(String.format(
                            "File server not configured: \"%s\" (%s)",
                            this.name,
                            this.version));
                }
                this.transport.addFile(this.path, service, version, action, file);
            }
        }

        Map<String, Map<String, List<Call>>> calls = transport.getCalls();

        if (calls == null) {
            calls = new HashMap<>();
            transport.setCalls(calls);
        }

        List<Call> callList = new ArrayList<>();

        Map<String, List<Call>> versionCalls = new HashMap<>();
        versionCalls.put(this.version, callList);

        if (calls.containsKey(this.name)) {
            if (calls.get(this.name).containsKey(version)) {
                callList = calls.get(this.name).get(this.version);
            } else {
                calls.get(this.name).put(this.version, callList);
            }
        } else {
            calls.put(this.name, versionCalls);
        }

        Call call = new Call();
        call.setName(service);
        call.setVersion(version);
        call.setAction(action);
        call.setParams(params);
        callList.add(call);
        return this;
    }

    public Action callRemote(String address, String service, String version, String action, List<Param> params, List<File> files, int timeout) {
        ServiceSchema serviceSchema = getServiceSchema(this.name, this.version);

        if (files != null) {
            for (File file : files) {
                if (file.isLocal() && !serviceSchema.hasFileServer()) {
                    throw new IllegalArgumentException(String.format(
                            "File server not configured: \"%s\" (%s)",
                            this.name,
                            this.version));
                }
                this.transport.addFile(address, service, version, action, file);
            }
        }

        Map<String, Map<String, List<Call>>> calls = transport.getCalls();
        List<Call> callList = new ArrayList<>();

        Map<String, List<Call>> versionCalls = new HashMap<>();
        versionCalls.put(this.version, callList);

        if (calls.containsKey(this.name)) {
            if (calls.get(this.name).containsKey(version)) {
                callList = calls.get(this.name).get(this.version);
            } else {
                calls.get(this.name).put(this.version, callList);
            }
        } else {
            calls.put(this.name, versionCalls);
        }

        Call call = new Call();
        call.setGateway(address);
        call.setName(service);
        call.setVersion(version);
        call.setAction(action);
        call.setTimeout(timeout);
        call.setParams(params);
        callList.add(call);
        return this;
    }

    /**
     * Register a userland error with the REQUIRED message argument as the natural language description of the error.
     * The OPTIONAL code MUST allow a code to be defined for the error, which MUST default to 0 if not defined.
     * The OPTIONAL case insensitive status argument MUST allow a suggested HTTP status code to be defined for the
     * error. The usage of this status code in the HTTP response depends upon the Service being the origin of the
     * request and this being the first registered error, or there being no errors for the origin Service and this
     * being the first error of the first Service which registered errors, or the Middleware choosing this error and
     * response status code.
     * If an error has already been registered for the given action it MUST be aggregated to the array of errors for
     * that action. The order of this registry is relevant.
     *
     * @param message Error message
     * @param code    Error code
     * @param status  Error status
     * @return Return true if the operation was successful
     */
    public Action error(String message, int code, String status) {
        Error error = new Error();
        error.setMessage(message);
        error.setCode(code);
        error.setStatus(status);

        Map<String, Map<String, Map<String, List<Error>>>> pathError = this.transport.getErrors();
        Map<String, Map<String, List<Error>>> serviceError = new HashMap<>();
        Map<String, List<Error>> versionError = new HashMap<>();
        List<Error> errors = new ArrayList<>();

        if (pathError.containsKey(getPath())) {
            serviceError = pathError.get(getPath());
            if (serviceError.containsKey(getName())) {
                versionError = serviceError.get(getName());
                if (versionError.containsKey(getVersion())) {
                    errors = versionError.get(getVersion());
                } else {
                    versionError.put(getVersion(), errors);
                }
            } else {
                versionError.put(getVersion(), errors);
                serviceError.put(getName(), versionError);
            }
        } else {
            versionError.put(getVersion(), errors);
            serviceError.put(getName(), versionError);
            pathError.put(getPath(), serviceError);
        }

        errors.add(error);

        return this;
    }

    public void setReturn(Object returnObject) {
        ServiceSchema serviceSchema = getServiceSchema(getName(), getVersion());
        ActionSchema actionSchema = serviceSchema.getActionSchema(getActionName());

        if (actionSchema.hasReturn()){
            throw new IllegalArgumentException("Cannot set a return value in \"" + getName() + "\" (" + getVersion() + ") for action: \"" + getActionName() + "\"");
        }

        validateReturnObjectType(actionSchema.getReturnType());

        this.returnObject = returnObject;
    }

    private void validateReturnObjectType(String returnType) {
        if (returnType.equals("boolean") && !(returnObject instanceof Boolean)) {
                throwInvalidTypeException();
        } else if (returnType.equals("integer") && !(returnObject instanceof Integer)){
                throwInvalidTypeException();
        } else if (returnType.equals("float") && !(returnObject instanceof Float)){
                throwInvalidTypeException();
        } else if (returnType.equals("string") && !(returnObject instanceof String)){
                throwInvalidTypeException();
        } else if (returnType.equals("array") && !(returnObject instanceof List)){
                throwInvalidTypeException();
        } else if (returnType.equals("object") && !(returnObject instanceof Map)){
                throwInvalidTypeException();
        }
    }

    private Object throwInvalidTypeException() {
        throw new IllegalArgumentException("Invalid return type given in " + getName() + " " + getVersion() + " for action: " + getActionName() );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Action)) {
            return false;
        }

        Action action = (Action) o;

        if (!getParams().equals(action.getParams())) {
            return false;
        }
        return getTransport().equals(action.getTransport());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getParams().hashCode();
        result = 31 * result + getTransport().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Action{" +
                "params=" + params +
                ", transport=" + transport +
                "} " + super.toString();
    }
}
