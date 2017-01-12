package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by juan on 27/08/16.
 */
public class Action extends Api {
    @JsonProperty("p")
    private Map<String, Param> params;

    @JsonProperty("T")
    private Transport transport;

    private String actionName;

    /**
     * Default constructor
     */
    public Action() {
        // Default constructor to make possible the serialization of this object.
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
    public void setParams(Map<String, Param> params) {
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

    // SDK Methods

    /**
     * Determine whether or not the current Service is the initial Service called in a request.
     *
     * @return Return true if the Service is the initial Service called in a request
     */
    @JsonIgnore
    public boolean isOrigin() {
        if (this.transport.getOriginService().length == 0) {
            return false;
        }
        return this.transport.getOriginService()[0].equals(getName());
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
        return this.params != null && this.params.get(name) != null;
    }

    /**
     * Get the parameter with the REQUIRED case sensitive name argument which MUST be returned as a Param object.
     * To read a file provided with the request the getFile() function MUST be used to access the File object.
     *
     * @param name Name of the param
     * @return Return the value of the param
     */
    public Param getParam(String name) {
        if (this.params == null) {
            return null;
        }
        return params.get(name);
    }

    /**
     * Param getter
     *
     * @return Return all the params of the action
     */
    public Map<String, Param> getParams() {
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
        for (String path : pathFiles.keySet()) {
            Map<String, Map<String, Map<String, Map<String, File>>>> serviceFiles = pathFiles.get(path);
            for (String service : serviceFiles.keySet()) {
                Map<String, Map<String, Map<String, File>>> versionFiles = serviceFiles.get(service);
                for (String version : versionFiles.keySet()) {
                    Map<String, Map<String, File>> actionFiles = versionFiles.get(version);
                    for (String action : actionFiles.keySet()) {
                        Map<String, File> nameFiles = actionFiles.get(action);
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
    public File getFile(String name) {
        Map<String, Map<String, Map<String, Map<String, Map<String, File>>>>> pathFiles = this.transport.getFiles();
        for (String path : pathFiles.keySet()) {
            Map<String, Map<String, Map<String, Map<String, File>>>> serviceFiles = pathFiles.get(path);
            for (String service : serviceFiles.keySet()) {
                Map<String, Map<String, Map<String, File>>> versionFiles = serviceFiles.get(service);
                for (String version : versionFiles.keySet()) {
                    Map<String, Map<String, File>> actionFiles = versionFiles.get(version);
                    for (String action : actionFiles.keySet()) {
                        Map<String, File> nameFiles = actionFiles.get(action);
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
    public List<File> getFiles() {
        List<File> files = new ArrayList<>();
        Map<String, Map<String, Map<String, Map<String, Map<String, File>>>>> pathFiles = this.transport.getFiles();
        for (String path : pathFiles.keySet()) {
            Map<String, Map<String, Map<String, Map<String, File>>>> serviceFiles = pathFiles.get(path);
            for (String service : serviceFiles.keySet()) {
                Map<String, Map<String, Map<String, File>>> versionFiles = serviceFiles.get(service);
                for (String version : versionFiles.keySet()) {
                    Map<String, Map<String, File>> actionFiles = versionFiles.get(version);
                    for (String action : actionFiles.keySet()) {
                        Map<String, File> nameFiles = actionFiles.get(action);
                        for (String name : nameFiles.keySet()) {
                            files.add(nameFiles.get(name));
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
        Map<String, Map<String, Map<String, Map<String, Map<String, File>>>>> pathFile = this.transport.getFiles();
        Map<String, Map<String, Map<String, Map<String, File>>>> serviceFile = new HashMap<>();
        Map<String, Map<String, Map<String, File>>> versionFile = new HashMap<>();
        Map<String, Map<String, File>> actionFile = new HashMap<>();
        Map<String, File> nameFile = new HashMap<>();

        if (pathFile.containsKey(getPath())) {
            serviceFile = pathFile.get(getPath());
            if (serviceFile.containsKey(getName())) {
                versionFile = serviceFile.get(getName());
                if (versionFile.containsKey(getVersion())) {
                    actionFile = versionFile.get(getVersion());
                    if (actionFile.containsKey(getActionName())) {
                        nameFile = actionFile.get(getActionName());
                    } else {
                        actionFile.put(getActionName(), nameFile);
                    }
                } else {
                    actionFile.put(getActionName(), nameFile);
                    versionFile.put(getVersion(), actionFile);
                }
            } else {
                actionFile.put(getActionName(), nameFile);
                versionFile.put(getVersion(), actionFile);
                serviceFile.put(getName(), versionFile);
            }
        } else {
            actionFile.put(getActionName(), nameFile);
            versionFile.put(getVersion(), actionFile);
            serviceFile.put(getName(), versionFile);
            pathFile.put(getPath(), serviceFile);
        }

        nameFile.put(file.getName(), file);
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
        Map<String, Map<String, Map<String, Object>>> serviceData = new HashMap<>();
        Map<String, Map<String, Object>> versionData = new HashMap<>();
        Map<String, Object> actionData = new HashMap<>();
        if (pathData.containsKey(getPath())){
            serviceData = pathData.get(getPath());
            if (serviceData.containsKey(getName())){
                versionData = serviceData.get(getName());
                if (versionData.containsKey(getVersion())){
                    actionData = versionData.get(getVersion());
                } else{
                    versionData.put(getVersion(), actionData);
                }
            } else{
                versionData.put(getVersion(), actionData);
                serviceData.put(getName(), versionData);
            }
        } else{
            versionData.put(getVersion(), actionData);
            serviceData.put(getName(), versionData);
            pathData.put(getPath(), serviceData);
        }
        actionData.put(getActionName(), entity);
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
        //TODO empty method
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
    public Action relateMany(String primaryKey, String service, String[] foreignKey) {
        // TODO empty method
        return this;
    }

    public Action relateOneRemote(String primaryKey, String address, String service, String foreignKey) {
        //TODO empty method
        return this;
    }

    public Action relateManyRemote(String primaryKey, String address, String service, String foreignKey) {
        //TODO empty method
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
        if (pathLink.containsKey(getPath())){
            serviceLink = pathLink.get(getPath());
            if (serviceLink.containsKey(getName())){
                linkMap = serviceLink.get(getName());
            } else{
                serviceLink.put(getName(), linkMap);
            }
        } else{
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
    public Action commit(String action, Map<String, Map<String, String>> params) {
        // TODO empty method
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
    public Action rollback(String action, Map<String, Map<String, String>> params) {
        // TODO empty method
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
    public Action complete(String action, Map<String, Map<String, String>> params) {
        // TODO empty method
        return this;
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
    public Action call(String service, String version, String action, Map<String, Map<String, String>> params, List<File> files, String callback) {
        // TODO empty method
        return this;
    }

    public Action callRemote(String address, String service, String version, String action, Map<String, Map<String, String>> params, List<File> files, String callback){
        // TODO empty method
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

    public Action(Action other) {
        this.params = other.params;
        this.transport = other.transport;
        this.actionName = other.actionName;
    }
}
