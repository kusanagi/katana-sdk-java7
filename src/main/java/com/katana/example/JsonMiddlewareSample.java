package com.katana.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.katana.api.Error;
import com.katana.api.common.Request;
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
public class MiddlewareSample {

    public static void main(String[] args) {
        Callable<Request> requestCallable = getRequestCallable();
        Callable<Response> responseCallable = getResponseCallable();

        Middleware middleware = new Middleware(args);
        middleware.request(requestCallable);
        middleware.response(responseCallable);
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
                    boolean hasExtraPath = parts.length == 6;

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

    private static Callable<Response> getResponseCallable() {
        return new Callable<Response>() {
                @Override
                public Response run(Response response) {
    //                // logic ...
                    response.getHttpResponse().setHeader("Content-Type", "application/json");
                    Transport transport = response.getTransport();
                    List<Error> errors = new ArrayList<>();
                    Logger.log(response.getHttpResponse().getStatusCode() + "");
                    if (response.getHttpResponse().getStatusCode() > 500) {
                        Error error = new Error();
                        error.setCode(response.getHttpResponse().getStatusCode());
                        error.setMessage(response.getHttpResponse().getBody());
                        errors.add(error);
                    } else {
                        errors = transport.getErrors();
                    }
                    if (errors != null) {
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
