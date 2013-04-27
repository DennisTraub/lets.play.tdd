/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

import java.math.BigDecimal;

public class TaxRate {

    private BigDecimal rate;

    public TaxRate(double rateAsPercentage) {
        this.rate = new BigDecimal(rateAsPercentage).divide(new BigDecimal(100));
    }

    public int taxFor(int amount) {
        return rate.multiply(new BigDecimal(amount)).intValue();
    }
}
