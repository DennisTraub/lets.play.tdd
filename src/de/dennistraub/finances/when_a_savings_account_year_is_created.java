/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class when_a_savings_account_year_is_created {

    private SavingsAccountYear newSUT() {
        return new SavingsAccountYear(10000, 7000, 10);
    }

    @Test
    public void starting_balance_is_set() {
        assertEquals(10000, newSUT().startingBalance());
    }

    @Test
    public void starting_principal_is_set() {
        assertEquals(7000, newSUT().startingPrincipal());
    }

    @Test
    public void interest_rate_is_set() {
        assertEquals(10, newSUT().interestRate());
    }

    @Test
    public void ending_balance_has_interest_rate_applied() {
        assertEquals(11000, newSUT().endingBalance(25));
    }

    @Test
    public void earned_interest_is_calculated() {
        assertEquals(1000, newSUT().interestEarned(25));
    }

    @Test
    public void ending_capital_gains_includes_earned_interest() {
        SavingsAccountYear year = newSUT();
        assertEquals(3000, year.startingCapitalGains());
        assertEquals(4000, year.endingCapitalGains(25));
    }
}
