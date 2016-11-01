package com.katana.sdk.components;

import com.katana.api.common.Request;
import com.katana.api.common.Response;
import com.katana.sdk.common.Callable;

/**
 * Created by juan on 27/08/16.
 */
public class Middleware {

    private final String[] args;

    public Middleware(String[] args) {
        this.args = args;
    }

    /**
     *
     * @param callable
     */
    public void runRequest(Callable<Request> callable) {
        RequestMiddleware middleware = new RequestMiddleware(args);
        middleware.run(callable);
    }

    /**
     *
     * @param callable
     */
    public void runResponse(Callable<Response> callable) {
        ResponseMiddleware middleware = new ResponseMiddleware(args);
        middleware.run(callable);
    }
}
