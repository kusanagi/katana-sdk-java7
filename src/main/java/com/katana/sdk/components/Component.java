package com.katana.sdk.components;

import com.katana.api.commands.common.CommandPayload;
import com.katana.api.common.Api;
import com.katana.api.replies.CommandReplyResult;
import com.katana.sdk.common.*;
import com.katana.utils.Utils;
import org.zeromq.ZMQ;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by juan on 27/08/16.
 * Katana Java SDK
 */

/**
 *
 * @param <T>
 * @param <S>
 */
public abstract class Component<T extends Api, S extends CommandReplyResult> implements ComponentWorker.WorkerListener<T> {

    private static final String HAS_BEEN_SET_MORE_THAN_ONCE = "has been set more than once";

    private static final String IS_REQUIRED = "is required";

    private static final String IS_NOT_VALID = "is not valid";

    private static final Option[] APP_OPTIONS = new Option[]{
            new Option(new String[]{"-c", "--componentName"}, true, true, true),
            new Option(new String[]{"-d", "--disable-compact-names"}, true, false, false),
            new Option(new String[]{"-n", "--name"}, true, true, true),
            new Option(new String[]{"-v", "--version"}, true, true, true),
            new Option(new String[]{"-a", "--action"}, true, true, true),
            new Option(new String[]{"-p", "--platform-version"}, true, true, true),
            new Option(new String[]{"-s", "--socket"}, true, false, true),
            new Option(new String[]{"-t", "--tcp"}, true, false, true),
            new Option(new String[]{"-D", "--debug"}, true, false, false),
            new Option(new String[]{"-V", "--var"}, false, false, true),
    };

    private final String workerEnpoint;

    private String componentName;

    private boolean disableCompactName;

    private String name;

    private String version;

    private String action;

    private String platformVersion;

    private String socket;

    private String tcp;

    private boolean debug;

    private List<String> var;

    private ZMQ.Socket router;

    private ZMQ.Context context;

    private Serializer serializer;

    private ZMQ.Socket dealer;

    /**
     * Initialize the componentName with the command line arguments
     *
     * @param args list of command line arguments
     * @throws IllegalArgumentException throws an IllegalArgumentException if any of the REQUIRED arguments is missing,
     *                                  if there is an invalid argument or if there are duplicated arguments
     */
    Component(String[] args) {
        this.var = new ArrayList<>();
        this.serializer = new MessagePackSerializer();

        setArgs(args);

        if (this.tcp == null && this.socket == null) {
            generateDefaultSocket();
        }

        if (isDebug()) {
            Logger.activate();
        }

        this.workerEnpoint = Constants.WORKER_ENDPOINT + "_" + UUID.randomUUID().toString();

        Logger.log(toString());
    }

    private void generateDefaultSocket() {
        this.socket = "@katana-" + this.componentName + "-" + this.name + "-" + UUID.randomUUID().toString();
    }

    /**
     * @return
     */
    public String getComponentName() {
        return componentName;
    }

