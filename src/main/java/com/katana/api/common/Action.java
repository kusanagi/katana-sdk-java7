package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by juan on 27/08/16.
 */
public class Action extends Api {
    @JsonProperty("p")
    private Map<String, Map<String, Map<String, String>>> params;

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
     * Param getter
     *
     * @return Return all the params of the action
     */
    public Map<String, Map<String, Map<String, String>>> getParams() {
        return params;
    }

    /**
     * Params setter
     *
     * @param params Params object
     */
    public void setParams(Map<String, Map<String, Map<String, String>>> params) {
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
    public boolean setProperty(String name, String value) {
        TransportMeta meta = this.transport.getMeta();
        Map<String, String> properties = meta.getProperties();
        properties.put(name, value);
        return true;
    }

    /**
     * Determine if a parameter with the name specified by the REQUIRED case sensitive name argument in the location
     * defined by the OPTIONAL location argument, which MUST default to "query", was provided for the action.
     *
     * @param location Location of the param, must be either "path", "query", "form-data", "header", or "body"
     * @param name     Name of the Param
     * @return Return true if the Action has the param
     */
    public boolean hasParam(String location, String name) {
        String locationToUse = "query";
        if (location != null) {
            locationToUse = location;
        }
        return params.containsKey(locationToUse) && params.get(locationToUse).containsKey(name);
    }

    /**
     * Get the parameter with the REQUIRED case sensitive name argument in the location defined by the OPTIONAL
     * location argument, or default to "query", and which MUST be returned as a Param object.
     * The value of the location argument MUST be either "path", "query", "form-data", "header" or "body", where any
     * other value MUST be accepted as "query".
     * To read a file provided with the request the getFile() function MUST be used to access the File object.
     *
     * @param location Location of the param, must be either "path", "query", "form-data", "header", or "body"
     * @param name     Name of the param
     * @return Return the value of the param
     */
    public Map<String, String> getParam(String location, String name) {
        String locationToUse = "query";
        if (location != null) {
            locationToUse = location;
        }
        return params.get(locationToUse).get(name);
    }

    /**
     * get all the parameters in the location defined by the OPTIONAL location argument, or all parameters in all
     * locations, and which MUST be returned as an array of Param objects. If no parameters are found an empty array
     * MUST be returned.
     * The value of the location argument MUST be either "path", "query", "form-data", "header" or "body", where any
     * other value MUST be accepted as "query".
     *
     * @param location Location of the param, must be either "path", "query", "form-data", "header", or "body"
     * @return Return the new param, if no param are found an empty array is returned
     */
    public Map<String, Map<String, String>> getParams(String location) {
        String locationToUse = "query";
        if (location != null) {
            locationToUse = location;
        }
        return params.get(locationToUse);
    }

    /**
     * Create a new parameter with the REQUIRED name argument in the location defined by
     * the OPTIONAL location argument, or default to "query", and which MUST be returned as a Param object.
     * The value of the location argument MUST be either "path", "query", "form-data", "header" or "body", where any
     * other value MUST be accepted as "query".
     * If the OPTIONAL value or type arguments are specified these MUST also be applied to the Param object. The value
     * of the type argument MUST be either "null", "boolean", "integer", "float", "string", "array" or "object", where
     * any other value MUST be accepted as "string".
     * When creating a new Param object the value of the exists property MUST be false.
     *
     * @param location Location of the param, must be either "path", "query", "form-data", "header", or "body"
     * @param name     Name of the new param
     * @param value    Value of the new param
     * @param type     Data type of the new param, MUST be either "null", "boolean", "integer", "float", "string", "array" or "object"
     * @return Return the new param
     */
    public Map<String, String> newParam(String location, String name, String value, String type) {
        String locationToUse = "query";
        if (location != null) {
            locationToUse = location;
        }

        Map<String, Map<String, String>> locationMap = new HashMap<>();
        Map<String, String> nameMap = new HashMap<>();

        if (this.params.containsKey(locationToUse)) {
            locationMap = this.params.get(locationToUse);
            if (locationMap.containsKey(name)) {
                nameMap = locationMap.get(name);
            } else {
                locationMap.put(name, nameMap);
            }
        } else {
            this.params.put(locationToUse, locationMap);
            locationMap.put(name, nameMap);
        }

        nameMap.put("v", value);
        return nameMap;
    }

    /**
     * Determine if a file with the parameter name specified by the REQUIRED case sensitive
     * name argument was provided for the action.
     *
     * @param name Name of the file
     * @return Return true if the action has the file
     */
    public boolean hasFile(String name) {
        for (File file : this.transport.getFiles()) {
            if (file.getName().equals(name)) {
                return true;
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
        for (File file : this.transport.getFiles()) {
            if (file.getName().equals(name)) {
                return file;
            }
        }
        return newFile(name, "", "");
    }

    /**
     * Get all the files, which MUST be returned as an array of File objects. If no files are found an empty array
     * MUST be returned.
     *
     * @return Return all the files
     */
    public List<File> getFiles() {
        return this.transport.getFiles();
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
    public File newFile(String name, String path, String mime) { //TODO path and mime
        File file = new File();
        file.setName(name);
        this.transport.getFiles().add(file);
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
    public boolean setDownload(File file) {
        //TODO empty method
        return true;
    }

    /**
     * Register an entity object with the REQUIRED entity argument under the current Service namespace, version and
     * action name.
     * If data already exists it MUST be replaced with the given entity.
     *
     * @param entity Entity to be registered
     * @return Return true if the operation was successful
     */
    public boolean setEntity(Object entity) {
        return this.transport.addData(getName(), getVersion(), actionName, entity);
    }

    /**
     * Register an array of entity objects with the REQUIRED collection argument under the current Service namespace,
     * version and action name.
     * If data already exists it MUST be replaced with the given collection.
     *
     * @param collection Collection array to be registered
     * @return Return true if the operation was successful
     */
    public boolean setCollection(List<?> collection) {
        return this.transport.addData(getName(), getVersion(), getActionName(), collection);
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
    public boolean relateOne(String primaryKey, String service, String foreignKey) {
        //TODO empty method
        return true;
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
    public boolean relateMany(String primaryKey, String service, String[] foreignKey) {
        // TODO empty method
        return true;
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
    public boolean setLink(String link, String uri) {
        return this.transport.addLink(getName(), link, uri);
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
    public boolean commit(String action, Map<String, Map<String, String>> params) {
        // TODO empty method
        return false;
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
    public boolean rollback(String action, Map<String, Map<String, String>> params) {
        // TODO empty method
        return false;
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
    public boolean complete(String action, Map<String, Map<String, String>> params) {
        // TODO empty method
        return false;
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
    public boolean call(String service, String version, String action, Map<String, Map<String, String>> params, List<File> files) {
        // TODO empty method
        return true;
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
    public boolean error(String message, int code, String status) {
        Error error = new Error();
        error.setMessage(message);
        error.setCode(code);
        error.setStatus(status);
        this.transport.getErrors().add(error);
        return true;
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
