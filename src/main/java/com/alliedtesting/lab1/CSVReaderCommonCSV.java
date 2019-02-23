package com.alliedtesting.lab1;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;

public class CSVReaderCommonCSV {

    private static final String SAMPLE_CSV_FILE_PATH = "./students.csv";

    public static void main(String[] args) throws IOException {
        try (
                Reader reader = new BufferedReader(new FileReader(SAMPLE_CSV_FILE_PATH));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader() //Header Auto-detection
                        .withIgnoreHeaderCase()
                        .withTrim());
        ) {
            for (CSVRecord csvRecord : csvParser) {
                // Accessing values by Header names
                String id = csvRecord.get("ID");
                String firstName = csvRecord.get("FirstName");
                String lastName = csvRecord.get("LastName");
                String birth = csvRecord.get("Birth");
                String address = csvRecord.get("Address");

                System.out.println("Record No - " + csvRecord.getRecordNumber());
                System.out.println("---------------");
                System.out.println("ID        : " + id);
                System.out.println("FirstName : " + firstName);
                System.out.println("LastName  : " + lastName);
                System.out.println("Birth     : " + birth);
                System.out.println("Address   : " + address);
                System.out.println("---------------\n");
            }
        }
    }
}
