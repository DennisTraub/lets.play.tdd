/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

import java.math.BigDecimal;

public class TaxRate {

    private double rate;

    public TaxRate(double rateAsPercentage) {
        this.rate = rateAsPercentage / 100.0;
    }

    public int simpleTaxFor(int amount) {
        return (int)(rate * amount);
    }

    public int compoundTaxFor(int amount) {
        return (int)(amount / (1 - rate) - amount);
    }
}
