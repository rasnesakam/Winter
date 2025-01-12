package org.example.core;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private final Map<String, String> requestHeaders;
    private final String requestURI;
    private final String requestMethod;
    private final String requestBody;
    private final String HttpProtocol;

    public static class HttpRequestBuilder {

        private Map<String, String> requestHeaders;
        private String requestURI;
        private String requestMethod;
        private String requestBody;
        private String HttpProtocol;

        public HttpRequestBuilder setRequestUri(String uri) {
            requestURI = uri;
            return this;
        }
        public HttpRequestBuilder setRequestMethod(String method) {
            requestMethod = method;
            return this;
        }
        public HttpRequestBuilder setRequestBody(String requestBody) {
            this.requestBody = requestBody;
            return this;
        }
        public HttpRequestBuilder setHttpProtocol(String httpProtocol) {
            HttpProtocol = httpProtocol;
            return this;
        }
        public HttpRequestBuilder setRequestHeaders(Map<String, String> requestHeaders) {
            this.requestHeaders = requestHeaders;
            return this;
        }
        public HttpRequest build() {
            return new HttpRequest(requestHeaders, requestURI, requestMethod, requestBody, HttpProtocol);
        }
    }

    private HttpRequest(Map<String, String> requestHeaders, String requestURI, String requestMethod, String requestBody, String httpProtocol) {
        this.requestHeaders = requestHeaders;
        this.requestURI = requestURI;
        this.requestMethod = requestMethod;
        this.requestBody = requestBody;
        HttpProtocol = httpProtocol;
    }

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public String getHttpProtocol() {
        return HttpProtocol;
    }
}
