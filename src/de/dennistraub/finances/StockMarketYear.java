/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

public class StockMarketYear {
    private Dollars startingBalance;
    private Dollars startingPrincipal;
    private InterestRate interestRate;
    private int totalWithdrawals;
    private TaxRate capitalGainsTaxRate;

    public StockMarketYear(
            Dollars startingBalance,
            Dollars startingPrincipal,
            InterestRate interestRate,
            TaxRate capitalGainsTaxRate) {
        this.startingBalance = startingBalance;
        this.startingPrincipal = startingPrincipal;
        this.interestRate = interestRate;
        this.capitalGainsTaxRate = capitalGainsTaxRate;
    }

    public Dollars startingBalance() {
        return startingBalance;
    }

    public Dollars startingPrincipal() {
        return startingPrincipal;
    }

    public InterestRate interestRate() {
        return interestRate;
    }

    public int endingBalance() {
        return startingBalance().amount() - totalWithdrawn() + interestEarned();
    }

    public int endingPrincipal() {
        return Math.max(0, startingPrincipal().amount() - totalWithdrawals);
    }

    public StockMarketYear nextYear() {
        return new StockMarketYear(
                new Dollars(endingBalance()),
                startingPrincipal,
                interestRate(),
                capitalGainsTaxRate);
    }

    public void withdraw(int amount) {
        this.totalWithdrawals += amount;
    }

    private int capitalGainsWithdrawn() {
        return Math.max(0, (startingPrincipal().amount() - totalWithdrawals) * -1);
    }

    public int capitalGainsTaxIncurred() {
        return capitalGainsTaxRate.compoundTaxFor(capitalGainsWithdrawn());
    }

    public int totalWithdrawn() {
        return totalWithdrawals + capitalGainsTaxIncurred();
    }

    public int interestEarned() {
        return interestRate.interestOn(startingBalance.amount() - totalWithdrawn());
    }

    public TaxRate capitalGainsTaxRate() {
        return capitalGainsTaxRate;
    }
}
