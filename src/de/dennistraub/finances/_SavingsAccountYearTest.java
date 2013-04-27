/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

import org.junit.Test;

import static org.junit.Assert.*;

public class _SavingsAccountYearTest {

    private SavingsAccountYear newAccount() {
        return new SavingsAccountYear(10000, 10000, 10);
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
        assertEquals(11000, newAccount().endingBalance(25));
    }

    @Test
    public void nextYearsStartingBalanceEqualsThisYearsEndingBalance() {
        SavingsAccountYear thisYear = newAccount();
        assertEquals(thisYear.nextYear(25).startingBalance(), thisYear.endingBalance(25));
    }

    @Test
    public void nextYearsInterestRateEqualsThisYearsInterestRate() {
        SavingsAccountYear thisYear = newAccount();
        assertEquals(thisYear.interestRate(), thisYear.nextYear(25).interestRate());
    }

    @Test
    public void withdrawingFundsOccursAtTheBeginningOfTheYear() {
        SavingsAccountYear year = new SavingsAccountYear(10000, 10000, 10);
        year.withdraw(1000);
        assertEquals(9900, year.endingBalance(25));
    }

    @Test
    public void startingPrincipal() {
        SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
        assertEquals(3000, year.startingPrincipal());
    }

    @Test
    public void endingPrincipal() {
        SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
        year.withdraw(2000);
        assertEquals("ending principal", 1000, year.endingPrincipal());
    }

    @Test
    public void endingPrincipalNeverGoesBelowZero() {
        SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
        year.withdraw(4000);
        assertEquals("ending principal", 0, year.endingPrincipal());
    }

    @Test
    public void multipleWithdrawalsInAYear() {
        SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
        year.withdraw(1000);
        year.withdraw(2000);
        assertEquals(3000, year.totalWithdrawn());
    }

    @Test
    public void capitalGainsWithdrawn() {
        SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
        year.withdraw(1000);
        assertEquals(0, year.capitalGainsWithdrawn());
        year.withdraw(3000);
        assertEquals(1000, year.capitalGainsWithdrawn());
    }

    @Test
    public void capitalGainsTaxIncurred() {
        SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
        year.withdraw(5000);
        assertEquals(500, year.capitalGainsTaxIncurred(25));
    }

    @Test
    public void capitalGainstaxIsIncludedInEndingBalance() {
        SavingsAccountYear year = new SavingsAccountYear(10000, 3000, 10);
        year.withdraw(5000);
        assertEquals(500, year.capitalGainsTaxIncurred(25));
        assertEquals(4950, year.endingBalance(25));
    }

//    @Test
//    public void withdrawingMoreThanPrincipalIncursCapitalGainsTax() {
//        SavingsAccountYear year = new SavingsAccountYear(10000, 7000, 10);
//        year.withdraw(3000);
//        assertEquals(7700, year.endingBalance());
//        year.withdraw(5000);
//        assertEquals(2200 - (1250), year.endingBalance(), 0.1);
//    }
}
