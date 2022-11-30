package by.bsuir.server.dao.creator;

import by.bsuir.server.model.StudentFile;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class StudentFileCreator {
    private static final StudentFileCreator instance = new StudentFileCreator();

    private StudentFileCreator() {}

    public static StudentFileCreator getInstance() {
        return instance;
    }

    public StudentFile create(Node node) {
        int id = 0;
        String lastName = "";
        String firstName = "";

        NodeList nodes = node.getChildNodes();

        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                String value = nodes.item(i).getTextContent();
                switch (nodes.item(i).getNodeName()) {
                    case "id" -> id = Integer.parseInt(value);
                    case "firstName" -> firstName = value;
                    case "lastName" -> lastName = value;
                    default -> throw new IllegalArgumentException("Illegal field name");
                }
            }
        }

        return new StudentFile(id, firstName, lastName);
    }
}
