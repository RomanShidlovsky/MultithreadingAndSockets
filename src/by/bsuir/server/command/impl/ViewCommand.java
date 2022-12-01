package by.bsuir.server.command.impl;

import by.bsuir.server.command.Command;
import by.bsuir.server.model.Rights;
import by.bsuir.server.model.StudentFile;
import by.bsuir.server.service.ServiceFactory;

public class ViewCommand implements Command {
    @Override
    public String execute(Object caller, String request){
        if (ServiceFactory.getInstance().getAuthService().getRights(caller) == Rights.UNAUTH)
            return "Should be authenticated";

        var files = ServiceFactory.getInstance().getStudentFileServiceInstance().getAllFiles();
        return toOutput(files);
    }

    private static String toOutput(StudentFile[] files) {
        var resultBuilder = new StringBuilder();
        resultBuilder.append("[\n");
        for (var file : files) {
            resultBuilder.append("\t").append(file.toString()).append("\n");
        }
        resultBuilder.append("]");
        return resultBuilder.toString();
    }
}
