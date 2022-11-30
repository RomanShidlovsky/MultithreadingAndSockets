package by.bsuir.server.dao.impl;

import by.bsuir.server.dao.StudentFileDAO;
import by.bsuir.server.dao.creator.StudentFileCreator;
import by.bsuir.server.model.StudentFile;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class StudentFileDAOImpl implements StudentFileDAO {
    private static final String RESOURCES_PATH = "./src/by/bsuir/server/resources/students_files.xml";

    private final Map<Integer, StudentFile> studentFiles;
    private final ReadWriteLock lock;

    public StudentFileDAOImpl() {
        studentFiles = new HashMap<>();
        lock = new ReentrantReadWriteLock();
        LoadFiles();
    }

    private void LoadFiles() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(RESOURCES_PATH));
            document.getDocumentElement().normalize();
            NodeList nodes = document.getDocumentElement().getChildNodes();

            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    StudentFileCreator creator = StudentFileCreator.getInstance();
                    StudentFile file = creator.create(node);
                    studentFiles.put(file.getId(), file);
                }
            }
        } catch (Exception e) { }
    }

    @Override
    public boolean contains(int id) {
        return  studentFiles.containsKey(id);
    }

    @Override
    public StudentFile[] getAll() {
        try {
            lock.readLock().lock();
            return studentFiles.values().toArray(new StudentFile[0]);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public boolean add(StudentFile file)
    {
        boolean result = true;
        try {
            lock.writeLock().lock();
            file.setId(studentFiles.keySet().stream().max(Comparator.comparingInt(a -> a)).get() + 1);
            studentFiles.put(file.getId(), file);
            // update xml
        } catch (Exception e) {
            result = false;
        } finally {
            lock.writeLock().unlock();
        }
        return result;
    }

    @Override
    public StudentFile getById(int id) {
        StudentFile result = null;
        try {
            lock.readLock().lock();
            result = studentFiles.get(id);
        } finally {
            lock.readLock().unlock();
        }
        return result;
    }

    @Override
    public boolean updateById(int id, StudentFile file) {
        boolean result = true;
        try {
            lock.writeLock().lock();
            file.setId(id);
            studentFiles.put(id, file);
            // update xml
        } catch (Exception e) {
            result = false;
        }finally {
            lock.writeLock().unlock();
        }
        return result;
    }


}