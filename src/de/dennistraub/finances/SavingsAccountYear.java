/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

public class SavingsAccountYear {
    private int startingBalance;
    private int capitalGainsAmount;
    private int interestRate;

    public SavingsAccountYear(int startingBalance, int capitalGainsAmount, int interestRate) {
        this.startingBalance = startingBalance;
        this.capitalGainsAmount = capitalGainsAmount;
        this.interestRate = interestRate;
    }

    public int startingBalance() {
        return startingBalance;
    }

    public int interestRate() {
        return interestRate;
    }

    public int endingBalance() {
        return startingBalance + (startingBalance * interestRate / 100);
    }

    public SavingsAccountYear nextYear() {
        return new SavingsAccountYear(this.endingBalance(), capitalGainsAmount, interestRate);
    }

    public void withdraw(int amount) {
        this.startingBalance -= amount;
    }
}
