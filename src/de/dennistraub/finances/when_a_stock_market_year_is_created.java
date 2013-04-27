/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class when_a_stock_market_year_is_created {

    private StockMarketYear newYear() {
        return new StockMarketYear(10000, 7000, 10, .25);
    }

    @Test
    public void the_constructor_parameters_are_set() {
        StockMarketYear year = newYear();
        assertEquals("starting balance", 10000, year.startingBalance());
        assertEquals("starting principal", 7000, year.startingPrincipal());
        assertEquals("interest rate", 10, year.interestRate());
        assertEquals("tax rate", .25, year.taxRate(), 0.001);
    }

    @Test
    public void ending_balance_includes_interest() {
        assertEquals(11000, newYear().endingBalance());
    }

    @Test
    public void next_years_values_get_carried_over() {
        StockMarketYear thisYear = newYear();
        StockMarketYear nextYear = thisYear.nextYear();
        assertEquals("balance", nextYear.startingBalance(), thisYear.endingBalance());
        assertEquals("principal", nextYear.startingPrincipal(), thisYear.endingPrincipal());
        assertEquals("interest rate", nextYear.interestRate(), thisYear.interestRate());
    }
}
