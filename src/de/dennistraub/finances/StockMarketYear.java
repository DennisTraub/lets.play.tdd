/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

public class StockMarketYear {
    private Dollars startingBalance;
    private Dollars startingPrincipal;
    private InterestRate interestRate;
    private Dollars totalWithdrawals;
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
        this.totalWithdrawals = new Dollars(0);
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
        int amount = (startingPrincipal().subtract(totalWithdrawals)).amount();
        return Math.max(0, amount);
    }

    public StockMarketYear nextYear() {
        return new StockMarketYear(
                new Dollars(endingBalance()),
                startingPrincipal,
                interestRate(),
                capitalGainsTaxRate);
    }

    public void withdraw(int amount) {
        this.totalWithdrawals = new Dollars(totalWithdrawals.amount() + amount);
    }

    private Dollars capitalGainsWithdrawn() {
        Dollars result = (startingPrincipal().subtract(totalWithdrawals)).multiplyBy(-1);
        if (result.amount() >= 0)
            return result;
        else
            return new Dollars(0);
    }

    public int capitalGainsTaxIncurred() {
        return capitalGainsTaxRate.compoundTaxFor(capitalGainsWithdrawn().amount());
    }

    public int totalWithdrawn() {
        return totalWithdrawals.amount() + capitalGainsTaxIncurred();
    }

    public int interestEarned() {
        return interestRate.interestOn(startingBalance.amount() - totalWithdrawn());
    }

    public TaxRate capitalGainsTaxRate() {
        return capitalGainsTaxRate;
    }
}
