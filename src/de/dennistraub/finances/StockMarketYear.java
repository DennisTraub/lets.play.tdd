/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

public class StockMarketYear {
    private int startingBalance;
    private int startingPrincipal;
    private InterestRate interestRate;
    private int totalWithdrawals;
    private TaxRate capitalGainsTaxRate;

    public StockMarketYear(int startingBalance, int startingPrincipal, InterestRate interestRate, TaxRate capitalGainsTaxRate) {
        this.startingBalance = startingBalance;
        this.startingPrincipal = startingPrincipal;
        this.interestRate = interestRate;
        this.capitalGainsTaxRate = capitalGainsTaxRate;
    }

    public int startingBalance() {
        return startingBalance;
    }

    public int startingPrincipal() {
        return startingPrincipal;
    }

    public InterestRate interestRate() {
        return interestRate;
    }

    public int endingBalance() {
        return startingBalance() - totalWithdrawn() + interestEarned();
    }

    public int endingPrincipal() {
        return Math.max(0, startingPrincipal() - totalWithdrawals);
    }

    public StockMarketYear nextYear() {
        return new StockMarketYear(endingBalance(), startingPrincipal, interestRate(), capitalGainsTaxRate);
    }

    public void withdraw(int amount) {
        this.totalWithdrawals += amount;
    }

    private int capitalGainsWithdrawn() {
        return Math.max(0, (startingPrincipal() - totalWithdrawals) * -1);
    }

    public int capitalGainsTaxIncurred() {
        return capitalGainsTaxRate.compoundTaxFor(capitalGainsWithdrawn());
    }

    public int totalWithdrawn() {
        return totalWithdrawals + capitalGainsTaxIncurred();
    }

    public int interestEarned() {
        return interestRate.interestOn(startingBalance - totalWithdrawn());
    }

    public TaxRate capitalGainsTaxRate() {
        return capitalGainsTaxRate;
    }
}
