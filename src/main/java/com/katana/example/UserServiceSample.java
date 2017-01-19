package com.katana.example;

import com.katana.api.common.Action;
import com.katana.sdk.common.Callable;
import com.katana.sdk.components.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan on 13/01/17.
 */
public class UserServiceSample {

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
        service.action("read", getReadCallable());
        service.action("list", getListCallable());
        service.run();
    }

    private static Callable<Action> getListCallable() {
        return new Callable<Action>() {
            @Override
            public Action run(Action action) {
                // logic ...
                List<User> users = getData();

                action.setCollection(users);
                action.setLink("self", "/0.1.0/users");

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
                    List<User> responseList = new ArrayList<>();
                    responseList.add(entity);
                    action.setEntity(responseList);
                    action.setLink("self", "/0.1.0/users/" + userId);
                }

                return action;
            }
        };
    }
}
