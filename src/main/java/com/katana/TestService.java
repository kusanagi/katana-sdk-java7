package com.katana;

import com.katana.api.Action;
import com.katana.sdk.callables.ActionCallable;
import com.katana.sdk.components.Service;

/**
 * Created by juan on 27/08/16.
 */
public class TestService {

    public static void main(String[] args){
        ActionCallable actionCallable = new ActionCallable() {
            @Override
            public void run(Action action) {

            }
        };

        Service service = new Service(args);
        service.runAction(actionCallable);
    }
}
