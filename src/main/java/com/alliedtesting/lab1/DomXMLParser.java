package com.alliedtesting.lab1;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class DomXMLParser {

    private static final File inputFile = new File("employees.xml");
    private static final String skillType = "for communication";

    public static void main(String[] args) {

        System.out.println("*** All Employees ***\n");
        parseXmlFile(inputFile);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Scanner in = new Scanner(System.in);
        System.out.print("\n*** Data about employee with ID = ");
        String empId = in.next();
        System.out.print("\n");
        queryXmlFile_byEmpId(inputFile, empId);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n*** Who has skills " + skillType + " ***\n");
        queryXmlFile_bySkillType(inputFile, skillType);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("*** All Employees with New Department***\n");
        modifyXmlFile(inputFile);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print("\n*** Managers of employee with ID = ");
        empId = in.next();
        System.out.print("\n");
        queryXmlFile_findAllMngrs(inputFile, empId);

        in.close();
    }

    public static void parseXmlFile(File inputFile) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("All employees work at : " + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("employee");
            System.out.println("----------------------------\n");

            Node depId = null;
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getParentNode().getAttributes().getNamedItem("depId") != depId){
                    System.out.println(nNode.getParentNode().getAttributes().getNamedItem("depId") + " "
                            + nNode.getParentNode().getAttributes().getNamedItem("name"));
                }
                System.out.print("\n");
                depId = nNode.getParentNode().getAttributes().getNamedItem("depId");

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Employee id : "
                            + eElement.getAttribute("empId"));
                    System.out.println("First Name : "
                            + eElement
                            .getElementsByTagName("firstName")
                            .item(0)
                            .getTextContent());
                    System.out.println("Last Name : "
                            + eElement
                            .getElementsByTagName("lastName")
                            .item(0)
                            .getTextContent());
                    System.out.println("Date of birth : "
                            + eElement
                            .getElementsByTagName("birthDate")
                            .item(0)
                            .getTextContent());
                    System.out.println("Position : "
                            + eElement
                            .getElementsByTagName("position")
                            .item(0)
                            .getTextContent());
                    System.out.println("Manager ID : "
                            + eElement
                            .getElementsByTagName("managerId")
                            .item(0)
                            .getTextContent());

                    NodeList skillsList = eElement.getElementsByTagName("skills");

                    for (int count = 0; count < skillsList.getLength(); count++) {
                        Node node1 = skillsList.item(count);

                        if (node1.getNodeType() == node1.ELEMENT_NODE) {
                            Element skill = (Element) node1;
                            if (!skill.getAttribute("type").isEmpty()) {
                                System.out.print("skill type : ");
                                System.out.println(skill.getAttribute("type"));
                            }
                            System.out.print("skill name : ");
                            System.out.println(skill.getTextContent());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void queryXmlFile_byEmpId(File inputFile, String empId) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("employee");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    if (eElement.getAttribute("empId").equals(empId))
                    {
                        System.out.println("First Name : "
                                + eElement
                                .getElementsByTagName("firstName")
                                .item(0)
                                .getTextContent());
                        System.out.println("Last Name : "
                                + eElement
                                .getElementsByTagName("lastName")
                                .item(0)
                                .getTextContent());
                        System.out.println("Date of birth : "
                                + eElement
                                .getElementsByTagName("birthDate")
                                .item(0)
                                .getTextContent());
                        System.out.println("Position : "
                                + eElement
                                .getElementsByTagName("position")
                                .item(0)
                                .getTextContent());
                        System.out.println("Manager ID : "
                                + eElement
                                .getElementsByTagName("managerId")
                                .item(0)
                                .getTextContent());
                        NodeList skillsList = eElement.getElementsByTagName("skills");
                        for (int count = 0; count < skillsList.getLength(); count++) {
                            Node node1 = skillsList.item(count);
                            if (node1.getNodeType() == node1.ELEMENT_NODE) {
                                Element skill = (Element) node1;
                                if (!skill.getAttribute("type").isEmpty()) {
                                    System.out.print("skill type : ");
                                    System.out.println(skill.getAttribute("type"));
                                }
                                System.out.print("skill name : ");
                                System.out.println(skill.getTextContent());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void queryXmlFile_bySkillType(File inputFile, String skillType) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("employee");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    NodeList skillsList = eElement.getElementsByTagName("skills");
                    for (int count = 0; count < skillsList.getLength(); count++) {
                        Node node1 = skillsList.item(count);

                        if (node1.getNodeType() == node1.ELEMENT_NODE) {
                            Element skill = (Element) node1;
                            if (skill.getAttribute("type").equals(skillType))
                            {
                                System.out.println("Employee id : "
                                        + eElement.getAttribute("empId"));
                                System.out.println("First Name : "
                                        + eElement
                                        .getElementsByTagName("firstName")
                                        .item(0)
                                        .getTextContent());
                                System.out.println("Last Name : "
                                        + eElement
                                        .getElementsByTagName("lastName")
                                        .item(0)
                                        .getTextContent());
                                System.out.println("Date of birth : "
                                        + eElement
                                        .getElementsByTagName("birthDate")
                                        .item(0)
                                        .getTextContent());
                                System.out.println("Position : "
                                        + eElement
                                        .getElementsByTagName("position")
                                        .item(0)
                                        .getTextContent());
                                System.out.print("skill name : ");
                                System.out.println(skill.getTextContent());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void modifyXmlFile(File inputFile) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            Node company = doc.getFirstChild();

            //create new department
            Element department = doc.createElement("department");
            department.setAttribute("name", "Marketing");
            department.setAttribute("depId", "3");

            //create new employee
            Element employee = doc.createElement("employee");
            employee.setAttribute("empId", "301");

            //create new employee's last name
            Element lastName = doc.createElement("lastName");
            lastName.appendChild(doc.createTextNode("Waters"));
            employee.appendChild(lastName);

            //create new employee's first name
            Element firstName = doc.createElement("firstName");
            firstName.appendChild(doc.createTextNode("Roger"));
            employee.appendChild(firstName);

            //create new employee's birth date
            Element birthDate = doc.createElement("birthDate");
            birthDate.appendChild(doc.createTextNode("16.01.1977"));
            employee.appendChild(birthDate);

            //create new employee's position
            Element position = doc.createElement("position");
            position.appendChild(doc.createTextNode("Department Manager"));
            employee.appendChild(position);

            //create new employee's skills
            Element skills = doc.createElement("skills");

            Element skill1 = doc.createElement("skill");
            skill1.appendChild(doc.createTextNode("Knows all about new web design trends"));
            skills.appendChild(skill1);

            employee.appendChild(skills);

            //create new employee's manager ID
            Element managerId = doc.createElement("managerId");
            managerId.appendChild(doc.createTextNode("0"));
            employee.appendChild(managerId);

            department.appendChild(employee);
            company.appendChild(department);

            company.normalize();

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(inputFile);
            transformer.transform(source, result);

            System.out.println("All employees work at : " + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("employee");
            System.out.println("----------------------------\n");

            Node depId = null;

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getParentNode().getAttributes().getNamedItem("depId") != depId){
                    System.out.println(nNode.getParentNode().getAttributes().getNamedItem("depId") + " "
                            + nNode.getParentNode().getAttributes().getNamedItem("name"));
                }
                System.out.print("\n");
                depId = nNode.getParentNode().getAttributes().getNamedItem("depId");

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    System.out.println("First Name : "
                            + eElement
                            .getElementsByTagName("firstName")
                            .item(0)
                            .getTextContent());
                    System.out.println("Last Name : "
                            + eElement
                             .getElementsByTagName("lastName")
                             .item(0)
                             .getTextContent());
                    System.out.println("Date of birth : "
                            + eElement
                             .getElementsByTagName("birthDate")
                             .item(0)
                             .getTextContent());
                    System.out.println("Position : "
                             + eElement
                             .getElementsByTagName("position")
                             .item(0)
                             .getTextContent());
                    System.out.println("Manager ID : "
                            + eElement
                            .getElementsByTagName("managerId")
                            .item(0)
                            .getTextContent());
                    NodeList skillsList = eElement.getElementsByTagName("skills");
                    for (int count = 0; count < skillsList.getLength(); count++) {
                        Node node1 = skillsList.item(count);
                        if (node1.getNodeType() == node1.ELEMENT_NODE) {
                            Element skill = (Element) node1;
                            if (!skill.getAttribute("type").isEmpty()) {
                               System.out.print("skill type : ");
                               System.out.println(skill.getAttribute("type"));
                            }
                            System.out.print("skill name : ");
                            System.out.println(skill.getTextContent());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void queryXmlFile_findAllMngrs(File inputFile, String empId) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("employee");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    if (eElement.getAttribute("empId").equals(empId))
                    {
                        System.out.print("emp "
                                + eElement.getAttribute("empId"));
                        System.out.print(" : "
                                + eElement
                                .getElementsByTagName("firstName")
                                .item(0)
                                .getTextContent());
                        System.out.print(" "
                                + eElement
                                .getElementsByTagName("lastName")
                                .item(0)
                                .getTextContent());

                        String managerId = eElement.getElementsByTagName("managerId").item(0).getTextContent();
                        if (!managerId.equals("0")){
                            System.out.println(" - managerId = " + managerId);
                            queryXmlFile_findAllMngrs(inputFile, managerId);

                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
