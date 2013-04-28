/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

public class Dollars {

    private int amount;

    public Dollars(int amount) {
        this.amount = amount;
    }

    public int amount() {
        return amount;
    }

    public Dollars add(Dollars summand) {
        return new Dollars(this.amount() + summand.amount());
    }

    public Dollars subtract(Dollars subtrahend) {
        return new Dollars(this.amount() - subtrahend.amount());
    }

    public Dollars multiplyBy(int factor) {
        return new Dollars(this.amount * factor);
    }

    public Dollars divideBy(int divisor) {
        return new Dollars((this.amount() / divisor));
    }

    @Override
    public String toString() {
        return "$" + amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dollars dollars = (Dollars) o;

        if (amount != dollars.amount) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return amount;
    }
}
