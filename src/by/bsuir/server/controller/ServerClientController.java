package by.bsuir.server.controller;

import by.bsuir.server.command.CommandFactory;
import by.bsuir.server.command.impl.DisconnectCommand;

import java.io.*;
import java.net.Socket;

public class ServerClientController extends Thread {
    private final Socket socket;
    private final ServerController serverController;

    private BufferedReader bufferedReader;
    private PrintWriter writer;

    public ServerClientController(Socket socket, ServerController serverController) {
        this.socket = socket;
        this.serverController = serverController;
    }

    @Override
    public void run() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        sendMessage("""
                Commands:
                AUTH USER/ADMIN
                DISCONNECT
                VIEW
                CREATE (firstname) (lastname)
                EDIT (id) (firstname) (lastname)""");

        var running = true;
        do {
            try {
                var request = readMessage();
                if (request == null) {
                    break;
                }

                var command = CommandFactory.getInstance().getCommand(request);
                var response = command.execute(this, request);
                sendMessage(response);

                if (command instanceof DisconnectCommand) {
                    running = false;
                }
            } catch(IllegalArgumentException e) {
                e.printStackTrace();
                sendMessage(e.getMessage());
            }
        } while (running);

        serverController.disconnect(this);
    }

    private String readMessage() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendMessage(String message) {
        writer.println(message);
        writer.flush();
    }

    public Socket getSocket() {
        return socket;
    }
}
