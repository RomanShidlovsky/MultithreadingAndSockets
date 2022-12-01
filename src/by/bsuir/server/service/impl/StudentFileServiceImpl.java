package by.bsuir.server.service.impl;

import by.bsuir.server.dao.DAOFactory;
import by.bsuir.server.dao.StudentFileDAO;
import by.bsuir.server.dao.creator.StudentFileCreator;
import by.bsuir.server.model.StudentFile;
import by.bsuir.server.service.StudentFileService;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class StudentFileServiceImpl implements StudentFileService {
    private final StudentFileCreator creator = StudentFileCreator.getInstance();
    private final StudentFileDAO studentFileDAO = DAOFactory.getInstance().getStudentFileDAO();

    @Override
    public StudentFile createFile(Node node) {
        return creator.create(node);
    }

    @Override
    public Element createNode(Document doc, StudentFile file) {
        return creator.createNode(doc, file);
    }

    @Override
    public boolean containsFile(int id) {
        return studentFileDAO.contains(id);
    }

    @Override
    public boolean addFile(StudentFile file) {
        return studentFileDAO.add(file);
    }

    @Override
    public StudentFile[] getAllFiles() {
        return studentFileDAO.getAll();
    }

    @Override
    public boolean updateFile(int id, StudentFile file) {
        return studentFileDAO.updateById(id, file);
    }
}
