/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

import org.junit.Test;

import static org.junit.Assert.*;

public class _DollarsTest {

    @Test
    public void addition() {
        Dollars dollars1 = new Dollars(1000);
        Dollars dollars2 = new Dollars(2000);
        Dollars sum = dollars1.add(dollars2);
        assertEquals(3000, sum.amount());
    }

    @Test
    public void subtraction() {
        Dollars dollars1 = new Dollars(3000);
        Dollars dollars2 = new Dollars(1000);
        Dollars sum = dollars1.subtract(dollars2);
        assertEquals(2000, sum.amount());
    }

    @Test
    public void multiplication() {
        Dollars dollars = new Dollars(3000);
        Dollars product = dollars.multiplyBy(2);
        assertEquals(6000, product.amount());
    }

    @Test
    public void division() {
        Dollars dollars = new Dollars(3000);
        Dollars quotient = dollars.divideBy(2);
        assertEquals(1500, quotient.amount());
    }

    @Test
    public void valueObject() {
        Dollars dollars1a = new Dollars(2000);
        Dollars dollars1b = new Dollars(2000);
        Dollars dollars2 = new Dollars(3000);

        assertEquals("$2000", dollars1a.toString());
        assertTrue(dollars1a.equals(dollars1b));
        assertFalse(dollars1a.equals(dollars2));
        assertTrue(dollars1a.hashCode() == dollars1b.hashCode());
    }
}
