package com.katana.sdk.components;

import com.katana.sdk.callables.RequestCallable;
import com.katana.sdk.callables.ResponseCallable;

/**
 * Created by juan on 27/08/16.
 */
public class Middleware extends Component {
    /**
     * Initialize the component with the command line arguments
     *
     * @param args list of command line arguments
     * @throws IllegalArgumentException throws an IllegalArgumentException if any of the REQUIRED arguments is missing,
     *                                  if there is an invalid argument or if there are duplicated arguments
     */
    public Middleware(String[] args) {
        super(args);
    }

    public void runRequest(RequestCallable requestCallable){
        run(requestCallable);
    }

    public void runResponse(ResponseCallable responseCallable){
        run(responseCallable);
    }
}
