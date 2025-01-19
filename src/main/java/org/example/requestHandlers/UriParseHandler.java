package org.example.requestHandlers;

import org.example.controller.SampleController;
import org.example.core.HttpContext;
import org.example.core.HttpResponse;
import org.example.core.Instantiator;
import org.example.core.RequestHandler;

import java.lang.reflect.Method;
import java.util.Optional;

public class UriParseHandler extends RequestHandler {
    public UriParseHandler(RequestHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handle(HttpContext context) {
        String uri = context.getRequest().getRequestURI();
        Optional<Method> optionalMethod = Instantiator
                .getInstantiator().getMethodFromUri(uri);
        if (optionalMethod.isPresent()) {
            SampleController controller = new SampleController();
            context.setRequestMethod(optionalMethod.get());
            context.setController(controller);
            this.next(context);
            return;
        }
        context.setResponse(HttpResponse.NotFound(""));
    }
}
