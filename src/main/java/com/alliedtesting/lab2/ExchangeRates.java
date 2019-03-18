package com.alliedtesting.lab2;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

@XStreamAlias("ValCurs")
public class ExchangeRates {

    @XStreamImplicit(itemFieldName = "Valute")
    private List<Currency> currency = new ArrayList<>();

    public List<Currency> getCurrencies() {
        return currency;
    }

    public void setCurrencies(List<Currency> valutes) {
        this.currency = valutes;
    }
}
