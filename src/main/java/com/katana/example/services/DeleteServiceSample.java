package com.katana.example.services;

import com.katana.api.common.Action;
import com.katana.sdk.common.Callable;
import com.katana.sdk.components.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan on 20/10/16.
 */
public class DeleteServiceSample {
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

                int userId = Integer.parseInt(action.getParam("path", "id").get("id"));

                Record entity = null;
                for (Record record : records){
                    if (record.getId() == userId) {
                        entity = record;
                        break;
                    }
                }

                if (entity == null) {
                    action.error("User does not exist", 1, "404 Not Found");
                }

                return action;
            }
        };

        Service service = new Service(args);
        service.runAction(callable);
    }
}
