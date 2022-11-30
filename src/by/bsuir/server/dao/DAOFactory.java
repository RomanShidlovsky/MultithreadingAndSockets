package by.bsuir.server.dao;

import by.bsuir.server.dao.impl.StudentFileDAOImpl;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final StudentFileDAO studentFileDAO = new StudentFileDAOImpl();

    private DAOFactory() {}

    public StudentFileDAO getStudentFileDAO() {
        return studentFileDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }
}
