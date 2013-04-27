/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class when_next_year_is_retrieved {

    private SavingsAccountYear newSUT() {
        return new SavingsAccountYear(10000, 7000, 10);
    }

    @Test
    public void starting_balance_equals_this_years_ending_balance() {
        SavingsAccountYear thisYear = newSUT();
        assertEquals(thisYear.nextYear(25).startingBalance(), thisYear.endingBalance(25));
    }

    @Test
    public void interest_rate_equals_this_years_interest_rate() {
        SavingsAccountYear thisYear = newSUT();
        assertEquals(thisYear.interestRate(), thisYear.nextYear(25).interestRate());
    }
}
