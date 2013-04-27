/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

public class SavingsAccountYear {
    private int startingBalance;
    private int startingPrincipal;
    private int interestRate;
    private int totalWithdrawals;

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

    public int endingBalance(int taxRate) {
        int endingBeforeInterest = startingBalance() - totalWithdrawals - capitalGainsTaxIncurred(taxRate);
        return endingBeforeInterest + interestEarned(taxRate);
    }

    public int endingPrincipal() {
        int result = startingPrincipal() - totalWithdrawals;
        return Math.max(0, result);
    }

    public SavingsAccountYear nextYear(int capitalGainsTaxRate) {
        return new SavingsAccountYear(endingBalance(capitalGainsTaxRate), startingPrincipal, interestRate());
    }

    public void withdraw(int amount) {
        this.totalWithdrawals += amount;
    }

    public int capitalGainsWithdrawn() {
        int result = (startingPrincipal() - totalWithdrawals) * -1;
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

    public int totalWithdrawn(int taxRate) {
        return totalWithdrawals + capitalGainsTaxIncurred(taxRate);
    }

    public int interestEarned(int taxRate) {
        return (startingBalance - totalWithdrawn(taxRate)) * interestRate / 100;
    }
}
