package com.katana.example;

import com.katana.api.common.Action;
import com.katana.sdk.common.Callable;
import com.katana.sdk.components.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan on 20/10/16.
 */
public class ServiceSample {

    private static List<Record> getData() {
        List<Record> records = new ArrayList<>();
        records.add(new Record(1, "James"));
        records.add(new Record(2, "Jeronimo"));
        records.add(new Record(3, "Fernando"));
        records.add(new Record(4, "Ricardo"));
        records.add(new Record(5, "Hugo"));
        return records;
    }

    public static void main(String[] args) {
        Service service = new Service(args);
        service.action("create", getCreateCallable());
        service.action("read", getReadCallable());
        service.action("list", getListCallable());
        service.action("delete", getDeleteCallable());
        service.action("update", getUpdateCallable());
        service.action("replace", getReplaceCallable());
        service.run();
    }

    private static Callable<Action> getReplaceCallable() {
        return new Callable<Action>() {
            @Override
            public Action run(Action action) {
                // logic ...
                List<Record> records = getData();

                int userId = Integer.parseInt(action.getParam("id").getValue());
                String name = action.getParam("name").getValue();

                Record entity = null;
                for (Record record : records) {
                    if (record.getId() == userId) {
                        entity = record;
                        break;
                    }
                }

                if (entity == null) {
                    action.error("User does not exist", 1, "404 Not Found");
                } else {
                    entity.setName(name);
                    List<Record> responseList = new ArrayList<>();
                    responseList.add(entity);
                    action.setEntity(responseList);
                    action.setLink("self", "/v1/users/" + userId);
                }

                return action;
            }
        };
    }

    private static Callable<Action> getUpdateCallable() {
        return new Callable<Action>() {
            @Override
            public Action run(Action action) {
                // logic ...
                List<Record> records = getData();

                int userId = Integer.parseInt(action.getParam("id").getValue());
                String name = action.getParam("name").getValue();

                Record entity = null;
                for (Record record : records) {
                    if (record.getId() == userId) {
                        entity = record;
                        break;
                    }
                }

                if (entity == null) {
                    action.error("User does not exist", 1, "404 Not Found");
                } else {
                    if (!name.isEmpty()) {
                        entity.setName(name);
                    }
                }

                List<Record> responseList = new ArrayList<>();
                responseList.add(entity);
                action.setEntity(responseList);
                action.setLink("self", "/v1/users/" + userId);

                return action;
            }
        };
    }

    private static Callable<Action> getDeleteCallable() {
        return new Callable<Action>() {
            @Override
            public Action run(Action action) {
                // logic ...
                List<Record> records = getData();

                int userId = Integer.parseInt(action.getParam("id").getValue());

                Record entity = null;
                for (Record record : records) {
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
    }

    private static Callable<Action> getListCallable() {
        return new Callable<Action>() {
            @Override
            public Action run(Action action) {
                // logic ...
                List<Record> records = getData();

                action.setCollection(records);
                action.setLink("self", "/v1/users");

                return action;
            }
        };
    }

    private static Callable<Action> getCreateCallable() {
        return new Callable<Action>() {
            @Override
            public Action run(Action action) {
                String name = action.getParam("name").getValue();
                Record entity = new Record(6, name);

                List<Record> responseList = new ArrayList<>();
                responseList.add(entity);
                action.setEntity(responseList);
                action.setLink("self", "/v1/users/" + entity.getId());

                return action;
            }
        };
    }

    private static Callable<Action> getReadCallable() {
        return new Callable<Action>() {
            @Override
            public Action run(Action action) {
                // logic ...
                List<Record> records = getData();

                int userId = Integer.parseInt(action.getParam("id").getValue());

                Record entity = null;
                for (Record record : records) {
                    if (record.getId() == userId) {
                        entity = record;
                        break;
                    }
                }

                if (entity == null) {
                    action.error("User does not exist", 1, "404 Not Found");
                } else {
                    List<Record> responseList = new ArrayList<>();
                    responseList.add(entity);
                    action.setEntity(responseList);
                    action.setLink("self", "/v1/users/" + userId);
                }

                return action;
            }
        };
    }
}
