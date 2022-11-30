package by.bsuir.server.dao;

import by.bsuir.server.model.StudentFile;

public interface StudentFileDAO {
    StudentFile[] getAll();
    boolean add(StudentFile file);
    StudentFile getById(int id);
    boolean updateById(int id, StudentFile file);
    boolean contains(int id);
}
