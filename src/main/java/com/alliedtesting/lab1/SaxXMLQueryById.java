package com.alliedtesting.lab1;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class SaxXMLQueryById {

    public static void main(String[] args) {

        try {
            File inputFile = new File("employees.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            EmployeeHandlerById userHandlerById = new EmployeeHandlerById();
            saxParser.parse(inputFile, userHandlerById);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
