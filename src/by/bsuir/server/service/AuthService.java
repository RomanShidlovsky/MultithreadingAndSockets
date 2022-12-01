package by.bsuir.server.service;

import by.bsuir.server.model.Rights;

public interface AuthService {
    Rights getRights(Object user);
    void setRights(Object user, Rights rights);
}
