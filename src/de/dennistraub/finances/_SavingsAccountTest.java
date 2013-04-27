/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

import org.junit.Test;

import static org.junit.Assert.*;

public class _SavingsAccountTest {

    @Test
    public void startingBalance() {
        SavingsAccountYear account = new SavingsAccountYear(10000, 10);
        assertEquals(10000, account.startingBalance());
    }

    @Test
    public void interestRate() {
        SavingsAccountYear account = new SavingsAccountYear(10000, 10);
        assertEquals(10, account.interestRate());
    }

    @Test
    public void endingBalance() {
        SavingsAccountYear account = new SavingsAccountYear(10000, 10);
        assertEquals(11000, account.endingBalance());
    }

    @Test
    public void nextYearsStartingBalanceShouldEqualThisYearsEndingBalance() {
        SavingsAccountYear thisYear = new SavingsAccountYear(10000, 10);
        assertEquals(thisYear.nextYear().startingBalance(), thisYear.endingBalance());
    }

    @Test
    public void nextYearsInterestRateEqualsThisYearsInterestRate() {
        SavingsAccountYear thisYear = new SavingsAccountYear(10000, 10);
        assertEquals(thisYear.interestRate(), thisYear.nextYear().interestRate());
    }
}
