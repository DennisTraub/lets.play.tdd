/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class when_withdrawing_funds {

    private StockMarketYear newSUT() {
        return new StockMarketYear(10000, 3000, 10, .25);
    }

    @Test
    public void total_withdrawal_ending_principal_and_balance_are_calculated() {
        StockMarketYear year = newSUT();
        year.withdraw(1000);
        year.withdraw(400);
        assertEquals("total withdrawal", 1400, year.totalWithdrawn());
        assertEquals("ending principal", 1600, year.endingPrincipal());
        assertEquals("ending balance", 9460, year.endingBalance());
    }

    @Test
    public void ending_principal_cant_go_below_zero() {
        StockMarketYear year = newSUT();
        year.withdraw(5000);
        assertEquals("ending principal can't go below 0", 0, year.endingPrincipal());
    }

    @Test
    public void principal_is_withdrawn_first() {
        StockMarketYear year = newSUT();
        year.withdraw(4000);
        assertEquals(1000, year.capitalGainsWithdrawn());
    }

    @Test
    public void incurred_tax_covers_the_withdrawal_for_taxes() {
        StockMarketYear year = newSUT();
        year.withdraw(5000);
        assertEquals(666, year.capitalGainsTaxIncurred());
    }

    @Test
    public void capital_gains_tax_is_deducted_from_ending_balance() {
        StockMarketYear year = newSUT();
        year.withdraw(5000);
        assertEquals(4767, year.endingBalance());
    }

    @Test
    public void total_withdrawn_includes_capital_gains_tax() {
        StockMarketYear year = new StockMarketYear(10000,0,10,.25);
        year.withdraw(1000);
        assertEquals(1333, year.totalWithdrawn());
    }

    @Test
    public void capital_gains_taxes_earn_no_interest() {
        StockMarketYear year = new StockMarketYear(10000,0,10,.25);
        year.withdraw(1000);
        assertEquals(866, year.interestEarned());
    }

}
