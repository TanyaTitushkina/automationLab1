package com.alliedtesting.lab2;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ExcelFileCreator {

    static Workbook originalWb = null;

    public static void workBookCreator(ExchangeRates rate, String date, int sheetIndex)
            throws Exception {
        Workbook wb = originalWb;

        if (wb == null) {
            System.out.println("This Excel workbook has not been created, yet.\nWill create it.\n");

            wb = new HSSFWorkbook();
            originalWb = wb;
        }
        Sheet sheet = wb.createSheet();
        wb.setSheetName(sheetIndex, date);

        int totalCurrencies = rate.getCurrencies().size();
        String fieldName;

        List<String> valueNames = new ArrayList();

        for (Field field : Currency.class.getDeclaredFields()) {
            fieldName = field.getName();
            if(!valueNames.contains(fieldName)) {
                valueNames.add(fieldName);
            }
        }

        for (int i = 0; i <= totalCurrencies; i++) {
            Row row = sheet.createRow(i);
            List<String> valuteData = null;
            if (i - 1 >= 0) {
                valuteData = rate.getCurrencies().get(i - 1).getDataArray();
            }
            for (int k = 0; k < valueNames.size(); k++) {
                String fillData;

                if (valuteData == null) { //header line
                    fillData = valueNames.get(k);
                    createHeaderCell(wb, row, k, fillData);
                } else {
                    fillData = valuteData.get(k);
                    createCell(wb, row, k, fillData);
                }
            }
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("Bnm_ExchangeRates_by_Dates.xls");

        wb.write(fileOut);
        fileOut.close();
        System.out.print("Bnm_ExchangeRates_by_Dates.xls is updated");
    }

    private static void createCell(Workbook wb, Row row, int column, String value) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
        cell.setCellStyle(cellStyle);
    }

    private static void createHeaderCell(Workbook wb, Row row, int column, String value) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);

        Font fontHeaderBold = wb.createFont();
        fontHeaderBold.setBold(true);

        CellStyle styleHeaderBold = wb.createCellStyle();
        styleHeaderBold.setFont(fontHeaderBold);
        styleHeaderBold.setAlignment(HorizontalAlignment.CENTER);
        styleHeaderBold.setVerticalAlignment(VerticalAlignment.CENTER);
        cell.setCellStyle(styleHeaderBold);
    }
}
