package com.alliedtesting.lab1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


public class XmlXPathParser {

    private static final File inputFile = new File("employees.xml");

    public static void main(String[] args) {

    //    System.out.println("*** Employee's (id=101) Data ***\n");
    //    parseXmlFile(inputFile);

        Scanner in = new Scanner(System.in);
        System.out.print("\n*** Data about employee with ID = ");
        String empId = in.next();
        System.out.println("Want to know its ");
        String tagName = in.next();
        System.out.print("\n");
        checkTagPresence(inputFile, empId, tagName);

        System.out.print("\n*** Does node ");
        tagName = in.next();
        System.out.println(" has any children?");
        System.out.print("\n");
        checkTagChildren(inputFile, tagName);

        System.out.print("\n*** The values of tag: ");
        tagName = in.next();
        System.out.print("\n");
        getTagValues(inputFile, tagName);

        in.close();
    }

    public static void checkTagPresence(File inputFile, String empId, String tagName) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;

            dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            XPath xPath =  XPathFactory.newInstance().newXPath();

            String expression = "/company/department/employee[@empId = '"+empId+"']";
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
                    doc, XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Employee ID = "
                            + eElement.getAttribute("empId"));
                    try {
                        if (!eElement.getElementsByTagName(tagName).item(0).getTextContent().equals("")) {
                            System.out.println(eElement
                                    .getElementsByTagName(tagName)
                                    .item(0).getNodeName() + " : "
                                    + eElement
                                    .getElementsByTagName(tagName)
                                    .item(0)
                                    .getTextContent());
                        }
                    } catch (NullPointerException e) {
                        System.out.println("Employee's " + tagName + " is undefined ");
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    public static void checkTagChildren(File inputFile, String tagName) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;

            dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            XPath xPath =  XPathFactory.newInstance().newXPath();

            String expression = "/company/department/employee";
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
                    doc, XPathConstants.NODESET);

            List<String> nodeNamesList = new ArrayList<>();

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node nNode = nodeList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    NodeList tagsList = eElement.getElementsByTagName(tagName).item(0).getChildNodes();

                    for (int count = 0; count < tagsList.getLength(); count++) {

                        Node node1 = tagsList.item(count);

                        if (!nodeNamesList.contains(node1.getNodeName())){

                            if (tagsList.getLength() == 1 && node1.getNodeType() == node1.TEXT_NODE) //even tag with nested tags has the 1st node with type TEXT_NODE
                            {
                                System.out.println("Tag <" + tagName + "> has no children");
                            } else if (node1.getNodeType() == node1.ELEMENT_NODE) {
                                if (nodeNamesList.size() == 1){
                                    System.out.println("Tag <" + tagName + "> has children:");
                                }
                                System.out.println(node1.getNodeName());
                            }
                        }
                        nodeNamesList.add(node1.getNodeName());
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Tag <" + tagName + "> is undefined.");
        }
    }

    public static void getTagValues(File inputFile, String tagName) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;

            dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            XPath xPath =  XPathFactory.newInstance().newXPath();

            String expression = "/company/department/employee";
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
                    doc, XPathConstants.NODESET);

            List<String> nodeNamesList = new ArrayList<>();
            String tagContext;

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node nNode = nodeList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    NodeList tagsList = eElement.getElementsByTagName(tagName).item(0).getChildNodes();

                    if (tagsList.getLength() == 1) //even tag with nested tags has the 1st node with type TEXT_NODE
                    {
                        NodeList skillsList = eElement.getElementsByTagName(tagName);

                        for (int count = 0; count < skillsList.getLength(); count++) {

                            Node node1 = skillsList.item(count);

                            if (node1.getNodeType() == node1.ELEMENT_NODE) {

                                Element skill = (Element) node1;
                                if (!nodeNamesList.contains(skill.getTextContent())) {
                                    System.out.println(skill.getTextContent());
                                    tagContext = skill.getTextContent();
                                    nodeNamesList.add(tagContext);
                                }
                            }
                        }
                    } else {
                        System.out.println("Tag <" + tagName + "> has only nested tags. \nPlease, look at <" + tagName.substring(0, tagName.length() - 1) + "> values.");

                        System.out.print("\n*** The values of tag: ");
                        tagName = new Scanner(System.in).next();
                        System.out.print("\n");
                        getTagValues(inputFile, tagName);

                        break;
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Tag <" + tagName + "> is undefined.");
        }
    }

    public static void parseXmlFile(File inputFile) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;

            dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            XPath xPath =  XPathFactory.newInstance().newXPath();

            String expression = "/company/department/employee[@empId = '101']";
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
                    doc, XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Employee ID = "
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
                    System.out.println("Birth Date : "
                            + eElement
                            .getElementsByTagName("birthDate")
                            .item(0)
                            .getTextContent());
                    System.out.println("Position : "
                            + eElement
                            .getElementsByTagName("position")
                            .item(0)
                            .getTextContent());
                    System.out.println("Skills : ");

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
                    /*
                    System.out.println("    "
                            + eElement
                            .getElementsByTagName("skill")
                            .item(0)
                            .getTextContent());*/
                    System.out.println("Manager ID : "
                            + eElement
                            .getElementsByTagName("managerId")
                            .item(0)
                            .getTextContent());
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }
}
