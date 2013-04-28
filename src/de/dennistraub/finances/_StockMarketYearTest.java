/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class _StockMarketYearTest {

    public static final Dollars STARTING_BALANCE = new Dollars(10000);
    public static final Dollars STARTING_PRINCIPAL = new Dollars(3000);
    public static final InterestRate INTEREST_RATE = new InterestRate(10);
    public static final TaxRate CAPITAL_GAINS_TAX_RATE = new TaxRate(25);

    private StockMarketYear newYear() {
        return new StockMarketYear(
                STARTING_BALANCE,
                STARTING_PRINCIPAL,
                INTEREST_RATE,
                CAPITAL_GAINS_TAX_RATE);
    }

    @Test
    public void starting_values() {
        StockMarketYear year = newYear();
        assertEquals(STARTING_BALANCE, year.startingBalance());
        assertEquals(STARTING_PRINCIPAL, year.startingPrincipal());
        assertEquals(INTEREST_RATE, year.interestRate());
        assertEquals(CAPITAL_GAINS_TAX_RATE, year.capitalGainsTaxRate());
    }

    @Test
    public void capital_gains_tax() {
        StockMarketYear year = newYear();
        year.withdraw(new Dollars(4000));
        assertEquals(333, year.capitalGainsTaxIncurred().amount());
        assertEquals(4333, year.totalWithdrawn().amount());
    }

    @Test
    public void interest_earned() {
        StockMarketYear year = newYear();
        assertEquals("basic interest earned", 1000, year.interestEarned().amount());
        year.withdraw(new Dollars(2000));
        assertEquals("withdrawals don't earn interest", 800, year.interestEarned().amount());
        year.withdraw(new Dollars(2000));
        assertEquals("capital gains tax doesn't earn interest", 566, year.interestEarned().amount());
    }

    @Test
    public void total_withdrawal_ending_principal_and_balance_are_calculated() {
        StockMarketYear year = newYear();
        year.withdraw(new Dollars(1000));
        year.withdraw(new Dollars(400));
        assertEquals("total withdrawal", 1400, year.totalWithdrawn().amount());
        assertEquals("ending principal", 1600, year.endingPrincipal().amount());
        assertEquals("ending balance", 9460, year.endingBalance().amount());
    }

    @Test
    public void ending_balance() {
        StockMarketYear year = newYear();
        assertEquals(11000, year.endingBalance().amount());
        year.withdraw(new Dollars(1000));
        assertEquals(9900, year.endingBalance().amount());
        year.withdraw(new Dollars(3000));
        assertEquals(6233, year.endingBalance().amount());
    }

    @Test
    public void values_carry_over_to_next_year() {
        StockMarketYear thisYear = newYear();
        StockMarketYear nextYear = thisYear.nextYear();
        assertEquals(thisYear.endingBalance(), nextYear.startingBalance());
        assertEquals(thisYear.endingPrincipal(), nextYear.startingPrincipal());
        assertEquals(thisYear.interestRate(), nextYear.interestRate());
        assertEquals(thisYear.capitalGainsTaxRate(), nextYear.capitalGainsTaxRate());
    }
}
