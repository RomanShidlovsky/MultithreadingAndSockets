package by.bsuir.server.command.impl;

import by.bsuir.server.command.Command;
import by.bsuir.server.model.Rights;
import by.bsuir.server.service.ServiceFactory;

public class UpdateCommand implements Command {
    @Override
    public String execute(Object caller, String request) {
        var arguments = request.split(" ");
        if (arguments.length != 4) throw new IllegalArgumentException("Invalid syntax EDIT");

        if (ServiceFactory.getInstance().getAuthService().getRights(caller) != Rights.ADMIN)
            return "Should be ADMIN";

        int id;
        try {
            id = Integer.parseInt(arguments[1]);
        } catch (NumberFormatException ignored) {
            return "Invalid id";
        }

        if (!ServiceFactory.getInstance().getStudentFileServiceInstance().containsFile(id))
            return "No such file";

        ServiceFactory.getInstance().getStudentFileServiceInstance().updateFile(id, arguments[2], arguments[3]);
        return "Success";
    }
}
