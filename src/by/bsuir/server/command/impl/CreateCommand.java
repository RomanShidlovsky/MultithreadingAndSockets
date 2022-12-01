package by.bsuir.server.command.impl;

import by.bsuir.server.command.Command;
import by.bsuir.server.model.Rights;
import by.bsuir.server.service.ServiceFactory;

public class CreateCommand implements Command {
    @Override
    public String execute(Object caller, String request) {
        var arguments = request.split(" ");
        if (arguments.length != 3) throw new IllegalArgumentException("CREATE invalid syntax");

        if (ServiceFactory.getInstance().getAuthService().getRights(caller) != Rights.ADMIN)
            return "Should be ADMIN.";

        ServiceFactory.getInstance().getStudentFileServiceInstance().addFile(arguments[1], arguments[2]);
        return "Success.";
    }
}
