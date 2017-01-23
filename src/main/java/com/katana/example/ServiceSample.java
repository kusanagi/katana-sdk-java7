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

    private static List<User> getData() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "James"));
        users.add(new User(2, "Jeronimo"));
        users.add(new User(3, "Fernando"));
        users.add(new User(4, "Ricardo"));
        users.add(new User(5, "Hugo"));
        return users;
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

    private static Callable<Action> getCreateCallable() {
        return new Callable<Action>() {
            @Override
            public Action run(Action action) {
                String name = (String) action.getParam("name").getValue();
                User entity = new User(6, name);

                action.setEntity(entity);
                action.setLink("self", "/v1/users/" + entity.getId());

                return action;
            }
        };
    }

    private static Callable<Action> getListCallable() {
        return new Callable<Action>() {
            @Override
            public Action run(Action action) {
                // logic ...
                List<User> users = getData();

                action.setCollection(users);
                action.setLink("self", "/v1/users");

                return action;
            }
        };
    }

    private static Callable<Action> getReadCallable() {
        return new Callable<Action>() {
            @Override
            public Action run(Action action) {
                // logic ...
                List<User> users = getData();

                int userId = (Integer) action.getParam("id").getValue();

                User entity = null;
                for (User user : users) {
                    if (user.getId() == userId) {
                        entity = user;
                        break;
                    }
                }

                if (entity == null) {
                    action.error("User does not exist", 1, "404 Not Found");
                } else {
                    action.setEntity(entity);
                    action.setLink("self", "/v1/users/" + userId);
                }

                return action;
            }
        };
    }

    private static Callable<Action> getReplaceCallable() {
        return new Callable<Action>() {
            @Override
            public Action run(Action action) {
                // logic ...
                List<User> users = getData();

                int userId = (Integer) action.getParam("id").getValue();
                String name = (String) action.getParam("name").getValue();

                User entity = null;
                for (User user : users) {
                    if (user.getId() == userId) {
                        entity = user;
                        break;
                    }
                }

                if (entity == null) {
                    action.error("User does not exist", 1, "404 Not Found");
                } else {
                    entity.setName(name);
                    action.setEntity(entity);
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
                List<User> users = getData();

                int userId = (Integer) action.getParam("id").getValue();
                String name = (String) action.getParam("name").getValue();

                User entity = null;
                for (User user : users) {
                    if (user.getId() == userId) {
                        entity = user;
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

                action.setEntity(entity);
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
                List<User> users = getData();

                int userId = (Integer) action.getParam("id").getValue();

                User entity = null;
                for (User user : users) {
                    if (user.getId() == userId) {
                        entity = user;
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
}
