package com.alliedtesting.lab2;

import java.io.*;

public class CsvParser {

    public static String[] readCsvUsingSplit(String csvFile)
    {
        String cvsSplitBy = ",";
        String[] data = new String[0];

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            br.readLine();
            String line1;

            while ((line1 = br.readLine()) != null) {
                data = line1.split(cvsSplitBy);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
