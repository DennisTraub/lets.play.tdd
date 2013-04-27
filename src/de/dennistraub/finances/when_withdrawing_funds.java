/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class when_withdrawing_funds {

    private StockMarketYear newSUT() {
        return new StockMarketYear(10000, 3000, 10, .25);
    }

    @Test
    public void withdrawn_funds_accumulate_no_gains() {
        StockMarketYear year = newSUT();
        year.withdraw(1000);
        assertEquals(9900, year.endingBalance());
    }

    @Test
    public void withdrawal_is_deducted_from_principal() {
        StockMarketYear year = newSUT();
        year.withdraw(2000);
        assertEquals("ending principal", 1000, year.endingPrincipal());
    }

    @Test
    public void principal_never_goes_below_zero() {
        StockMarketYear year = newSUT();
        year.withdraw(4000);
        assertEquals("ending principal", 0, year.endingPrincipal());
    }

    @Test
    public void multiple_withdrawals_add_up() {
        StockMarketYear year = newSUT();
        year.withdraw(1000);
        year.withdraw(2000);
        assertEquals(3000, year.totalWithdrawn());
    }
}
