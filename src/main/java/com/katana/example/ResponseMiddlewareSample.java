package com.katana.example;

import com.katana.api.common.Response;
import com.katana.sdk.common.Callable;
import com.katana.sdk.components.Middleware;

/**
 * Created by juan on 27/08/16.
 */
public class ResponseMiddlewareSample {

    public static void main(String[] args) {
        Callable<Response> callable = new Callable<Response>() {
            @Override
            public Response run(Response response) {
                // logic ...
                return response;
            }
        };

        Middleware middleware = new Middleware(args);
        middleware.runResponse(callable);
    }
}
