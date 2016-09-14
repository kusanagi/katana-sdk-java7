package com.katana.sdk.components;

import com.katana.api.Request;
import com.katana.api.Transport;
import com.katana.sdk.callables.Callable;
import com.sun.org.apache.xml.internal.security.transforms.TransformationException;

/**
 * Created by juan on 14/09/16.
 */
public class RequestMiddleware extends Component<Request>{

    /**
     * Initialize the component with the command line arguments
     *
     * @param args list of command line arguments
     * @throws IllegalArgumentException throws an IllegalArgumentException if any of the REQUIRED arguments is missing,
     *                                  if there is an invalid argument or if there are duplicated arguments
     */
    public RequestMiddleware(String[] args) {
        super(args);
    }

    @Override
    protected Request getUserlandMessage(Transport transport) {
        return null;
    }
}
