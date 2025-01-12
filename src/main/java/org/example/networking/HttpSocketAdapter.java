package org.example.networking;

import org.example.core.HttpRequest;
import org.example.core.HttpResponse;
import org.example.core.Instantiator;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class HttpSocketAdapter implements Closeable {
    private final ServerSocket serverSocket;

    public HttpSocketAdapter(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public static class HttpSocketAdapterBuilder {
        private int port;

        public HttpSocketAdapterBuilder port(int port) {
            this.port = port;
            return this;
        }
        public HttpSocketAdapter build() throws IOException {
            return new HttpSocketAdapter(port);
        }
    }

    @Override
    public void close() throws IOException {
        serverSocket.close();
    }

    public void startReading() throws IOException {
        System.out.println("Accepting requests on port: " + serverSocket.getLocalPort());
        while (true) {
            try (Socket socket = serverSocket.accept()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                if (reader.ready()) {
                    HttpRequest request = Instantiator.getInstantiator().createHttpRequest(reader);

                    HttpResponse response = HttpResponse.Ok("<h1>Hello World</h1>");
                    writer.println(response);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
