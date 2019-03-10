package com.alliedtesting.lab1;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ApachePOIExcelWriter {

    private static final File inputFile = new File("employees.xml");

    public static void main(String[] args) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            NodeList depList = doc.getElementsByTagName("department");

            Workbook wb = new HSSFWorkbook();

            int rowHeight = 0;

            for (int temp = 0; temp < depList.getLength(); temp++) {
                Node depNode = depList.item(temp);

                if (depNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) depNode;

                    String sheetName = "depId-" + eElement.getAttribute("depId") + "_" + eElement.getAttribute("name");

                    Sheet newSheet = wb.createSheet(sheetName); // create new sheet with depId and depName as title

                    //create header row with bold and centered text
                    Row headerRow = newSheet.createRow(0);

                    Font fontHeaderBold = wb.createFont();
                    fontHeaderBold.setBold(true);

                    CellStyle styleHeaderBold = wb.createCellStyle();
                    styleHeaderBold.setFont(fontHeaderBold);
                    styleHeaderBold.setAlignment(HorizontalAlignment.CENTER);
                    styleHeaderBold.setVerticalAlignment(VerticalAlignment.CENTER);

                    Font fontNonBold = wb.createFont();
                    fontNonBold.setBold(false);

                    CellStyle styleNonBold = wb.createCellStyle();
                    styleNonBold.setFont(fontNonBold);
                    styleNonBold.setAlignment(HorizontalAlignment.LEFT);
                    styleNonBold.setVerticalAlignment(VerticalAlignment.CENTER);

                    final int empIdCellNumber = 0;
                    final int firstNameCellNumber = 1;
                    final int lastNameCellNumber = 2;
                    final int birthDateCellNumber = 3;
                    final int positionCellNumber = 4;
                    final int managerIdCellNumber = 5;
                    final int skillsCellNumber = 6;

                    Cell empIdCell = headerRow.createCell(empIdCellNumber);
                    empIdCell.setCellValue("Employee ID");
                    empIdCell.setCellStyle(styleHeaderBold);

                    Cell firstNameCell = headerRow.createCell(firstNameCellNumber);
                    firstNameCell.setCellValue("First name");
                    firstNameCell.setCellStyle(styleHeaderBold);

                    Cell lastNameCell = headerRow.createCell(lastNameCellNumber);
                    lastNameCell.setCellValue("Last name");
                    lastNameCell.setCellStyle(styleHeaderBold);

                    Cell birthDateCell = headerRow.createCell(birthDateCellNumber);
                    birthDateCell.setCellValue("Birth date");
                    birthDateCell.setCellStyle(styleHeaderBold);

                    Cell positionCell = headerRow.createCell(positionCellNumber);
                    positionCell.setCellValue("Position");
                    positionCell.setCellStyle(styleHeaderBold);

                    Cell managerIdCell = headerRow.createCell(managerIdCellNumber);
                    managerIdCell.setCellValue("Manager ID");
                    managerIdCell.setCellStyle(styleHeaderBold);

                    Cell skillsCell = headerRow.createCell(skillsCellNumber);
                    skillsCell.setCellValue("Skills");
                    skillsCell.setCellStyle(styleHeaderBold);

                    //add employees data
                    NodeList empList = eElement.getElementsByTagName("employee");

                    for (int i = 0; i < empList.getLength(); i++) {
                        Node empNode = empList.item(i);

                        if (empNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element empElement = (Element) empNode;

                            NodeList skillsList = empElement.getElementsByTagName("skills");

                            Row row = newSheet.createRow(i + 1);
                            rowHeight = (int)(skillsList.getLength() * newSheet.getDefaultRowHeightInPoints()); //row height = number of employee's skills

                            //add skills
                            String skillsString = "";

                            for (int count = 0; count < skillsList.getLength(); count++) {
                                Node node1 = skillsList.item(count);
                                if (node1.getNodeType() == node1.ELEMENT_NODE) {
                                    Element skill = (Element) node1;
                                    skillsString += skill.getTextContent() + "\n";
                                }
                            }

                            CellStyle styleMultiline = wb.createCellStyle();
                            styleMultiline.setWrapText(true);
                            styleMultiline.setAlignment(HorizontalAlignment.LEFT);
                            styleMultiline.setVerticalAlignment(VerticalAlignment.CENTER);

                            Cell skillsValuesCell = row.createCell(skillsCellNumber);
                            skillsValuesCell.setCellValue(skillsString);
                            skillsValuesCell.setCellStyle(styleMultiline);

                            //add empId
                            String empId = empElement.getAttribute("empId");
                            if (!"".equals(empId)) {
                                Cell empIdValuesCell = row.createCell(empIdCellNumber);
                                empIdValuesCell.setCellValue(empId);
                                empIdValuesCell.setCellStyle(styleNonBold);
                            }

                            //add firstName
                            String firstName = empElement.getElementsByTagName("firstName").item(0).getTextContent();
                            if (!"".equals(firstName)) {
                                Cell firstNameValuesCell = row.createCell(firstNameCellNumber);
                                firstNameValuesCell.setCellValue(firstName);
                                firstNameValuesCell.setCellStyle(styleNonBold);
                            }

                            //add lastName
                            String lastName = empElement.getElementsByTagName("lastName").item(0).getTextContent();
                            if (!"".equals(lastName)) {
                                Cell lastNameValuesCell = row.createCell(lastNameCellNumber);
                                lastNameValuesCell.setCellValue(lastName);
                                lastNameValuesCell.setCellStyle(styleNonBold);
                            }

                            //add birthDate
                            String birthDate = empElement.getElementsByTagName("birthDate").item(0).getTextContent();
                            if (!"".equals(birthDate)) {
                                Cell birthDateValuesCell = row.createCell(birthDateCellNumber);
                                birthDateValuesCell.setCellValue(birthDate);
                                birthDateValuesCell.setCellStyle(styleNonBold);
                            }

                            //add position
                            String position = empElement.getElementsByTagName("position").item(0).getTextContent();
                            if (!"".equals(position)) {
                                Cell positionValuesCell = row.createCell(positionCellNumber);
                                positionValuesCell.setCellValue(position);
                                positionValuesCell.setCellStyle(styleNonBold);
                            }

                            //add managerId
                            String managerId = empElement.getElementsByTagName("managerId").item(0).getTextContent();
                            if (!"".equals(managerId)) {
                                Cell managerIdValuesCell = row.createCell(managerIdCellNumber);
                                managerIdValuesCell.setCellValue(managerId);
                                managerIdValuesCell.setCellStyle(styleNonBold);
                            }
                        }
                        newSheet.autoSizeColumn(empIdCellNumber);
                        newSheet.autoSizeColumn(firstNameCellNumber);
                        newSheet.autoSizeColumn(lastNameCellNumber);
                        newSheet.autoSizeColumn(birthDateCellNumber);
                        newSheet.autoSizeColumn(positionCellNumber);
                        newSheet.autoSizeColumn(managerIdCellNumber);
                        newSheet.autoSizeColumn(skillsCellNumber);
                        newSheet.setDefaultRowHeightInPoints(rowHeight);
                    }
                    try (OutputStream fileOut = new FileOutputStream("emp.xls")) {
                        wb.write(fileOut);
                        System.out.println("Excel file is filled with data about employees of department " + (temp+1));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
