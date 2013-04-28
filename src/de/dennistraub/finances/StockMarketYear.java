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

    public Dollars endingBalance() {
        return startingBalance().subtract(totalWithdrawn()).add(interestEarned());
    }

    public Dollars endingPrincipal() {
        Dollars result = (startingPrincipal().subtract(totalWithdrawals));
        if (result.amount() >= 0)
            return result;
        else
            return new Dollars(0);
    }

    public StockMarketYear nextYear() {
        return new StockMarketYear(
                endingBalance(),
                startingPrincipal,
                interestRate(),
                capitalGainsTaxRate);
    }

    public void withdraw(Dollars amount) {
        this.totalWithdrawals = totalWithdrawals.add(amount);
    }

    private Dollars capitalGainsWithdrawn() {
        Dollars principalMinusWithdrawals = startingPrincipal().subtract(totalWithdrawals);
        Dollars result = principalMinusWithdrawals.multiplyBy(-1);
        if (result.amount() >= 0)
            return result;
        else
            return new Dollars(0);
    }

    public Dollars capitalGainsTaxIncurred() {
        int taxAmount = capitalGainsTaxRate.compoundTaxFor(capitalGainsWithdrawn().amount());
        return new Dollars(taxAmount);
    }

    public Dollars totalWithdrawn() {
        return totalWithdrawals.add(capitalGainsTaxIncurred());
    }

    public Dollars interestEarned() {
        int interest = interestRate.interestOn(startingBalance.subtract(totalWithdrawn()).amount());
        return new Dollars(interest);
    }

    public TaxRate capitalGainsTaxRate() {
        return capitalGainsTaxRate;
    }
}
