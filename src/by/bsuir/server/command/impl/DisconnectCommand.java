package by.bsuir.server.command.impl;

import by.bsuir.server.command.Command;
import by.bsuir.server.model.Rights;
import by.bsuir.server.service.ServiceFactory;

public class DisconnectCommand implements Command {
    @Override
    public String execute(Object caller, String request) {
        ServiceFactory.getInstance().getAuthService().setRights(caller, Rights.UNAUTH);
        return "Disconnected!";
    }
}
