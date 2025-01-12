package org.example.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;

public class Instantiator {
    private static Instantiator instance;

    public static Instantiator getInstantiator() {
        if (instance == null)
            instance = new Instantiator();

        return instance;
    }

    public HttpRequest createHttpRequest(BufferedReader reader) throws IOException {
        HttpRequest.HttpRequestBuilder requestBuilder = new HttpRequest.HttpRequestBuilder();
        HashMap<String, String> headers = new HashMap<>();

        String line = reader.readLine();
        String[] requestLines = line.split(" ");
        requestBuilder
                .setRequestMethod(requestLines[0])
                .setRequestUri(requestLines[1])
                .setHttpProtocol(requestLines[2]);

        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            String[] headerKeyValues = line.split(":");
            headers.put(headerKeyValues[0].trim(), headerKeyValues[1].trim());
        }
        requestBuilder.setRequestHeaders(headers);
        if (headers.containsKey("Content-Length")) {
            int contentLength = Integer.parseInt(headers.get("Content-Length"));
            char[] content = new char[contentLength];
            if (reader.read(content, 0, contentLength) > 0){
                requestBuilder.setRequestBody(new String(content));
            }
        }
        return requestBuilder.build();
    }
}
