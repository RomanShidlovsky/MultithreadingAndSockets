package by.bsuir.client.controller;

import by.bsuir.server.controller.ServerController;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientController extends Thread {
    private PrintWriter writer;
    private boolean running = true;

    @Override
    public void run() {
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), ServerController.PORT);
            var inputController = new ClientReaderController(this);
            inputController.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            String request;
            while ((request = reader.readLine()) != null) {
                System.out.println(request);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        running = false;
    }

    public void sendMessage(String message) {
        writer.println(message);
        writer.flush();
    }

    public boolean isRunning() {
        return running;
    }
}
