package com.katana.sdk.components;

import com.katana.api.Transport;
import com.katana.sdk.common.Option;
import com.katana.sdk.common.Callable;
import com.katana.utils.Utils;
import org.zeromq.ZMQ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by juan on 27/08/16.
 * Katana Java SDK
 */
public abstract class Component<T> {

    private static final String HAS_BEEN_SET_MORE_THAN_ONCE = "has been set more than once";

    private static final String IS_REQUIRED = "is required";

    private static final String IS_NOT_VALID = "is not valid";

    private static final Option[] APP_OPTIONS = new Option[]{
            new Option(new String[]{"-c", "--component"}, true, true, true),
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

    private String component;

    private boolean disableCompactName;

    private String name;

    private String version;

    private String action;

    private String platformVersion;

    private String socket;

    private String tcp;

    private List<String> var;

    private ZMQ.Socket responder;

    private ZMQ.Context context;

    private boolean debug;

    /**
     * Initialize the component with the command line arguments
     *
     * @param args list of command line arguments
     * @throws IllegalArgumentException throws an IllegalArgumentException if any of the REQUIRED arguments is missing,
     *                                  if there is an invalid argument or if there are duplicated arguments
     */
    public Component(String[] args) {
        var = new ArrayList<>();

        setArgs(args);

        if (this.tcp == null && this.socket == null) {
            generateDefaultSocket();
        }
    }

    private void generateDefaultSocket() {
        this.socket = "@katana-" + this.component + "-" + this.name + "-" + UUID.randomUUID().toString();
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
     * com.katana.sdk.components.Component variable getter
     *
     * @return return a the list of variable for the component
     */
    public List<String> getVar() {
        return var;
    }

    /**
     * com.katana.sdk.components.Component variable setter
     *
     * @param var list of variables to be used by the component
     */
    public void setVar(List<String> var) {
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

    /**
     * The method to run the component
     *
     * @param callable the logic to be used by the component
     */
    void run(Callable<T> callable) {
        startZQM();
        while (!Thread.currentThread().isInterrupted()) {
            byte[] messageBytes = responder.recv(0);
            String request = Arrays.toString(messageBytes);
            String response = processRequest(callable, request);
            responder.send(response);
        }
        stopZMQ();
    }

    private String processRequest(Callable<T> callable, String request) {
        Transport requestTransport = new Transport(request);
        T requestMessage = getObjectMessage(requestTransport);

        T responseMessage = callable.run(requestMessage);
        Transport responseTransport = getTransport(responseMessage);

        return responseTransport.getMessage();
    }

    private Transport getTransport(T response) {
        return new Transport();
    }

    protected abstract T getObjectMessage(Transport transport);

    private void startZQM() {
        context = ZMQ.context(1);
        responder = context.socket(ZMQ.REP);
        bindSocket();
    }

    private void bindSocket() {
        if (this.tcp != null) {
            responder.bind("tcp://" + this.tcp);
        } else {
            responder.bind("ipc://" + this.socket);
        }
    }

    private void stopZMQ() {
        responder.close();
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
                        Option option = APP_OPTIONS[j].clone();
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
                    this.component = option.getValue();
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
            }
        }
    }

}
