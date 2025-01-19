package org.example.requestHandlers;

import org.example.core.HttpContext;
import org.example.core.HttpResponse;
import org.example.core.RequestHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EndpointExecutionHandler extends RequestHandler {
    public EndpointExecutionHandler(RequestHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handle(HttpContext context) {
        Object controller = context.getController();
        Method method = context.getRequestMethod();
        try {
            Object responseBody = method.invoke(controller);
            context.setResponse(HttpResponse.Ok(responseBody.toString()));
        } catch (IllegalAccessException | InvocationTargetException e) {
            context.setResponse(HttpResponse.ServerError(""));
        }
    }
}
