package com.katana.example.middlewares;

import com.katana.api.common.Request;
import com.katana.sdk.common.Callable;
import com.katana.sdk.common.Logger;
import com.katana.sdk.components.Middleware;

/**
 * Created by juan on 27/08/16.
 */
public class RestMiddlewareSample {

    public static void main(String[] args) {
        Callable<Request> callable = new Callable<Request>() {
            @Override
            public Request run(Request request) {
                // logic ...
                // /{version}/{service}/{extra}
                Logger.log(request.toString());
                String[] parts = request.getUrlPath().split("/");
                request.setServiceVersion(parts[3]);
                request.setServiceName(parts[4]);
                boolean hasExtraPath = parts.length == 6;

                String method = request.getMethod();

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
                Logger.log(request.toString());
                return request;
            }
        };

        Middleware middleware = new Middleware(args);
        middleware.runRequest(callable);
    }

}
