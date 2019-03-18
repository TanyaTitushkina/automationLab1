package com.alliedtesting.lab2;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

public class BnmParser {

    static List<ExchangeRates> allCurrencies = new ArrayList();
    static String[] dates;

    public static void main(String[] args) throws Exception {

        String pathToCSV = "./dates.csv";
        dates = CsvParser.readCsvUsingSplit(pathToCSV);
        allCurrencies = new ArrayList();
        System.out.println("Number of dates = " + dates.length + "\n");
        for (int i = 0; i < dates.length; i++) {
            String date = dates[i];
            System.out.println("Exchange Rates for Date " + date + "\n");
            try {
                Client client = Client.create();
                WebResource webResource = client.resource("http://bnm.md/en/official_exchange_rates?get_xml=1&date=" + date);
                ClientResponse response = webResource.accept("application/xml").get(ClientResponse.class);
                if (response.getStatus() != 200) { //if HTTP status code is not OK
                    throw new RuntimeException("Failed. HTTP error code = " + response.getStatus());
                }
                String output = response.getEntity(String.class);
                System.out.println(output);

                XStream xstream = new XStream();
                xstream.processAnnotations(Currency.class);
                xstream.processAnnotations(ExchangeRates.class);

                ExchangeRates exchangeRate = (ExchangeRates) xstream.fromXML(output);
                allCurrencies.add(exchangeRate);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Writing Exchange Rates data for " + allCurrencies.size() + " days to Bnm_ExchangeRates_by_Dates.xls.\n");
        writeToExcel();
    }

    public static void writeToExcel() throws Exception {
        for (int i = 0; i < allCurrencies.size(); i++) {
            ExcelFileCreator.workBookCreator(allCurrencies.get(i), dates[i], i);
            System.out.println(" for " +  dates[i] + "\n");
        }
    }
}
