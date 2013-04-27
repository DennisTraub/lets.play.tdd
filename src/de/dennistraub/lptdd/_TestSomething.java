/* Copyright 2013 by Dennis Traub */
package de.dennistraub.lptdd;

import org.junit.Test;

import static org.junit.Assert.*;

public class _TestSomething {
    @Test
    public void nothing() {
        SavingsAccount account = new SavingsAccount();
        account.deposit(100);
        assertEquals("after deposit", 100, account.balance());
        account.withdraw(50);
        assertEquals("after withdrawal", 50, account.balance());
    }
}
