package com.katana.example.services;

import com.katana.api.common.Action;
import com.katana.sdk.common.Callable;
import com.katana.sdk.components.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan on 27/08/16.
 */
public class ListServiceSample {

    public static void main(String[] args) {
        Callable<Action> callable = new Callable<Action>() {
            @Override
            public Action run(Action action) {
                // logic ...
                List<Record> records = new ArrayList<>();
                records.add(new Record(1, "James"));
                records.add(new Record(2, "Jeronimo"));
                records.add(new Record(3, "Fernando"));
                records.add(new Record(4, "Ricardo"));
                records.add(new Record(5, "Hugo"));

                action.setCollection(records);
                action.setLink("self", "/v1/users");

                return action;
            }
        };

        Service service = new Service(args);
        service.runAction(callable);
    }

}
