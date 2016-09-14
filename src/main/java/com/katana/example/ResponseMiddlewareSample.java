package com.katana.example;

import com.katana.api.Response;
import com.katana.sdk.callables.Callable;
import com.katana.sdk.callables.ResponseCallable;
import com.katana.sdk.components.Middleware;

/**
 * Created by juan on 27/08/16.
 */
public class ResponseMiddlewareSample {

    public static void main(String[] args) {
        Callable<Response> callable = new Callable<Response>() {
            @Override
            public void run(Response response) {
                // logic ...
            }
        };

        Middleware middleware = new Middleware(args);
        middleware.runResponse(callable);
    }
}
