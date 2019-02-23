package com.alliedtesting.lab1;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReaderSplitScanner {

    private static final String csvFile = "./students.csv";
    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';

    public static void main(String[] args) {

        System.out.println("***Read Using Split***\n");
        readUsingSplit(csvFile);

        System.out.println("\n***Read Using Scanner***\n");
        readUsingScanner(csvFile);
    }

    public static void readUsingSplit(String csvFile)
    {
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            br.readLine(); // this will read the first line
            String line1 = null;

            while ((line1 = br.readLine()) != null) {
                // use comma as separator
                String[] data = line1.split(cvsSplitBy);

                System.out.println(data[1] + " " + data[2] + " lives at " + data[4] + ".");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readUsingScanner(String csvFile) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(csvFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        parseLine(scanner.nextLine());
        List<String> line1 = null;

        while (scanner.hasNext()) {
            line1 = parseLine(scanner.nextLine());
            System.out.println(line1.get(1) + " " + line1.get(2) + " lives at " + line1.get(4) + ".");
        }
        scanner.close();
    }

    public static List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String cvsLine, char separators, char customQuote) {

        List<String> result = new ArrayList<>();

        //if empty, return!
        if (cvsLine == null && cvsLine.isEmpty()) {
            return result;
        }
        if (customQuote == ' ') {
            customQuote = DEFAULT_QUOTE;
        }
        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }
        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {
            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {
                    //Fixed : allow "" in custom quote enclosed
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }
                }
            } else {
                if (ch == customQuote) {
                    inQuotes = true;
                    //Fixed : allow "" in empty quote enclosed
                    if (chars[0] != '"' && customQuote == '\"') {
                        curVal.append('"');
                    }
                    //double quotes in column will hit this!
                    if (startCollectChar) {
                        curVal.append('"');
                    }
                } else if (ch == separators) {
                    result.add(curVal.toString());
                    curVal = new StringBuffer();
                    startCollectChar = false;
                } else if (ch == '\r') {
                    //ignore LF characters
                    continue;
                } else if (ch == '\n') {
                    //the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }
        }
        result.add(curVal.toString());
        return result;
    }
}
