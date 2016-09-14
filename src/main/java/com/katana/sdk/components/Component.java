package com.katana.sdk.components;

import com.katana.api.Transport;
import com.katana.sdk.common.Option;
import com.katana.sdk.callables.Callable;
import com.katana.utils.Utils;
import org.zeromq.ZMQ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by juan on 27/08/16.
 * Katana Java SDK
 */
public abstract class Component<T> {

    private static final String HAS_BEEN_SET_MORE_THAN_ONCE = "has been set more than once";

    private static final String IS_REQUIRED = "is required";

    private static final String IS_NOT_VALID = "is not valid";

    private static final Option[] APP_OPTIONS = new Option[]{
            new Option(new String[]{"-n", "--name"}, true, true, true),
            new Option(new String[]{"-v", "--version"}, true, true, true),
            new Option(new String[]{"-p", "--platform-version"}, true, true, true),
            new Option(new String[]{"-s", "--socket"}, true, false, true),
            new Option(new String[]{"-D", "--debug"}, true, false, false),
            new Option(new String[]{"-V", "--var"}, false, false, true),
    };

    private String name;

    private String version;

    private String platformVersion;

    private String socket;

    private List<String> var;

    protected Callable callable;
    private ZMQ.Socket responder;
    private ZMQ.Context context;

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
    }

    private boolean debug;

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
        this.callable = callable;

        startZQM();
        while (!Thread.currentThread().isInterrupted()) {
            byte[] request = responder.recv(0);
            String message = getMessageAsString(request);
            Transport transport = getTransport(message);
            callable.run(getUserlandMessage(transport));
        }
        stopZMQ();
    }

    protected abstract T getUserlandMessage(Transport transport);

    private void startZQM() {
        context = ZMQ.context(1);
        responder = context.socket(ZMQ.REP);
        responder.bind("ipc://" + this.socket);
    }

    private void stopZMQ() {
        responder.close();
        context.term();
    }

    private Transport getTransport(String message) {
        return new Transport(message);
    }

    private String getMessageAsString(byte[] message) {
        return Arrays.toString(message);
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
