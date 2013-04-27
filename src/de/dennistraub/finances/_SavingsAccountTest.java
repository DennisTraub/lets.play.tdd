/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

import org.junit.Test;

import static org.junit.Assert.*;

public class _SavingsAccountTest {

    @Test
    public void depositAndWithdrawal() {
        SavingsAccount account = new SavingsAccount();
        account.deposit(100);
        assertEquals("after deposit", 100, account.balance());
        account.withdraw(50);
        assertEquals("after withdrawal", 50, account.balance());
    }

    @Test
    public void negativeBalanceIsJustFine() {
        SavingsAccount account = new SavingsAccount();
        account.withdraw(100);
        assertEquals(-100, account.balance());
    }

    @Test
    public void nextYear() {
        SavingsAccount account = new SavingsAccount();
        account.deposit(10000);
        SavingsAccount nextYear = account.nextYear(10);
        assertEquals(11000, nextYear.balance());
    }
}
