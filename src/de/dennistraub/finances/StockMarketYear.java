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
        return startingBalance()
                - totalWithdrawals
                - capitalGainsTaxIncurred()
                + interestEarned();
    }

    public int endingPrincipal() {
        int result = startingPrincipal() - totalWithdrawals;
        return Math.max(0, result);
    }

    public StockMarketYear nextYear() {
        return new StockMarketYear(endingBalance(), startingPrincipal, interestRate(), capitalGainsTaxRate);
    }

    public void withdraw(int amount) {
        this.totalWithdrawals += amount;
    }

    private int capitalGainsWithdrawn() {
        int result = (startingPrincipal() - totalWithdrawals) * -1;
        return Math.max(0, result);
    }

    public int capitalGainsTaxIncurred() {
        return capitalGainsTaxRate.compoundTaxFor(capitalGainsWithdrawn());
    }

    public int totalWithdrawn() {
        return totalWithdrawals + capitalGainsTaxIncurred();
    }

    public int interestEarned() {
        return interestRate.interestFor(startingBalance - totalWithdrawn());
    }

    public TaxRate capitalGainsTaxRate() {
        return capitalGainsTaxRate;
    }
}
