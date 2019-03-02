package com.alliedtesting.lab1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class EmployeeHandler extends DefaultHandler {

    boolean bFirstName = false;
    boolean bLastName = false;
    boolean bBirthDate = false;
    boolean bPosition = false;
    boolean bSkills = false;
    boolean bSkill = false;
    boolean bMngrId = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("employee")) {
            String empId = attributes.getValue("empId");
            System.out.println("\nEmployee ID = " + empId);
        } else if (qName.equalsIgnoreCase("firstname")) {
            bFirstName = true;
        } else if (qName.equalsIgnoreCase("lastname")) {
            bLastName = true;
        } else if (qName.equalsIgnoreCase("birthDate")) {
            bBirthDate = true;
        } else if (qName.equalsIgnoreCase("position")) {
            bPosition = true;
        } else if (qName.equalsIgnoreCase("skills")) {
            bSkills = true;
        } else if (qName.equalsIgnoreCase("skill")) {
            bSkill = true;
        } else if (qName.equalsIgnoreCase("managerId")) {
            bMngrId = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("employee")) {
            System.out.println("\n************************************");
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (bLastName) {
            System.out.println("Last Name: "
                    + new String(ch, start, length));
            bLastName = false;
        } else if (bFirstName) {
            System.out.println("First Name: " + new String(ch, start, length));
            bFirstName = false;
        } else if (bBirthDate) {
            System.out.println("Birth Date: " + new String(ch, start, length));
            bBirthDate = false;
        } else if (bPosition) {
            System.out.println("Position: " + new String(ch, start, length));
            bPosition = false;
        } else if (bSkills) {
            System.out.println("\nSkills: " + new String(ch, start, length));
            bSkills = false;
        } else if (bSkill) {
            System.out.println("    " + new String(ch, start, length));
            bSkill = false;
        } else if (bMngrId) {
            System.out.println("\nManager ID: " + new String(ch, start, length));
            bMngrId = false;
        }
    }
}
