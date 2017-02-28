package com.katana.sdk.common;

import com.katana.api.Api;
import com.katana.api.commands.Mapping;
import com.katana.api.commands.common.CommandPayload;
import com.katana.api.replies.common.CommandReplyResult;
import com.katana.api.schema.ActionSchema;
import com.katana.api.schema.ServiceSchema;
import com.katana.common.Constants;
import com.katana.common.utils.Logger;
import com.katana.common.utils.MessagePackSerializer;
import com.katana.common.utils.Option;
import com.katana.common.utils.OptionManager;
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

    public static final String LOCALHOST = "127.0.0.1";

    private final String workerEndpoint;

    private String componentName;

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

    private Map<String, Callable<T>> resources;

    protected EventCallable<R> startupCallable;

    protected EventCallable<R> shutdownCallable;

    protected EventCallable<R> errorCallable;

    private ZMQ.Socket router;

    private ZMQ.Context context;

    private Serializer serializer;

    private ZMQ.Socket dealer;

    private OptionManager optionManager;

    /**
     * Initialize the componentName with the command line arguments
     *
     * @param args list of command line arguments
     * @throws IllegalArgumentException throws an IllegalArgumentException if any of the REQUIRED arguments is missing,
     *                                  if there is an invalid argument or if there are duplicated arguments
     */
    public Component(String[] args) {
        this.var = new HashMap<>();
        this.resources = new HashMap<>();
        this.serializer = new MessagePackSerializer();
        this.optionManager = new OptionManager();
        this.optionManager.setOptions(Arrays.asList(APP_OPTIONS));

        setArgs(args);

        if (this.tcp == null && this.socket == null) {
            generateDefaultSocket();
        }

        if (isDebug() && !this.quiet) {
            Logger.activate();
        } else {
            Logger.deactivate();
        }

        this.workerEndpoint = Constants.WORKER_ENDPOINT + "_" + UUID.randomUUID().toString();
    }

    /**
     * @return
     */
    public String getComponent() {
        return componentName;
    }

    /**
     * @param component
     */
    public void setComponent(String component) {
        this.componentName = component;
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
     * @return return the name of the componentName
     */
    public String getName() {
        return name;
    }

    /**
     * Name setter
     *
     * @param name name of the componentName
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Version getter
     *
     * @return return the version of the componentName
     */
    public String getVersion() {
        return version;
    }

    /**
     * Version setter
     *
     * @param version version of the componentName
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
     * @return returns the socket of the componentName
     */
    public String getSocket() {
        return socket;
    }

    /**
     * Socket setter
     *
     * @param socket socket of the componentName
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
     * @return return a the list of variable for the componentName
     */
    public Map<String, String> getVar() {
        return var;
    }

    /**
     * com.katana.sdk.common.Component variable setter
     *
     * @param var list of variables to be used by the componentName
     */
    public void setVar(Map<String, String> var) {
        this.var = var;
    }

    /**
     * Debug mode getter
     *
     * @return true is the componentName is in debug mode and false otherwise
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

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public boolean isQuiet() {
        return quiet;
    }

    public void setQuiet(boolean quiet) {
        this.quiet = quiet;
    }

    // SDK METHODS

    public boolean setResource(String name, Callable<T> resource) {
        this.resources.put(name, resource);
        return true;
    }

    public boolean hasResource(String name) {
        return this.resources.containsKey(name);
    }

    public Callable<T> getResource(String name) {
        return this.resources.get(name);
    }

    public Component<T, S, R> startup(EventCallable<R> callback) {
        this.startupCallable = callback;
        return this;
    }

    public Component<T, S, R> shutdown(EventCallable<R> callback) {
        this.shutdownCallable = callback;
        return this;
    }

    public Component<T, S, R> error(EventCallable<R> callback) {
        this.errorCallable = callback;
        return this;
    }

    /**
     * The method to run the componentName
     */
    public void run() {
        startSocket();

        setWorkers();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                if (shutdownCallable != null) {
                    runShutdown();
                }

                stopSocket();
            }
        });

        ZMQ.proxy(router, dealer, null);
    }

    protected abstract void runShutdown();

    private void setWorkers() {
        int workerCount = 1;
        if (this.var.containsKey("workers")) {
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
            router.bind("tcp://" + LOCALHOST + ":" + this.tcp);
        } else {
            router.bind("ipc://" + this.socket);
        }

        dealer.bind(this.workerEndpoint);
    }

    public void stopSocket() {
        dealer.close();
        router.close();
//        context.term();
    }

    public boolean log(String value) {
        Logger.log(Logger.DEBUG, value);
        return this.debug;
    }

    private void generateDefaultSocket() {
        this.socket = "@katana-" + this.componentName + "-" + this.name + "-" + this.version;
    }

    /**
     * @param componentType
     * @param commandBytes
     * @return
     */
    @Override
    public byte[] onRequestReceived(String componentType, byte[] mappings, byte[] commandBytes) {
        try {
            CommandPayload<T> command = serializer.deserialize(commandBytes, getCommandPayloadClass(componentType));
            Mapping mapping = deserializeMappings(mappings);
            S commandReply = processRequest(componentType, mapping, command);
            return serializer.serializeInBytes(commandReply);
        } catch (Exception e) {
            Logger.log(e);
            return new byte[0];
        }
    }

    private Mapping deserializeMappings(byte[] mappings) {
        if (mappings == null) {
            return null;
        }

        Mapping mapping = new Mapping();

        Map<String, Object> schemas = serializer.deserialize(mappings, Map.class);
        for (Map.Entry serviceKey : schemas.entrySet()) {
            Map<String, Object> versionMap = (Map) schemas.get((String) serviceKey.getKey());
            for (Map.Entry versionKey : versionMap.entrySet()) {
                Map<String, Object> serviceSchemaMap = (Map) versionMap.get((String) versionKey.getKey());

                String jsonServiceSchema = serializer.serializeInJson(serviceSchemaMap);
                ServiceSchema serviceSchema = serializer.deserialize(jsonServiceSchema, ServiceSchema.class);
                serviceSchema.setName((String) serviceKey.getKey());
                serviceSchema.setVersion((String) versionKey.getKey());
                for (String action : serviceSchema.getActions()) {
                    ActionSchema actionSchema = serviceSchema.getActionSchema(action);
                    actionSchema.setName(action);
                    for (Map.Entry param : actionSchema.getParams().entrySet()) {
                        actionSchema.getParamSchema((String) param.getKey()).setName((String) param.getKey());
                    }
                }

                Map<String, ServiceSchema> newVersionMap = new HashMap<>();
                Map<String, Map<String, ServiceSchema>> newServiceSchema = new HashMap<>();

                newVersionMap.put((String) versionKey.getKey(), serviceSchema);
                newServiceSchema.put((String) serviceKey.getKey(), newVersionMap);

                mapping.setServiceSchema(newServiceSchema);
            }
        }

        return mapping;
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
        command.setComponent(this);
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

    private S processRequest(String componentType, Mapping mapping, CommandPayload<T> commandPayload) {
        T command = commandPayload.getCommand().getArgument();
        setBaseCommandAttrs(componentType, mapping, command);
        getCallable(componentType).run(command);

        return getCommandReplyPayload(componentType, command);
    }

    protected abstract Callable<T> getCallable(String componentType);

    private void setArgs(String[] args) throws IllegalArgumentException {
        List<Option> currentOptions = optionManager.extractOptions(args);
        setMembers(currentOptions);
    }

    private void setMembers(List<Option> options) {
        for (Option option : options) {
            switch (option.getNames()[0]) {
                case "-p":
                    this.platformVersion = option.getValue();
                    if (!this.platformVersion.matches(Constants.VERSION_PATTERN)) {
                        throw new IllegalArgumentException("Invalid platform version " + this.platformVersion);
                    }
                    break;
                case "-c":
                    this.componentName = option.getValue();
                    if (!this.componentName.equals(Constants.SERVICE) && !this.componentName.equals(Constants.MIDDLEWARE)) {
                        throw new IllegalArgumentException("Invalid componentName " + this.componentName);
                    }
                    break;
                case "-n":
                    this.name = option.getValue();
                    break;
                case "-v":
                    this.version = option.getValue();
                    if (!this.version.matches(Constants.VERSION_PATTERN)) {
                        throw new IllegalArgumentException("Invalid version " + this.version);
                    }
                    break;
                case "-s":
                    this.socket = option.getValue();
                    break;
                case "-t":
                    this.tcp = option.getValue();
                    break;
                case "-V":
                    String[] varObject = option.getValue().split("=");
                    if (varObject.length < 2) {
                        throw new IllegalArgumentException("Invalid variable " + option.getValue());
                    }
                    String varName = varObject[0];
                    String varValue = varObject[1];
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
                "componentName='" + componentName + '\'' +
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
