/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

public class TaxRate {

    private double rate;

    public TaxRate(double rateAsPercentage) {
        this.rate = rateAsPercentage / 100.0;
    }

    public double rate() {
        return this.rate;
    }

    public int simpleTaxFor(int amount) {
        return (int)(rate * amount);
    }

    public int compoundTaxFor(int amount) {
        return (int)(amount / (1 - rate) - amount);
    }

    @Override
    public String toString() {
        return (int)(rate * 100) + "%";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaxRate taxRate = (TaxRate) o;

        if (Double.compare(taxRate.rate, rate) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(rate);
        return (int) (temp ^ (temp >>> 32));
    }
}
