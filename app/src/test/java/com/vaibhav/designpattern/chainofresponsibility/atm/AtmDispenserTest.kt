package com.vaibhav.designpattern.chainofresponsibility.atm

import org.junit.Test

class AtmDispenserTest {
    @Test
    fun `test Dispense 5300`() {
        // Expected: 2x2000, 2x500, 1x200, 1x100
        AtmDispenserChain.withdraw(5300)
    }

    @Test
    fun `test Dispense 1200`() {
        // Expected: 2x500, 1x200
        AtmDispenserChain.withdraw(1200)
    }
    
    @Test
    fun `test Invalid Amount`() {
        AtmDispenserChain.withdraw(1250)
    }
}
