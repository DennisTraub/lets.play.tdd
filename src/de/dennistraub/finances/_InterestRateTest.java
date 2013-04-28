/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

import org.junit.Test;

import static org.junit.Assert.*;

public class _InterestRateTest {

    @Test
    public void calculate_interest_for_amount() {
        InterestRate rate = new InterestRate(10);
        assertEquals(100, rate.interestFor(1000));
    }

    @Test
    public void valueObject() {
        InterestRate rate1a = new InterestRate(33);
        InterestRate rate1b = new InterestRate(33);
        InterestRate rate2 = new InterestRate(40);

        assertEquals("33%", rate1a.toString());
        assertTrue(rate1a.equals(rate1b));
        assertFalse(rate1a.equals(rate2));
        assertTrue(rate1a.hashCode() == rate1b.hashCode());
    }

}
