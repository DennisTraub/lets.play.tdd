/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

import org.junit.Test;

import static org.junit.Assert.*;

public class _TaxRateTest {

    @Test
    public void compound_tax_applies_tax_rate_to_dollars() {
        TaxRate taxRate = new TaxRate(25);
        assertEquals(
                new Dollars(333), taxRate.compoundTaxFor(new Dollars(1000))
        );
    }

    @Test
    public void valueObject() {
        TaxRate rate1a = new TaxRate(33);
        TaxRate rate1b = new TaxRate(33);
        TaxRate rate2 = new TaxRate(40);

        assertEquals("33.0%", rate1a.toString());
        assertTrue(rate1a.equals(rate1b));
        assertFalse(rate1a.equals(rate2));
        assertTrue(rate1a.hashCode() == rate1b.hashCode());
    }

}
