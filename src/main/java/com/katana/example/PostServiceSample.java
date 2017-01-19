package com.katana.example;

import com.katana.api.common.Action;
import com.katana.api.common.Param;
import com.katana.sdk.common.Callable;
import com.katana.sdk.components.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan on 13/01/17.
 */
public class PostServiceSample {

    private static List<Post> getData() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1, "First Post", 1));
        posts.add(new Post(2, "Second Post", 2));
        posts.add(new Post(3, "Third Post", 1));
        return posts;
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
                List<Post> posts = getData();

                action.setCollection(posts);
                action.setLink("self", "/0.1.0/posts");

                return action;
            }
        };
    }

    private static Callable<Action> getReadCallable() {
        return new Callable<Action>() {
            @Override
            public Action run(Action action) {
                // logic ...
                List<Post> posts = getData();

                int postId = (Integer) action.getParam("id").getValue();

                Post entity = null;
                for (Post post : posts) {
                    if (post.getId() == postId) {
                        entity = post;
                        break;
                    }
                }

                if (entity == null) {
                    action.error("User does not exist", 1, "404 Not Found");
                } else {
                    List<Post> responseList = new ArrayList<>();
                    responseList.add(entity);
                    action.setEntity(responseList);
                    action.setLink("self", "/0.1.0/posts/" + postId);

                    List<Param> commentsParams = new ArrayList<>();
                    commentsParams.add(new Param("post_id", postId, null, false));
                    action.call("comments", "0.1.0", "list", commentsParams, null, null);

//                    List<Param> usersParms = new ArrayList<>();
//                    usersParms.add(new Param("users", 2, null, false));
//                    action.call("users", "0.1.0", "list", usersParms, null, null);
                }

                return action;
            }
        };
    }
}
