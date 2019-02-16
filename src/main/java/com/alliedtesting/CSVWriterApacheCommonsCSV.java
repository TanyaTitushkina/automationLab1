package com.alliedtesting;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class CSVWriterApacheCommonsCSV {
    private static final String STUDENTS_CSV_FILE = "./students.csv";

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENTS_CSV_FILE));

                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("ID", "FirstName", "LastName", "Birth", "Address"))
        ) {
            csvPrinter.printRecord("1", "Alice", "Mindls", "06.07.1991", "Lemon Street 48");
            csvPrinter.printRecord("2", "Elisabeth", "Noker", "12.11.1992", "Fleet Street 2");
            csvPrinter.printRecord("3", "Jack", "Baggins", "01.04.1991", "Backend Valley 1");

            csvPrinter.printRecord(Arrays.asList("4", "Dick", "Norton", "Stockholm Street 11/2"));

            csvPrinter.flush();
        }
    }
}
