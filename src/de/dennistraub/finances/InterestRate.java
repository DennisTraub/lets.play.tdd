/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

public class InterestRate {

    private double rate;

    public InterestRate(int rateAsPercentage) {
        this.rate = rateAsPercentage / 100.0;
    }

    public int interestOn(int amount) {
        return (int)(amount * rate);
    }

    @Override
    public String toString() {
        return (int)(rate * 100) + "%";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InterestRate that = (InterestRate) o;

        if (Double.compare(that.rate, rate) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(rate);
        return (int) (temp ^ (temp >>> 32));
    }
}
