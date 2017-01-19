package com.katana.example;

import com.katana.api.common.Action;
import com.katana.api.common.Param;
import com.katana.sdk.common.Callable;
import com.katana.sdk.components.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan on 13/01/17.
 */
public class CommentServiceSample {

    private static List<Comment> getData() {
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment(1, 1, "Hola mundo", 1));
        comments.add(new Comment(2, 1, "Some other comment", 1));
        comments.add(new Comment(3, 1, "The cake is a lie", 3));
        comments.add(new Comment(4, 2, "Commend for 2nd post", 2));
        return comments;
    }

    public static void main(String[] args) {
        Service service = new Service(args);
        service.action("list", getListCallable());
        service.run();
    }

    private static Callable<Action> getListCallable() {
        return new Callable<Action>() {
            @Override
            public Action run(Action action) {
                // logic ...
                List<Comment> comments = getData();

                Object postIdValue = action.getParam("post_id").getValue();
                int postId = postIdValue == null ? -1 : (Integer) postIdValue;

                List<Integer> users = new ArrayList<>();
                List<Comment> collection = new ArrayList<>();

                for (Comment comment : comments) {
                    if (postId == comment.getPostId()) {
                        if (users.contains(comment.getUserId())) {
                            users.add(comment.getUserId());
                        }
                        collection.add(comment);
                    }
                }

                action.setCollection(collection);
                action.setLink("self", "/0.1.0/comments");

                List<Param> params = new ArrayList<>();
                params.add(new Param("user", StringUtils.join(users, ","), null, false));
                action.call("users", "0.1.0", "list", params, null, null);

                return action;
            }
        };
    }
}
