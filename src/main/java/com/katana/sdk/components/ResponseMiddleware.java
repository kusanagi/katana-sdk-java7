package com.katana.sdk.components;

import com.katana.api.Response;
import com.katana.api.Transport;

/**
 * Created by juan on 14/09/16.
 */
public class ResponseMiddleware extends Component<Response>{

    /**
     * Initialize the component with the command line arguments
     *
     * @param args list of command line arguments
     * @throws IllegalArgumentException throws an IllegalArgumentException if any of the REQUIRED arguments is missing,
     *                                  if there is an invalid argument or if there are duplicated arguments
     */
    ResponseMiddleware(String[] args) {
        super(args);
    }

    @Override
    protected Response getObjectMessage(Transport transport) {
        return null;
    }
}

