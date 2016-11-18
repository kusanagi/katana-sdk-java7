package com.katana.api.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.katana.api.Error;
import com.katana.api.replies.CommandReplyResult;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by juan on 14/09/16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transport implements CommandReplyResult {

    @JsonProperty("m")
    private TransportMeta meta;

    @JsonProperty("b")
    private File body;

    @JsonProperty("f")
    private Map<String, Map<String, Map<String, Map<String, File>>>> files;

    @JsonProperty("d")
    private Map<String, Map<String, Map<String, Object>>> data;

    @JsonProperty("r")
    private Map<String, Map<String, Map<String, Object>>> relations;

    @JsonProperty("l")
    private Map<String, Map<String, String>> links;

    @JsonProperty("c")
    private Map<String, Map<String, List<Call>>> calls;

    @JsonProperty("t")
    private Transaction[] transactions;

    @JsonProperty("e")
    private List<Error> errors;

    /**
     *
     */
    public Transport() {
        this.links = new HashMap<>();
    }

    /**
     * Transport meta getter
     *
     * @return Return the transport meta
     */
    public TransportMeta getMeta() {
        return meta;
    }

    /**
     * Transport meta setter
     *
     * @param meta Transport meta object
     */
    public void setMeta(TransportMeta meta) {
        this.meta = meta;
    }

    /**
     * File getter
     *
     * @return Return the file
     */
    public File getBody() {
        return body;
    }

    /**
     * File body setter
     *
     * @param body File object
     */
    public void setBody(File body) {
        this.body = body;
    }

    /**
     * File list getter
     *
     * @return Return the list of files
     */
    public Map<String, Map<String, Map<String, Map<String, File>>>> getFiles() {
        return files;
    }

    /**
     * File list setter
     *
     * @param files File list
     */
    public void setFiles(Map<String, Map<String, Map<String, Map<String, File>>>> files) {
        this.files = files;
    }

    /**
     * Data getter
     *
     * @return Return data object
     */
    public Map<String, Map<String, Map<String, Object>>> getData() {
        return data;
    }

    /**
     * Data setter
     *
     * @param data Data object
     */
    public void setData(Map<String, Map<String, Map<String, Object>>> data) {
        this.data = data;
    }

    /**
     * Relations getter
     *
     * @return Return the list of the relations
     */
    public Map<String, Map<String, Map<String, Object>>> getRelations() {
        return relations;
    }

    /**
     * Relations setter
     *
     * @param relations Relation list
     */
    public void setRelations(Map<String, Map<String, Map<String, Object>>> relations) {
        this.relations = relations;
    }

    /**
     * Links getter
     *
     * @return Return the links
     */
    public Map<String, Map<String, String>> getLinks() {
        return links;
    }

    /**
     * Links setter
     *
     * @param links Links to set
     */
    public void setLinks(Map<String, Map<String, String>> links) {
        this.links = links;
    }

    /**
     * Calls getter
     *
     * @return Return the call
     */
    public Map<String, Map<String, List<Call>>> getCalls() {
        return calls;
    }

    /**
     * Calls setter
     *
     * @param calls RequestCall list
     */
    public void setCalls(Map<String, Map<String, List<Call>>> calls) {
        this.calls = calls;
    }

    /**
     * Transaction getter
     *
     * @return Return the transactions
     */
    public Transaction[] getTransactions() {
        return transactions;
    }

    /**
     * Transaction setter
     *
     * @param transactions Transaction list
     */
    public void setTransactions(Transaction[] transactions) {
        this.transactions = transactions;
    }

    /**
     * Errors getter
     *
     * @return Return the error list
     */
    public List<Error> getErrors() {
        return errors;
    }

    /**
     * Error setter
     *
     * @param errors Error list
     */
    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    // SDK Methods

    /**
     * @return Return the UUID of the request.
     */
    @JsonIgnore
    public String getRequestId() {
        return this.meta.getId();
    }

    /**
     * @return Return the creation datetime of the request.
     */
    @JsonIgnore
    public String getRequestTimeStamp() {
        return this.meta.getDatetime();
    }

    /**
     * @return Return the array containing name, version and action of the Service that was the origin of the request.
     * If an origin was not set this MUST return an empty array.
     */
    @JsonIgnore
    public String[] getOriginService() {
        return this.meta.getOrigin();
    }

    /**
     * Get a custom userland property with the REQUIRED case sensitive name argument.
     * The default argument is the OPTIONAL value to use if the property does not exist. If the property is defined in
     * the Transport, but does not have a value, the value of the default argument SHOULD NOT be applied.
     * If a property with the specified name does not exist, and no default is provided, and empty string MUST be
     * returned.
     *
     * @param name          Argument name
     * @param defaultString Value to use if the property does not exist
     * @return If a property with the specified name does not exist, and no default is provided, and empty string MUST be
     * returned, otherwise the value of the property will be returned
     */
    public String getProperty(String name, String defaultString) {
        String property = this.meta.getProperties().get(name);
        return property == null ? defaultString == null ? "" : defaultString : property;
    }

    /**
     * Determine if a file download has been registered for the HTTP response.
     *
     * @return Return true if a download file has been registered
     */
    public boolean hasDownload() {
        return this.body != null;
    }

    /**
     * @return Return the file download defined for the HTTP response as a File object.
     */
    @JsonIgnore
    public File getDownload() {
        return this.body;
    }

    /**
     * Return all of the data as an object, as it is stored in the Transport. If the OPTIONAL case sensitive service
     * argument is specified, it MUST only return the data stored under that Service namespace. If the OPTIONAL case
     * sensitive version argument is specified, it MUST only return the data stored under the specified Service
     * namespace and version. If the OPTIONAL case sensitive action argument is specified, it MUST only return the
     * data stored under the specified Service namespace, version and action name.
     *
     * @param service Service name
     * @param version Version of the service
     * @param action  Name of the action
     * @return Return all the data as an object, as it is stored in the transport
     */
    public Object getData(String service, String version, String action) {
        if (service != null) {
            if (version != null) {
                if (action != null) {
                    return this.data.get(service).get(version).get(action);
                }
                return this.data.get(service).get(version);
            }
            return this.data.get(service);
        }
        return this.data;
    }

    /**
     * Return all of the relations as an object, as they are stored in the Transport. If the OPTIONAL case sensitive
     * service argument is specified, it MUST only return the relations stored under that Service namespace.
     *
     * @param service Service name
     * @return Return the relations
     */
    public Map<String, Map<String, Map<String, Object>>> getRelations(String service) {
        if (service == null) {
            return this.relations;
        } else {
            Map<String, Map<String, Map<String, Object>>> serviceRelations = new HashMap<>();
            if (this.relations.containsKey(service)) {
                serviceRelations.put(service, this.relations.get(service));
            }
            return serviceRelations;
        }
    }

    /**
     * Return all of the links as an object, as they are stored in the Transport. If the OPTIONAL case sensitive
     * service argument is specified, it MUST only return the links stored under that Service namespace.
     *
     * @param name Service name
     * @param link Link name
     * @param uri  Uri of the link
     * @return Return all the links as an object, as they are stored in the Transport
     */
    public boolean addLink(String name, String link, String uri) {
        Map<String, String> linkDic = new HashMap<>();
        linkDic.put(link, uri);
        this.links.put(name, linkDic);
        return true;
    }

    /**
     * Return all of the links as an object, as they are stored in the Transport. If the OPTIONAL case sensitive
     * service argument is specified, it MUST only return the links stored under that Service namespace.
     *
     * @param service Service name
     * @return Return all the links as an object, as they are stored in the Transport
     */
    public Map<String, String> getLinks(String service) {
        if (service != null) {
            return this.links.get(service);
        } else {
            Map<String, String> linkMap = new HashMap<>();
            for (Map<String, String> links : this.links.values()) {
                linkMap.putAll(links);
            }
            return linkMap;
        }
    }

    /**
     * Return all of the calls as an object, as they are stored in the Transport. If the OPTIONAL case sensitive
     * service argument is specified, it MUST only return the calls stored under that Service namespace.
     *
     * @param service Service name
     * @return Return all the calls as an object, as they are stored in the Transport.
     */
    public Map<String, Map<String, List<Call>>> getCalls(String service) {
        if (service == null) {
            return this.calls;
        } else {
            Map<String, Map<String, List<Call>>> serviceCalls = new HashMap<>();
            if (this.calls.containsKey(service)) {
                serviceCalls.put(service, this.calls.get(service));
            }
            return serviceCalls;
        }
    }

    /**
     * Return all of the transactions as an object, as they are stored in the Transport. If the OPTIONAL case
     * sensitive service argument is specified, it MUST only return the transactions stored under that Service
     * namespace.
     *
     * @param service Service name
     * @return Return all the transactions as an object, as they are stored in the Transport.
     */
    public Transaction[] getTransactions(String service) {
        //TODO transactions
        return this.transactions;
    }

    /**
     * Return all of the errors as an object, as they are stored in the Transport. If the OPTIONAL case sensitive
     * service argument is specified, it MUST only return the links stored under that Service namespace.
     *
     * @param service Service name
     * @return Return all the errors as an object, as they are stored in the Transport.
     */
    public List<Error> getErrors(String service) {
        return this.errors;
    }

    /**
     * Register a data in the transport
     *
     * @param name       Service name
     * @param version    Version of the service
     * @param actionName Name of the action
     * @param collection Data collection
     * @return Return true if the operation was successful
     */
    public boolean addData(String name, String version, String actionName, Object collection) {
        if (this.data == null) {
            this.data = new HashMap<>();
        }

        Map<String, Map<String, Object>> versionMap = new HashMap<>();
        Map<String, Object> actionMap = new HashMap<>();

        if (this.data.containsKey(version)) {
            versionMap = this.data.get(name);
            if (this.data.get(version).containsKey(actionName)) {
                actionMap = this.data.get(version).get(version);
            }
        }

        actionMap.put(actionName, collection);
        versionMap.put(version, actionMap);
        this.data.put(name, versionMap);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transport)) {
            return false;
        }

        Transport transport = (Transport) o;

        if (getMeta() != null ? !getMeta().equals(transport.getMeta()) : transport.getMeta() != null) {
            return false;
        }
        if (getBody() != null ? !getBody().equals(transport.getBody()) : transport.getBody() != null) {
            return false;
        }
        if (getFiles() != null ? !getFiles().equals(transport.getFiles()) : transport.getFiles() != null) {
            return false;
        }
        if (getData() != null ? !getData().equals(transport.getData()) : transport.getData() != null) {
            return false;
        }
        if (getRelations() != null ? !getRelations().equals(transport.getRelations()) : transport.getRelations() != null) {
            return false;
        }
        if (getLinks() != null ? !getLinks().equals(transport.getLinks()) : transport.getLinks() != null) {
            return false;
        }
        if (getCalls() != null ? !getCalls().equals(transport.getCalls()) : transport.getCalls() != null) {
            return false;
        }
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getTransactions(), transport.getTransactions())) {
            return false;
        }
        return getErrors() != null ? getErrors().equals(transport.getErrors()) : transport.getErrors() == null;

    }

    @Override
    public int hashCode() {
        int result = getMeta() != null ? getMeta().hashCode() : 0;
        result = 31 * result + (getBody() != null ? getBody().hashCode() : 0);
        result = 31 * result + (getFiles() != null ? getFiles().hashCode() : 0);
        result = 31 * result + (getData() != null ? getData().hashCode() : 0);
        result = 31 * result + (getRelations() != null ? getRelations().hashCode() : 0);
        result = 31 * result + (getLinks() != null ? getLinks().hashCode() : 0);
        result = 31 * result + (getCalls() != null ? getCalls().hashCode() : 0);
        result = 31 * result + Arrays.hashCode(getTransactions());
        result = 31 * result + (getErrors() != null ? getErrors().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "meta=" + meta +
                ", body=" + body +
                ", files=" + files +
                ", data=" + data +
                ", relations=" + relations +
                ", links=" + links +
                ", calls=" + calls +
                ", transactions=" + Arrays.toString(transactions) +
                ", errors=" + errors +
                '}';
    }
}
