package by.bsuir.server.command;

import by.bsuir.server.command.impl.*;

public class CommandFactory {
    private static final CommandFactory instance = new CommandFactory();

    private CommandFactory() { }

    public static CommandFactory getInstance() {
        return instance;
    }

    public Command getCommand(String request) {
        if (request == null) throw new IllegalArgumentException("No command");
        var arguments = request.split(" ");
        if (arguments.length < 1) throw new IllegalArgumentException("No command");
        return switch (arguments[0]) {
            case "AUTH" -> new AuthCommand();
            case "DISCONNECT" -> new DisconnectCommand();
            case "UPDATE" -> new UpdateCommand();
            case "VIEW" -> new ViewCommand();
            case "CREATE" -> new CreateCommand();
            default -> throw new IllegalArgumentException("No such command");
        };
    }
}
