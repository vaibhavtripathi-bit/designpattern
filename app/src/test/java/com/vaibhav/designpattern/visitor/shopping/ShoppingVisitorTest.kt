package com.vaibhav.designpattern.visitor.shopping

import org.junit.Test
import org.junit.Assert.assertEquals

class ShoppingVisitorTest {

    @Test
    fun `test Price Calculation`() {
        val cart = listOf(
            Book(price = 500, weightKg = 1.0),
            Fruit(pricePerKg = 100, weightKg = 2.5) // 250
        )

        val priceVisitor = PriceVisitor()
        cart.forEach { it.accept(priceVisitor) }

        assertEquals(750.0, priceVisitor.totalPrice, 0.01)
    }

    @Test
    fun `test Weight Calculation`() {
        val cart = listOf(
            Book(price = 500, weightKg = 1.2),
            Fruit(pricePerKg = 100, weightKg = 2.0)
        )

        val weightVisitor = WeightVisitor()
        cart.forEach { it.accept(weightVisitor) }

        assertEquals(3.2, weightVisitor.totalWeight, 0.01)
    }
}
