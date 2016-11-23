package com.katana.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.katana.api.common.Error;
import com.katana.api.common.Response;
import com.katana.api.common.Transport;
import com.katana.sdk.common.Callable;
import com.katana.sdk.common.Logger;
import com.katana.sdk.components.Middleware;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan on 27/08/16.
 */
public class JsonMiddlewareSample {

    public static void main(String[] args) {
        Middleware middleware = new Middleware(args);
        middleware.response(getResponseCallable());
        middleware.run();
    }

    private static Callable<Response> getResponseCallable() {
        return new Callable<Response>() {
            @Override
            public Response run(Response response) {
                //                // logic ...
                response.getHttpResponse().setHeader("Content-Type", "application/json");
                Transport transport = response.getTransport();
                List<Error> errors = new ArrayList<>();
                if (response.getHttpResponse().getStatusCode() > 500) {
                    Error error = new Error();
                    error.setCode(response.getHttpResponse().getStatusCode());
                    error.setMessage(response.getHttpResponse().getBody());
                    errors.add(error);
                    response.getHttpResponse().setBody(errors.toString());
                } else {
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        response.getHttpResponse().setBody(transport.getData() == null ? "" : mapper.writeValueAsString(transport.getData()));
                    } catch (JsonProcessingException e) {
                        Logger.log(e);
                    }
                }
                return response;
            }
        };
    }

}
