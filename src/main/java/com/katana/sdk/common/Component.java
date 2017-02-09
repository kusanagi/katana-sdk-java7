package com.katana.sdk.common;

import com.katana.api.commands.Mapping;
import com.katana.api.commands.common.CommandPayload;
import com.katana.api.Api;
import com.katana.api.Resource;
import com.katana.api.replies.common.CommandReplyResult;
import com.katana.common.Constants;
import com.katana.common.utils.*;
import org.zeromq.ZMQ;

import java.util.*;

/**
 * Created by juan on 27/08/16.
 * Katana Java SDK
 */

/**
 * @param <T>
 * @param <S>
 */
public abstract class Component<T extends Api, S extends CommandReplyResult, R extends Component> implements ComponentWorker.WorkerListener {

    private static final Option[] APP_OPTIONS = new Option[]{
            new Option(new String[]{"-p", "--platform-version"}, true, true, true),
            new Option(new String[]{"-c", "--component"}, true, true, true),
            new Option(new String[]{"-n", "--name"}, true, true, true),
            new Option(new String[]{"-v", "--version"}, true, true, true),
            new Option(new String[]{"-s", "--socket"}, true, false, true),
            new Option(new String[]{"-t", "--tcp"}, true, false, true),
            new Option(new String[]{"-V", "--var"}, false, false, true),
            new Option(new String[]{"-d", "--disable-compact-names"}, true, false, false),
            new Option(new String[]{"-D", "--debug"}, true, false, false),
            new Option(new String[]{"-C", "--callback"}, true, false, true),
            new Option(new String[]{"-q", "--quiet"}, true, false, false),
    };

    private final String workerEndpoint;

    private String component;

    private boolean disableCompactName;

    private String name;

    private String version;

    private String platformVersion;

    private String socket;

    private String tcp;

    private boolean debug;

    private Map<String, String> var;

    private String callback;

    private boolean quiet;

    private List<Resource<T>> resources;

    protected EventCallable<R> startupCallable;

    protected EventCallable<R> shutdownCallable;

    protected EventCallable<R> errorCallable;

    private ZMQ.Socket router;

    private ZMQ.Context context;

    private Serializer serializer;

    private ZMQ.Socket dealer;

    private OptionManager optionManager;

    /**
     * Initialize the component with the command line arguments
     *
     * @param args list of command line arguments
     * @throws IllegalArgumentException throws an IllegalArgumentException if any of the REQUIRED arguments is missing,
     *                                  if there is an invalid argument or if there are duplicated arguments
     */
    public Component(String[] args) {
        this.var = new HashMap<>();
        this.serializer = new MessagePackSerializer();
        this.optionManager = new OptionManager();
        this.optionManager.setOptions(Arrays.asList(APP_OPTIONS));

        setArgs(args);

        if (this.tcp == null && this.socket == null) {
            generateDefaultSocket();
        }

        if (isDebug() && !this.quiet) {
            Logger.activate();
        }

        this.workerEndpoint = Constants.WORKER_ENDPOINT + "_" + UUID.randomUUID().toString();
    }

    /**
     * @return
     */
    public String getComponent() {
        return component;
    }

    /**
     * @param component
     */
    public void setComponent(String component) {
        this.component = component;
    }

    /**
     * @return
     */
    public boolean isDisableCompactName() {
        return disableCompactName;
    }

    /**
     * @param disableCompactName
     */
    public void setDisableCompactName(boolean disableCompactName) {
        this.disableCompactName = disableCompactName;
    }


    /**
     * Name getter
     *
     * @return return the name of the component
     */
    public String getName() {
        return name;
    }

    /**
     * Name setter
     *
     * @param name name of the component
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Version getter
     *
     * @return return the version of the component
     */
    public String getVersion() {
        return version;
    }

    /**
     * Version setter
     *
     * @param version version of the component
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Platform version getter
     *
     * @return return the version of the platform
     */
    public String getPlatformVersion() {
        return platformVersion;
    }

    /**
     * Platform version setter
     *
     * @param platformVersion
     */
    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    /**
     * Socket getter
     *
     * @return returns the socket of the component
     */
    public String getSocket() {
        return socket;
    }

    /**
     * Socket setter
     *
     * @param socket socket of the component
     */
    public void setSocket(String socket) {
        this.socket = socket;
    }

    /**
     * @return
     */
    public String getTcp() {
        return tcp;
    }

    /**
     * @param tcp
     */
    public void setTcp(String tcp) {
        this.tcp = tcp;
    }

    /**
     * com.katana.sdk.common.Component variable getter
     *
     * @return return a the list of variable for the component
     */
    public Map<String, String> getVar() {
        return var;
    }

    /**
     * com.katana.sdk.common.Component variable setter
     *
     * @param var list of variables to be used by the component
     */
    public void setVar(Map<String, String> var) {
        this.var = var;
    }

