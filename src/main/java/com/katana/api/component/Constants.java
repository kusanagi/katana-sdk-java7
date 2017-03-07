package com.katana.api.component;

/**
 * Created by juan on 1/10/16.
 */
public class Constants {

    public static final String VERSION_PATTERN = "[a-zA-Z0-9*.,_\\-]+";

    public static final String SERVICE = "service";
    public static final String MIDDLEWARE = "middleware";
    public static final String REQUEST_STRING = "request";
    public static final String WORKERS = "workers";
    public static final String TCP = "tcp";
    public static final String IPC = "ipc";

    public static final String WORKER_ENDPOINT = "ipc://workers";

    public static final int VOID_BYTE = 0x00;
    public static final int CALL_BYTE = 0x01;
    public static final int FILE_BYTE = 0x02;
    public static final int TRANSACTION_BYTE = 0x03;
    public static final int DOWNLOAD_BYTE = 0x04;

    //Args
    public static final String SHORT_FRAMEWORK_VERSION_ARG = "-f";
    public static final String FRAMEWORK_VERSION_ARG = "--framework-version";
    public static final String SHORT_COMPONENT_ARG = "-c";
    public static final String COMPONENT_ARG = "--component";
    public static final String SHORT_NAME_ARG = "-n";
    public static final String NAME_ARG = "--name";
    public static final String SHORT_VERSION_ARG = "-v";
    public static final String VERSION_ARG = "--version";
    public static final String SHORT_SOCKET_ARG = "-s";
    public static final String SOCKET_ARG = "--socket";
    public static final String SHORT_TCP_ARG = "-t";
    public static final String TCP_ARG = "--tcp";
    public static final String SHORT_VAR_ARG = "-V";
    public static final String VAR_ARG = "--var";
    public static final String SHORT_DISABLE_COMPACT_NAMES_ARG = "-d";
    public static final String DISABLE_COMPACT_NAMES_ARG = "--disable-compact-names";
    public static final String SHORT_DEBUG_ARG = "-D";
    public static final String DEBUG_ARG = "--debug";
    public static final String SHORT_CALLBACK_ARG = "-C";
    public static final String CALLBACK_ARG = "--callback";
    public static final String SHORT_QUIET_ARG = "-q";
    public static final String QUIET_ARG = "--quiet";

    //Patterns
    public static final String WORKER_ENDPOINT_STRING = "%s_%s";
    public static final String TCP_HOST_STRING = "%s://%s:%s";
    public static final String IPC_HOST_STRING = "%s://%s";
    public static final String KATANA_DEFAULT_SOCKET_STRING = "@katana-%s-%s-%s";

    //Status
    public static final String INTERNAL_SERVER_ERROR_STATUS = "500 Internal Server Error";

    //Data types
    public static final String TYPE_BOOLEAN = "boolean";
    public static final String TYPE_INTEGER = "integer";
    public static final String TYPE_FLOAT = "float";
    public static final String TYPE_STRING = "string";
    public static final String TYPE_ARRAY = "array";
    public static final String TYPE_OBJECT = "object";
    public static final String ARRAY_TYPE_CSV = "csv";

    public static final String RELATION_ONE = "one";

    public static final String KATANA_PROTOCOL_HTTP = "urn:katana:protocol:http";

    //Errors
    public static final String INVALID_FRAMEWORK_VERSION = "Invalid framework version %s";
    public static final String INVALID_COMPONENT_NAME = "Invalid componentName %s";
    public static final String INVALID_VERSION = "Invalid version %s";
    public static final String INVALID_VARIABLE = "Invalid variable %s";
    public static final String UNSUPPORTED_PARAMETER = "Unsupported parameter %s";
    public static final String FILE_SERVER_NOT_CONFIGURED = "File server not configured: \"%s\" (%s)";
    public static final String CALL_NOT_CONFIGURED_CONNECTION_TO_ACTION = "Call not configured, connection to action on \"%s\" (%s) aborted: \"%s\"";
    public static final String CANNOT_REFERENCE_LOCAL_FILE_CONNECTION_TO_ACTION = "Cannot reference local file, connection to action on \"%s\" (%s) aborted: \"%s\"";
    public static final String REMOTE_CALL_NOT_CONFIGURED_CONNECTION_TO_ACTION = "Remote call not configured, connection to action on [\"%s\"] \"%s\" (%s) aborted: \"%s\"";
    public static final String CANNOT_SET_A_RETURN_VALUE = "Cannot set a return value in \"%s\" (%s) for action: \"%s\"";
    public static final String INVALID_RETURN_TYPE = "Invalid return type given in %s %s for action: %s";
    public static final String DEFERRED_CALL_NOT_CONFIGURED = "Deferred call not configured, connection to action on \"%s\" (%s) aborted: \"%s\"";
    public static final String CANNOT_RESOLVE_ENTITY = "Cannot resolve entity for action: %s";
    public static final String CANNOT_RESOLVE_SCHEMA_FOR_PARAMETER = "Cannot resolve schema for parameter: %s";
    public static final String CANNOT_RESOLVE_SCHEMA_FOR_FILE = "Cannot resolve schema for file: %s";
    public static final String FILE_DOES_NOT_EXIST_IN_PATH = "File does not exist in path: %s";
    public static final String CANNOT_RESOLVE_SCHEMA_FOR_ACTION = "Cannot resolve schema for action: %s";

    private Constants() {
        // private constructor to block the instantiation of this object
    }

}
