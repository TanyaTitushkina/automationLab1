package com.alliedtesting.lab1;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class SaxXMLParser {

    public static void main(String[] args) {

        try {
            File inputFile = new File("employees.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            EmployeeHandler userHandler = new EmployeeHandler();
            saxParser.parse(inputFile, userHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
