package com.katana.example;

import com.katana.api.common.Action;
import com.katana.sdk.common.Callable;
import com.katana.sdk.common.Logger;
import com.katana.sdk.components.Service;

/**
 * Created by juan on 27/08/16.
 */
public class ServiceSample {

    public static void main(String[] args) {
        Callable<Action> callable = new Callable<Action>() {
            @Override
            public Action run(Action action) {
                Logger.log("Callable run");
                // logic ...
                String[] records = {
                        "{'id':1, 'name':'James'}",
                        "{'id':2, 'name':'Jeronimo'}",
                        "{'id':3, 'name':'Fernando'}",
                        "{'id':4, 'name':'Ricardo'}",
                        "{'id':5, 'name':'Hugo'}"
                };

                action.setCollection(records);
                action.setLink("self", "/v1/users");

                return action;
            }
        };

        Service service = new Service(args);
        service.runAction(callable);
    }
}
