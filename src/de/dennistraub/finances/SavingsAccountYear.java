/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

public class SavingsAccountYear {
    private int startingBalance;
    private int capitalGainsAmount;
    private int interestRate;
    private int totalWithdrawn;

    public SavingsAccountYear(int startingBalance, int capitalGainsAmount, int interestRate) {
        this.startingBalance = startingBalance;
        this.capitalGainsAmount = capitalGainsAmount;
        this.interestRate = interestRate;
    }

    public int startingBalance() {
        return startingBalance;
    }

    public int startingPrincipal() {
        return startingBalance - capitalGainsAmount;
    }

    public int interestRate() {
        return interestRate;
    }

    public int endingBalance() {
        int result = startingBalance;
        result -= totalWithdrawn;
        return result + (result * interestRate / 100);
    }

    public int endingPrincipal() {
        return startingPrincipal() - totalWithdrawn;
    }

    public SavingsAccountYear nextYear() {
        return new SavingsAccountYear(this.endingBalance(), capitalGainsAmount, interestRate);
    }

    public void withdraw(int amount) {
        this.totalWithdrawn = amount;
    }


}
