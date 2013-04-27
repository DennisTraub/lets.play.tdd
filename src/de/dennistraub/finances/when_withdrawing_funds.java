/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class when_withdrawing_funds {

    private SavingsAccountYear newSUT() {
        return new SavingsAccountYear(10000, 3000, 10, .25);
    }

    @Test
    public void withdrawn_funds_accumulate_no_gains() {
        SavingsAccountYear year = newSUT();
        year.withdraw(1000);
        assertEquals(9900, year.endingBalance());
    }

    @Test
    public void withdrawal_is_deducted_from_principal() {
        SavingsAccountYear year = newSUT();
        year.withdraw(2000);
        assertEquals("ending principal", 1000, year.endingPrincipal());
    }

    @Test
    public void principal_never_goes_below_zero() {
        SavingsAccountYear year = newSUT();
        year.withdraw(4000);
        assertEquals("ending principal", 0, year.endingPrincipal());
    }

    @Test
    public void multiple_withdrawals_add_up() {
        SavingsAccountYear year = newSUT();
        year.withdraw(1000);
        year.withdraw(2000);
        assertEquals(3000, year.totalWithdrawn());
    }
}