    /**
     * Debug mode getter
     *
     * @return true is the component is in debug mode and false otherwise
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * Debug mode setter
     *
     * @param debug flag to set the debug mode
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    // SDK METHODS

    public boolean setResource(String name, Callable<T> resource){
        if (this.resources == null){
            this.resources = new ArrayList<>();
        }
        this.resources.add(new Resource<T>(name, resource));
        return true;
    }

    public boolean hasResource(String name){
        if (this.resources == null)
            return false;
        for (Resource<T> resource : this.resources){
            if (resource.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public Resource<T> getResource(String name){
        if (this.resources == null)
            return null;
        for (Resource<T> resource : this.resources){
            if (resource.getName().equals(name)){
                return resource;
            }
        }
        return null;
    }

    public Component<T, S, R> startup(EventCallable<R> callback){
        this.startupCallable = callback;
        return this;
    }

    public Component<T, S, R> shutdown(EventCallable<R> callback){
        this.shutdownCallable = callback;
        return this;
    }

    public Component<T, S, R> error(EventCallable<R> callback){
        this.errorCallable = callback;
        return this;
    }

    /**
     * The method to run the component
     */
    public void run() {
        startSocket();

        setWorkers();

        ZMQ.proxy(router, dealer, null);
    }

    public boolean log(String value){
        Logger.log(Logger.DEBUG, value);
        return true;
    }

    private void generateDefaultSocket() {
        this.socket = "@katana-" + this.component + "-" + this.name + "-" + this.version;
    }

    /**
     * @param componentType
     * @param commandBytes
     * @return
     */
    @Override
    public byte[] onRequestReceived(String componentType, byte[] mappings, byte[] commandBytes) {
        try {
            CommandPayload<T> command = serializer.read(commandBytes, getCommandPayloadClass(componentType));
            Mapping mapping = new Mapping();
            mapping.setServiceSchema(mappings == null ? null : serializer.read(mappings, Map.class));
            S commandReply = processRequest(componentType, mapping, command);
            return serializer.write(commandReply);
        } catch (Exception e) {
            Logger.log(Logger.ERROR, e);
            return new byte[0];
        }
    }

    /**
     * @param componentType
     * @return
     */
    protected abstract Class<? extends CommandPayload> getCommandPayloadClass(String componentType);

    /**
     * @param componentType
     * @param command
     */
    protected void setBaseCommandAttrs(String componentType, Mapping mapping, T command) {
        command.setName(this.getName());
        command.setProtocolVersion(this.getVersion());
        command.setPlatformVersion(this.getPlatformVersion());
        command.setDebug(this.isDebug());
        command.setVariables(this.getVar());
        command.setMapping(mapping);
    }

    /**
     * @param componentType
     * @param response
     * @return
     */
    protected abstract S getCommandReplyPayload(String componentType, T response);

    /**
     * @param response
     * @return
     */
    protected abstract CommandReplyResult getReply(String componentType, T response);

    private void setWorkers() {
        int workerCount = 1;
        if (this.var.containsKey("workers")){
            workerCount = Integer.valueOf(this.var.get("workers"));
            workerCount = workerCount < 1 ? 1 : workerCount;
        }

        for (int i = 0; i < workerCount; i++) {
            ComponentWorker componentWorker = new ComponentWorker(workerEndpoint);
            componentWorker.setWorkerListener(this);
            componentWorker.start();
        }
    }

    private void startSocket() {
        context = ZMQ.context(1);
        router = context.socket(ZMQ.ROUTER);
        dealer = context.socket(ZMQ.DEALER);
        bindSocket();
    }

    private void bindSocket() {
        if (this.tcp != null) {
            router.bind("tcp://127.0.0.1:" + this.tcp);
        } else {
            router.bind("ipc://" + this.socket);
        }

        dealer.bind(this.workerEndpoint);
    }

    private S processRequest(String componentType, Mapping mapping, CommandPayload<T> commandPayload) {
        T command = commandPayload.getCommand().getArgument();
        setBaseCommandAttrs(componentType, mapping, command);
        getCallable(componentType).run(command);

        return getCommandReplyPayload(componentType, command);
    }

    protected abstract Callable<T> getCallable(String componentType);

    private void stopSocket() {
        dealer.close();
        router.close();
        context.term();
    }

    private void setArgs(String[] args) throws IllegalArgumentException {
        optionManager.extractOptions(args);
        setMembers(optionManager.getOptions());
    }
    private void setMembers(List<Option> options) {
        for (Option option : options) {
            switch (option.getNames()[0]) {
                case "-p":
                    this.platformVersion = option.getValue();
                    break;
                case "-c":
                    this.component = option.getValue();
                    break;
                case "-n":
                    this.name = option.getValue();
                    break;
                case "-v":
                    this.version = option.getValue();
                    break;
                case "-s":
                    this.socket = option.getValue();
                    break;
                case "-t":
                    this.tcp = option.getValue();
                    break;
                case "-V":
                    String varName = option.getValue().split("=")[0];
                    String varValue = option.getValue().split("=")[1];
                    this.var.put(varName, varValue);
                    break;
                case "-d":
                    this.disableCompactName = true;
                    break;
                case "-D":
                    this.debug = true;
                    break;
                case "-C":
                    this.callback = option.getValue();
                    break;
                case "-q":
                    this.quiet = true;
                    break;
                default:
                    Logger.log(Logger.ERROR, "Unsupported parameter " + option.getNames()[0]);
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return "Component{" +
                "component='" + component + '\'' +
                ", disableCompactName=" + disableCompactName +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", platformVersion='" + platformVersion + '\'' +
                ", socket='" + socket + '\'' +
                ", tcp='" + tcp + '\'' +
                ", debug=" + debug +
                ", var=" + var +
                ", callback=" + callback +
                ", quiet=" + quiet +
                '}';
    }
}
