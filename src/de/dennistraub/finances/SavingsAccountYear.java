/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

public class SavingsAccountYear {
    private int startingBalance;
    private int startingPrincipal;
    private int interestRate;
    private int totalWithdrawals;
    private double taxRate;

    public SavingsAccountYear(int startingBalance, int startingPrincipal, int interestRate, double taxRate) {
        this.startingBalance = startingBalance;
        this.startingPrincipal = startingPrincipal;
        this.interestRate = interestRate;
        this.taxRate = taxRate;
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

    public int endingBalance() {
        int endingBeforeInterest = startingBalance() - totalWithdrawals - capitalGainsTaxIncurred();
        return endingBeforeInterest + interestEarned();
    }

    public int endingPrincipal() {
        int result = startingPrincipal() - totalWithdrawals;
        return Math.max(0, result);
    }

    public SavingsAccountYear nextYear() {
        return new SavingsAccountYear(endingBalance(), startingPrincipal, interestRate(), taxRate);
    }

    public void withdraw(int amount) {
        this.totalWithdrawals += amount;
    }

    public int capitalGainsWithdrawn() {
        int result = (startingPrincipal() - totalWithdrawals) * -1;
        return Math.max(0, result);
    }

    public int capitalGainsTaxIncurred() {
        double capitalGainsAsDouble = capitalGainsWithdrawn();
        return (int)((capitalGainsAsDouble / (1 - taxRate)) - capitalGainsAsDouble);
    }

    public int startingCapitalGains() {
        return startingBalance() - startingPrincipal();
    }

    public int endingCapitalGains() {
        return startingCapitalGains() + interestEarned();
    }

    public int totalWithdrawn() {
        return totalWithdrawals + capitalGainsTaxIncurred();
    }

    public int interestEarned() {
        return (startingBalance - totalWithdrawn()) * interestRate / 100;
    }
}
