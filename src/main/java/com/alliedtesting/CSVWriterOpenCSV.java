package com.alliedtesting;

import java.io.*;
import java.util.*;
import com.opencsv.CSVWriter;

public class CSVWriterOpenCSV {
    private static final String CSV_FILE_PATH = "./students.csv";
    public static void main(String[] args)
    {
        addDataToCSV(CSV_FILE_PATH);
    }
    public static void addDataToCSV(String output)
    {
        // first create file object for file placed at location
        // specified by filepath
        File file = new File(output);
        Scanner sc = new Scanner(System.in);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter with ';' as separator
            CSVWriter writer = new CSVWriter(outputfile/*, ';',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END*/);

            // create a List which contains Data
            List<String[]> data = new ArrayList<>();

            data.add(new String[] { "ID", "FirstName", "LastName", "Birth", "Address" });
            data.add(new String[] { "1", "Alice", "Mindls", "06.07.1991", "Lemon Street 48" });
            data.add(new String[] { "2", "Elisabeth", "Noker", "12.11.1992", "Fleet Street 2" });
            data.add(new String[] { "3", "Jack", "Baggins", "01.04.1991", "Backend Valley 1" });
            data.add(new String[] { "4", "Dick", "Norton", "Stockholm Street 11/2" });

            writer.writeAll(data);

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
