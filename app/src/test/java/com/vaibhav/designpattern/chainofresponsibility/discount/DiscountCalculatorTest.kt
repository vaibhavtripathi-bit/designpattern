package com.vaibhav.designpattern.chainofresponsibility.discount

import org.junit.Test
import org.junit.Assert.assertEquals

class DiscountCalculatorTest {
    @Test
    fun `test Cumulative Discount`() {
        // 100 - 10% = 90
        // 90 - 5% = 85.5
        // 85.5 - 20% = 68.4
        val finalPrice = DiscountChain.calculateFinalPrice(100.0)
        assertEquals(68.4, finalPrice, 0.01)
    }
}
