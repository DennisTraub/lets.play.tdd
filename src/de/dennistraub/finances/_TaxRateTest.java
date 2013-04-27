/* Copyright 2013 by Dennis Traub */
package de.dennistraub.finances;

import org.junit.Test;

import static org.junit.Assert.*;

public class _TaxRateTest {

    @Test
    public void simpleTaxAppliesTaxRateToAmount() {
        TaxRate taxRate = new TaxRate(25);
        assertEquals(250, taxRate.simpleTaxFor(1000));
    }

    @Test
    public void compundTaxIsTheAmountOfTaxThatIsIncurredIfYouPayTaxOnTax() {
        TaxRate taxRate = new TaxRate(25);
        assertEquals(333, taxRate.compoundTaxFor(1000));
    }

}
