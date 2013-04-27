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

    public int endingBalance(int taxRate) {
        int endingBeforeInterest = startingBalance() - totalWithdrawn() - capitalGainsTaxIncurred(taxRate);
        return endingBeforeInterest + interestEarned(taxRate);
    }

    public int endingPrincipal() {
        int result = startingPrincipal() - totalWithdrawn();
        return Math.max(0, result);
    }

    public SavingsAccountYear nextYear(int capitalGainsTaxRate) {
        return new SavingsAccountYear(endingBalance(capitalGainsTaxRate), startingPrincipal, interestRate());
    }

    public void withdraw(int amount) {
        this.totalWithdrawn += amount;
    }

    public int capitalGainsWithdrawn() {
        int result = (startingPrincipal() - totalWithdrawn()) * -1;
        return Math.max(0, result);
    }

    public int capitalGainsTaxIncurred(int taxRate) {
        double taxRateAsDouble = taxRate / 100.0;
        double capitalGainsAsDouble = capitalGainsWithdrawn();
        return (int)((capitalGainsAsDouble / (1 - taxRateAsDouble)) - capitalGainsAsDouble);
    }

    public int startingCapitalGains() {
        return startingBalance() - startingPrincipal();
    }

    public int endingCapitalGains(int taxRate) {
        return startingCapitalGains() + interestEarned(taxRate);
    }

    public int interestEarned(int taxRate) {
        return (startingBalance - totalWithdrawn() - capitalGainsTaxIncurred(taxRate)) * interestRate / 100;
    }
}
