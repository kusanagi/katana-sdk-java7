package com.katana.example;

import com.katana.api.Request;
import com.katana.sdk.callables.Callable;
import com.katana.sdk.callables.RequestCallable;
import com.katana.sdk.components.Middleware;

/**
 * Created by juan on 27/08/16.
 */
public class RequestMiddlewareSample {

    public static void main(String[] args) {
        Callable<Request> callable = new Callable<Request>() {
            @Override
            public void run(Request request) {
                // logic ...
            }
        };

        Middleware middleware = new Middleware(args);
        middleware.runRequest(callable);
    }

}
