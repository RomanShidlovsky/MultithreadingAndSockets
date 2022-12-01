package by.bsuir.server.service;

import by.bsuir.server.service.impl.AuthServiceImpl;
import by.bsuir.server.service.impl.StudentFileServiceImpl;

public class ServiceFactory {
    public static final ServiceFactory instance = new ServiceFactory();
    private final StudentFileService studentFileService = new StudentFileServiceImpl();

    private final AuthService authService = new AuthServiceImpl();
    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public StudentFileService getStudentFileServiceInstance() {
        return studentFileService;
    }

    public AuthService getAuthService() {
        return authService;
    }
}
