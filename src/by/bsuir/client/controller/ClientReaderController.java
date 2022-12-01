package by.bsuir.client.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientReaderController extends Thread {
    private final ClientController client;

    public ClientReaderController(ClientController client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            var reader = new BufferedReader(new InputStreamReader(System.in));
            while (client.isRunning()) {
                var response = reader.readLine();
                client.sendMessage(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
