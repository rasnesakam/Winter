package org.example;

import org.example.core.HttpResponse;
import org.example.networking.HttpSocketAdapter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        HttpSocketAdapter.HttpSocketAdapterBuilder builder = new HttpSocketAdapter
                .HttpSocketAdapterBuilder();
        builder.port(8080);

        try (HttpSocketAdapter adapter = builder.build()){
            adapter.startReading();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}