package com.vaibhav.designpattern.decorator.coffee

import org.junit.Test
import org.junit.Assert.assertEquals

class CoffeeDecoratorTest {

    @Test
    fun `test Espresso with no condiments`() {
        val beverage: Beverage = Espresso()
        assertEquals("Espresso", beverage.description)
        assertEquals(1.99, beverage.cost(), 0.01)
    }

    @Test
    fun `test HouseBlend with Mocha and Whip`() {
        var beverage: Beverage = HouseBlend()
        beverage = Mocha(beverage)
        beverage = Whip(beverage)

        // HouseBlend (0.89) + Mocha (0.20) + Whip (0.10) = 1.19
        assertEquals("House Blend Coffee, Mocha, Whip", beverage.description)
        assertEquals(1.19, beverage.cost(), 0.01)
    }

    @Test
    fun `test Espresso with Soy and Double Mocha`() {
        var beverage: Beverage = Espresso()
        beverage = Soy(beverage)
        beverage = Mocha(beverage)
        beverage = Mocha(beverage)

        // Espresso (1.99) + Soy (0.15) + Mocha (0.20) + Mocha (0.20) = 2.54
        assertEquals("Espresso, Soy, Mocha, Mocha", beverage.description)
        assertEquals(2.54, beverage.cost(), 0.01)
    }
}
