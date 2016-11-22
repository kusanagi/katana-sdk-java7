package com.katana.example;

import com.katana.api.common.Request;
import com.katana.sdk.common.Callable;
import com.katana.sdk.components.Middleware;

/**
 * Created by juan on 27/08/16.
 */
public class RestMiddlewareSample {

    public static void main(String[] args) {
        Callable<Request> requestCallable = getRequestCallable();

        Middleware middleware = new Middleware(args);
        middleware.request(requestCallable);
        middleware.run();
    }

    private static Callable<Request> getRequestCallable() {
        return new Callable<Request>() {
            @Override
            public Request run(Request request) {
                // logic ...
                // /{version}/{service}/{extra}
                String[] parts = request.getHttpRequest().getUrlPath().split("/");
                request.setServiceVersion(parts[1]);
                request.setServiceName(parts[2]);
                boolean hasExtraPath = parts.length == 4 && !parts[3].isEmpty();

                String method = request.getHttpRequest().getMethod();

                String actionName = null;

                switch (method) {
                    case "GET":
                        actionName = hasExtraPath ? "read" : "list";
                        break;
                    case "POST":
                        actionName = "create";
                        break;
                    case "PUT":
                        actionName = "replace";
                        break;
                    case "PATCH":
                        actionName = "update";
                        break;
                    case "DELETE":
                        actionName = "delete";
                        break;
                }

                if (actionName != null) {
                    request.setActionName(actionName);
                }
                return request;
            }
        };
    }
}
