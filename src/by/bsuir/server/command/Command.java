package by.bsuir.server.command;

public interface Command {
    String execute(Object caller, String request);
}
