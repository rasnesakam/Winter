package org.example.core;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class HttpResponse {
    private static final String protocol = "HTTP/1.1";
    private static final String server = "Winter/1.0-Alpha";
    private int statusCode;
    private String statusMessage;
    private HashMap<String, String> headers;
    private String body;

    private HttpResponse(){}

    private void setPredefinedHeaders() {
        if (headers == null)
            headers = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss zzz");
        headers.put("Server", server);
        headers.put("Date", formatter.format(ZonedDateTime.now(ZoneOffset.UTC)));
        headers.put("MIME-Version", "1.0");
        headers.put("Last-Modified", formatter.format(ZonedDateTime.now(ZoneOffset.UTC)));
        if (body != null){
            headers.put("Content-Length", Integer.toString(body.length() + 2));
        }
    }

    private HttpResponse(int statusCode, String statusMessage, HashMap<String, String> headers, String body) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.headers = headers;
        this.body = body;

        setPredefinedHeaders();
    }

    public static HttpResponse Ok(String body) {
        return new HttpResponse(200, "OK", null, body);
    }
    public static HttpResponse NotFound(String body) {
        return new HttpResponse(404, "OK", null, body);
    }
    public static HttpResponse ServerError(String body) {
        return new HttpResponse(500, "OK", null, body);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(protocol).append(" ").append(statusCode).append(" ").append(statusMessage).append("\n\r");
        if (headers != null) {
            for (String key : headers.keySet()) {
                sb.append(key).append(": ").append(headers.get(key)).append("\n\r");
            }
        }
        if (body != null) {
            sb.append("\n\r").append(body).append("\n\r");
        }
        return sb.toString();
    }
}
