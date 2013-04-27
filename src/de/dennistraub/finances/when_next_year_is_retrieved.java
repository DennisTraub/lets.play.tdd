/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class when_next_year_is_retrieved {

    private StockMarketYear newSUT() {
        return new StockMarketYear(10000, 7000, 10, .25);
    }

    @Test
    public void starting_balance_equals_this_years_ending_balance() {
        StockMarketYear thisYear = newSUT();
        assertEquals(thisYear.nextYear().startingBalance(), thisYear.endingBalance());
    }

    @Test
    public void interest_rate_equals_this_years_interest_rate() {
        StockMarketYear thisYear = newSUT();
        assertEquals(thisYear.interestRate(), thisYear.nextYear().interestRate());
    }
}
