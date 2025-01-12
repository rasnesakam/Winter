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
        try (HttpSocketAdapter adapter = new HttpSocketAdapter.HttpSocketAdapterBuilder().port(8080).build()){
            adapter.startReading();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}