    /**
     * @param componentName
     */
    public void setComponentName(String componentName) {
        this.componentName = componentName;
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
     * @return
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action
     */
    public void setAction(String action) {
        this.action = action;
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
     * com.katana.sdk.components.Component variable getter
     *
     * @return return a the list of variable for the componentName
     */
    public List<String> getVar() {
        return var;
    }

    /**
     * com.katana.sdk.components.Component variable setter
     *
     * @param var list of variables to be used by the componentName
     */
    public void setVar(List<String> var) {
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

    /**
     * The method to run the componentName
     *
     * @param callable the logic to be used by the componentName
     */
    public void run(Callable<T> callable) {
        Logger.log("Component run");

        startSocket();
        Logger.log("Socket started");

        setWorkers(callable);
        Logger.log("Component workers are running!");

        ZMQ.proxy(router, dealer, null);
        Logger.log("ZMQ proxy set");
    }

    /**
     *
     * @param commandBytes
     * @param callable
     * @return
     */
    @Override
    public byte[] onRequestReceived(byte[] commandBytes, Callable<T> callable) {
        CommandPayload<T> command = serializer.read(commandBytes, getCommandPayloadClass());
        S commandReply = processRequest(callable, command);
        Logger.log(commandReply.toString());
        return serializer.write(commandReply);
    }

    /**
     *
     * @return
     */
    protected abstract Class<? extends CommandPayload<T>> getCommandPayloadClass();

    /**
     *
     * @param command
     */
    protected void setBaseCommandAttrs(T command) {
        command.setName(this.getName());
        command.setVersion(this.getVersion());
        command.setPlatformVersion(this.getPlatformVersion());
        command.setDebug(this.isDebug());
//        command.setVariables(this.getVar());
    }

    /**
     *
     * @param response
     * @return
     */
    protected abstract S getCommandReplyPayload(T response);

    /**
     *
     * @param response
     * @return
     */
    protected abstract CommandReplyResult getReply(T response);

    private void setWorkers(Callable<T> callable) {
        ComponentWorker<T> componentWorker = new ComponentWorker<>(callable, workerEnpoint);
        componentWorker.setWorkerListener(this);
        componentWorker.start();
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
            Logger.log("Router binded to tcp://127.0.0.1:" + this.tcp);
        } else {
            router.bind("ipc://" + this.socket);
            Logger.log("Router binded to ipc://" + this.socket);
        }

        dealer.bind(this.workerEnpoint);
        Logger.log("Dealer binded to " + this.workerEnpoint);
    }

    private S processRequest(Callable<T> callable, CommandPayload<T> commandPayload) {
        T command = commandPayload.getCommand().getArgument();
        setBaseCommandAttrs(command);
        Logger.log(commandPayload.toString());
        callable.run(command);
        return getCommandReplyPayload(command);
    }

    private void stopSocket() {
        dealer.close();
        router.close();
        context.term();
    }

    private void setArgs(String[] args) throws IllegalArgumentException {
        List<Option> options = new ArrayList<>();
        int[] optionCounts = new int[APP_OPTIONS.length];

        extractOptions(args, options, optionCounts);
        validateConstrains(optionCounts);
        setMembers(options);
    }

    private void extractOptions(String[] args, List<Option> options, int[] optionCounts) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].charAt(0) == '-') {
                boolean exist = false;
                for (int j = 0; j < APP_OPTIONS.length && !exist; j++) {
                    if (Utils.contain(APP_OPTIONS[j].getNames(), args[i])) {
                        Option option = new Option(APP_OPTIONS[j]);
                        if (APP_OPTIONS[j].isHasValue()) {
                            i++;
                            option.setValue(args[i]);
                        }
                        options.add(option);

                        optionCounts[j]++;
                        exist = true;
                    }
                }
                if (!exist) {
                    throw new IllegalArgumentException(args[i] + " " + IS_NOT_VALID);
                }
            } else {
                throw new IllegalArgumentException(args[i] + " " + IS_NOT_VALID);
            }
        }
    }

    private void validateConstrains(int[] optionCounts) {
        for (int j = 0; j < APP_OPTIONS.length; j++) {
            if (APP_OPTIONS[j].isRequired() && optionCounts[j] == 0) {
                throw new IllegalArgumentException(getOptionName(APP_OPTIONS[j]) + " " + IS_REQUIRED);
            }

            if (APP_OPTIONS[j].isUnique() && optionCounts[j] > 1) {
                throw new IllegalArgumentException(getOptionName(APP_OPTIONS[j]) + " " + HAS_BEEN_SET_MORE_THAN_ONCE);
            }
        }
    }

    private String getOptionName(Option appOption) {
        return appOption.getNames()[0] + " or " + appOption.getNames()[1];
    }

    private void setMembers(List<Option> options) {
        for (Option option : options) {
            switch (option.getNames()[0]) {
                case "-c":
                    this.componentName = option.getValue();
                    break;
                case "-d":
                    this.disableCompactName = true;
                    break;
                case "-n":
                    this.name = option.getValue();
                    break;
                case "-v":
                    this.version = option.getValue();
                    break;
                case "-a":
                    this.action = option.getValue();
                    break;
                case "-p":
                    this.platformVersion = option.getValue();
                    break;
                case "-s":
                    this.socket = option.getValue();
                    break;
                case "-t":
                    this.tcp = option.getValue();
                    break;
                case "-D":
                    this.debug = true;
                    break;
                case "-V":
                    this.var.add(option.getValue());
                    break;
                default:
                    Logger.log("Unsupported parameter " + option.getNames()[0]);
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
                ", action='" + action + '\'' +
                ", platformVersion='" + platformVersion + '\'' +
                ", socket='" + socket + '\'' +
                ", tcp='" + tcp + '\'' +
                ", debug=" + debug +
                ", var=" + var +
                '}';
    }
}
