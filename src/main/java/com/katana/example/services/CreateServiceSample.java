package com.katana.example.services;

import com.katana.api.common.Action;
import com.katana.sdk.common.Callable;
import com.katana.sdk.components.Service;

/**
 * Created by juan on 20/10/16.
 */
public class CreateServiceSample {
    public static void main(String[] args) {
        Callable<Action> callable = new Callable<Action>() {
            @Override
            public Action run(Action action) {
                String name = action.getParam("query", "name").get("name");
                Record entity = new Record(6, name);

                action.setEntity(entity);
                action.setLink("self", "/v1/users/" + entity.getId());

                return action;
            }
        };

        Service service = new Service(args);
        service.runAction(callable);
    }
}
