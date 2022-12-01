package by.bsuir.server.service;

import by.bsuir.server.model.StudentFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public interface StudentFileService {
    StudentFile createFile(Node node);
    Element createNode(Document doc, StudentFile file);
    boolean containsFile(int id);
    boolean addFile(String firstName, String lastName);
    StudentFile[] getAllFiles();
    boolean updateFile(int id, String firstName, String lastName);
}
