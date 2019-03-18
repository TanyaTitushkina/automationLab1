package com.alliedtesting.lab2;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Valute")
public class Currency {

    private String NumCode;
    private String CharCode;
    private int Nominal;
    private String Name;
    private double Value;

    public Currency(String numCode, String charCode, int nominal, String name, double value) {
        NumCode = numCode;
        CharCode = charCode;
        Nominal = nominal;
        Name = name;
        Value = value;
    }

    public void setNumCode(String numCode) {
        NumCode = numCode;
    }

    public void setCharCode(String charCode) {
        CharCode = charCode;
    }

    public void setNominal(int nominal) {
        Nominal = nominal;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setValue(double value) {
        Value = value;
    }

    public String getNumCode() {
        return NumCode;
    }

    public String getCharCode() {
        return CharCode;
    }

    public int getNominal() {
        return Nominal;
    }

    public String getName() {
        return Name;
    }

    public double getValue() {
        return Value;
    }

    public List<String> getDataArray() {
        List<String> data = new ArrayList<>();
        data.add(NumCode);
        data.add(CharCode);
        data.add(String.valueOf(Nominal));
        data.add(Name);
        data.add(String.valueOf(Value));
        return data;
    }
}
