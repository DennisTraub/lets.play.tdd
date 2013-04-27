/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

import org.junit.Test;

import static org.junit.Assert.*;

public class _SavingsAccountTest {

    private SavingsAccountYear newAccount() {
        return new SavingsAccountYear(10000, 10);
    }

    @Test
    public void startingBalanceMatchesConstructor() {
        assertEquals(10000, newAccount().startingBalance());
    }

    @Test
    public void interestRateMatchesConstructor() {
        assertEquals(10, newAccount().interestRate());
    }

    @Test
    public void endingBalanceAppliesInterestRate() {
        assertEquals(11000, newAccount().endingBalance());
    }

    @Test
    public void nextYearsStartingBalanceEqualsThisYearsEndingBalance() {
        SavingsAccountYear thisYear = newAccount();
        assertEquals(thisYear.nextYear().startingBalance(), thisYear.endingBalance());
    }

    @Test
    public void nextYearsInterestRateEqualsThisYearsInterestRate() {
        SavingsAccountYear thisYear = newAccount();
        assertEquals(thisYear.interestRate(), thisYear.nextYear().interestRate());
    }
}
