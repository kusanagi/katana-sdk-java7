package com.katana.example;

import com.katana.api.common.Request;
import com.katana.sdk.common.Callable;
import com.katana.sdk.components.Middleware;

/**
 * Created by juan on 27/08/16.
 */
public class RequestMiddlewareSample {

    public static void main(String[] args) {
        Callable<Request> callable = new Callable<Request>() {
            @Override
            public Request run(Request request) {
                // logic ...
                return request;
            }
        };

        Middleware middleware = new Middleware(args);
        middleware.runRequest(callable);
    }

}
