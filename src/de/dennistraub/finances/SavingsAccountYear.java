/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

public class SavingsAccountYear {
    private int startingBalance;
    private int startingPrincipal;
    private int interestRate;
    private int totalWithdrawn;

    public SavingsAccountYear(int startingBalance, int startingPrincipal, int interestRate) {
        this.startingBalance = startingBalance;
        this.startingPrincipal = startingPrincipal;
        this.interestRate = interestRate;
    }

    public int startingBalance() {
        return startingBalance;
    }

    public int startingPrincipal() {
        return startingPrincipal;
    }

    public int interestRate() {
        return interestRate;
    }

    public int totalWithdrawn() {
        return totalWithdrawn;
    }

    public int endingBalance() {
        int endingBeforeInterest = startingBalance() - totalWithdrawn();
        return endingBeforeInterest + (endingBeforeInterest * interestRate() / 100);
    }

    public int endingPrincipal() {
        int result = startingPrincipal() - totalWithdrawn();
        return Math.max(0, result);
    }

    public SavingsAccountYear nextYear() {
        return new SavingsAccountYear(endingBalance(), startingPrincipal, interestRate());
    }

    public void withdraw(int amount) {
        this.totalWithdrawn += amount;
    }

    public int capitalGainsWithdrawn() {
        int result = (startingPrincipal() - totalWithdrawn()) * -1;
        return Math.max(0, result);
    }
}
