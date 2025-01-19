package org.example.core;

public abstract class RequestHandler {
    private final RequestHandler nextHandler;

    protected RequestHandler(RequestHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handle(HttpContext context);

    protected void next(HttpContext context){
        nextHandler.handle(context);
    }
}
