/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

public class SavingsAccountYear {
    private int interestRate;
    private int startingBalance = 0;

    public SavingsAccountYear() {}
    public SavingsAccountYear(int startingBalance, int interestRate) {
        this.startingBalance = startingBalance;
        this.interestRate = interestRate;
    }

    public int startingBalance() {
        return startingBalance;
    }

    public SavingsAccountYear nextYear() {
        return new SavingsAccountYear(this.endingBalance(), interestRate);
    }

    public int endingBalance() {
        return startingBalance + (startingBalance * interestRate / 100);
    }

    public int interestRate() {
        return interestRate;
    }
}
