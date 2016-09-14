package com.katana.example;

import com.katana.api.Action;
import com.katana.sdk.callables.ActionCallable;
import com.katana.sdk.callables.Callable;
import com.katana.sdk.components.Service;

/**
 * Created by juan on 27/08/16.
 */
public class ServiceSample {

    public static void main(String[] args){
        Callable<Action> callable = new Callable<Action>() {
            @Override
            public void run(Action action) {
                // logic ...
            }
        };

        Service service = new Service(args);
        service.runAction(callable);
    }
}
