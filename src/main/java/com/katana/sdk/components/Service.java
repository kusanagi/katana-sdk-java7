package com.katana.sdk.components;

import com.katana.api.Action;
import com.katana.api.Transport;
import com.katana.sdk.common.Callable;

/**
 * Created by juan on 27/08/16.
 */
public class Service extends Component<Action> {

    /**
     * Initialize the component with the command line arguments
     *
     * @param args list of command line arguments
     * @throws IllegalArgumentException throws an IllegalArgumentException if any of the REQUIRED arguments is missing,
     *                                  if there is an invalid argument or if there are duplicated arguments
     */
    public Service(String[] args) {
        super(args);
    }

    @Override
    protected Action getObjectMessage(Transport transport) {
        return null;
    }

    public void runAction(Callable<Action> callable){
        run(callable);
    }

}
