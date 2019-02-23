package com.alliedtesting.lab1;

import java.io.File;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.*;

public class DomXMLParser {

    private static final File inputFile = new File("employees.xml");
    private static final String skillType = "for communication";

    public static void main(String[] args) {

        System.out.println("*** All Employees ***\n");
        parseXmlFile(inputFile);

        Scanner in = new Scanner(System.in);
        System.out.print("\n*** Data about employee with ID = ");
        String empId = in.next();
        in.close();
        System.out.print("\n");
        queryXmlFile_byEmpId(inputFile, empId);

        System.out.println("\n*** Who has skills " + skillType + " ***\n");
        queryXmlFile_bySkillType(inputFile, skillType);
    }

    public static void parseXmlFile(File inputFile) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element : " + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("employee");
            System.out.println("----------------------------\n");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("Current Element : " + nNode.getNodeName());

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
}
