/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

import org.junit.Test;

import static org.junit.Assert.*;

public class _SavingsAccountTest {

    @Test
    public void depositAndWithdrawal() {
        SavingsAccountYear account = new SavingsAccountYear();
        account.deposit(100);
        assertEquals("after deposit", 100, account.balance());
        account.withdraw(50);
        assertEquals("after withdrawal", 50, account.balance());
    }

    @Test
    public void negativeBalanceIsJustFine() {
        SavingsAccountYear account = new SavingsAccountYear();
        account.withdraw(100);
        assertEquals(-100, account.balance());
    }

    @Test
    public void nextYear() {
        SavingsAccountYear account = new SavingsAccountYear();
        account.deposit(10000);
        SavingsAccountYear nextYear = account.nextYear(10);
        assertEquals(11000, nextYear.balance());
    }

    @Test
    public void endingBalance() {
        SavingsAccountYear account = new SavingsAccountYear(10000, 10);
        assertEquals(11000, account.endingBalance());
    }

}
