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
    public static void main(String[] args) {
        Callable<Action> callable = getCreateCallable();
        Callable<Action> listCallable = getListCallable();
        Callable<Action> readCallable = getReadCallable();
        Callable<Action> deleteCallable = getDeleteCallable();
        Callable<Action> updateCallable = getUpdateCallable();
        Callable<Action> replaceCallable = getReplaceCallable();

        Service service = new Service(args);
        service.action("create", callable);
        service.action("read", readCallable);
        service.action("list", listCallable);
        service.action("delete", deleteCallable);
        service.action("update", updateCallable);
        service.action("replace", replaceCallable);
        service.run();
    }

    private static Callable<Action> getReplaceCallable() {
        return new Callable<Action>() {
                @Override
                public Action run(Action action) {
                    // logic ...
                    List<Record> records = new ArrayList<>();
                    records.add(new Record(1, "James"));
                    records.add(new Record(2, "Jeronimo"));
                    records.add(new Record(3, "Fernando"));
                    records.add(new Record(4, "Ricardo"));
                    records.add(new Record(5, "Hugo"));

                    int userId = Integer.parseInt(action.getParam("p", "id").get("v"));
                    String name = action.getParam("q", "name").get("v");

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
                    List<Record> records = new ArrayList<>();
                    records.add(new Record(1, "James"));
                    records.add(new Record(2, "Jeronimo"));
                    records.add(new Record(3, "Fernando"));
                    records.add(new Record(4, "Ricardo"));
                    records.add(new Record(5, "Hugo"));

                    int userId = Integer.parseInt(action.getParam("p", "id").get("v"));
                    String name = action.getParam("q", "name").get("v");

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
                    List<Record> records = new ArrayList<>();
                    records.add(new Record(1, "James"));
                    records.add(new Record(2, "Jeronimo"));
                    records.add(new Record(3, "Fernando"));
                    records.add(new Record(4, "Ricardo"));
                    records.add(new Record(5, "Hugo"));

                    int userId = Integer.parseInt(action.getParam("p", "id").get("v"));

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
    }

    private static Callable<Action> getCreateCallable() {
        return new Callable<Action>() {
                @Override
                public Action run(Action action) {
                    String name = action.getParam("q", "name").get("v");
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
                    List<Record> records = new ArrayList<>();
                    records.add(new Record(1, "James"));
                    records.add(new Record(2, "Jeronimo"));
                    records.add(new Record(3, "Fernando"));
                    records.add(new Record(4, "Ricardo"));
                    records.add(new Record(5, "Hugo"));

                    int userId = Integer.parseInt(action.getParam("p", "id").get("v"));

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
