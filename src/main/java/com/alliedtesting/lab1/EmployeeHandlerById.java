package com.alliedtesting.lab1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class EmployeeHandlerById extends DefaultHandler {

    boolean bFirstName = false;
    boolean bLastName = false;
    boolean bBirthDate = false;
    boolean bPosition = false;
    boolean bSkills = false;
    boolean bSkill = false;
    boolean bMngrId = false;
    String empId = null;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("employee")) {
            empId = attributes.getValue("empId");
        }
        if(("203").equals(empId) && qName.equalsIgnoreCase("employee")) {
            System.out.println("\nEmployee ID = " + empId);
        }
        if (("203").equals(empId) && qName.equalsIgnoreCase("firstname")) {
            bFirstName = true;
        } else if (("203").equals(empId) && qName.equalsIgnoreCase("lastname")) {
            bLastName = true;
        } else if (("203").equals(empId) && qName.equalsIgnoreCase("birthDate")) {
            bBirthDate = true;
        } else if (("203").equals(empId) && qName.equalsIgnoreCase("position")) {
            bPosition = true;
        } else if (("203").equals(empId) && qName.equalsIgnoreCase("skills")) {
            bSkills = true;
        } else if (("203").equals(empId) && qName.equalsIgnoreCase("skill")) {
            bSkill = true;
        } else if (("203").equals(empId) && qName.equalsIgnoreCase("managerId")) {
            bMngrId = true;
        }
    }

    @Override
    public void endElement(
            String uri, String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase("employee")) {
            if(("203").equals(empId) && qName.equalsIgnoreCase("employee"))
                System.out.println("****************************");
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (bLastName && ("203").equals(empId)) {
            System.out.println("Last Name: " + new String(ch, start, length));
            bLastName = false;
        } else if (bFirstName && ("203").equals(empId)) {
            System.out.println("First Name: " + new String(ch, start, length));
            bFirstName = false;
        } else if (bBirthDate && ("203").equals(empId)) {
            System.out.println("Birth Date: " + new String(ch, start, length));
            bBirthDate = false;
        } else if (bPosition && ("203").equals(empId)) {
            System.out.println("Position: " + new String(ch, start, length));
            bPosition = false;
        } else if (bSkills && ("203").equals(empId)) {
            System.out.println("\nSkills: " + new String(ch, start, length));
            bSkills = false;
        } else if (bSkill && ("203").equals(empId)) {
            System.out.println("    " + new String(ch, start, length));
            bSkill = false;
        } else if (bMngrId && ("203").equals(empId)) {
            System.out.println("\nManager ID: " + new String(ch, start, length));
            bMngrId = false;
        }
    }
}
