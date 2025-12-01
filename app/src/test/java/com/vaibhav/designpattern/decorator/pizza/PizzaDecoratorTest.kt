package com.vaibhav.designpattern.decorator.pizza

import org.junit.Test
import org.junit.Assert.assertEquals

class PizzaDecoratorTest {

    @Test
    fun `test PlainPizza with no toppings`() {
        val pizza: Pizza = PlainPizza()
        assertEquals("Plain Pizza", pizza.description)
        assertEquals(4.00, pizza.cost(), 0.01)
    }

    @Test
    fun `test PlainPizza sizes`() {
        val smallPizza = PlainPizza(Size.SMALL)
        assertEquals(3.00, smallPizza.cost(), 0.01)

        val largePizza = PlainPizza(Size.LARGE)
        assertEquals(5.00, largePizza.cost(), 0.01)
    }

    @Test
    fun `test Margherita sizes`() {
        val smallMargherita = Margherita(Size.SMALL)
        assertEquals("Margherita Pizza", smallMargherita.description)
        assertEquals(5.00, smallMargherita.cost(), 0.01)

        val mediumMargherita = Margherita(Size.MEDIUM)
        assertEquals(7.00, mediumMargherita.cost(), 0.01)

        val largeMargherita = Margherita(Size.LARGE)
        assertEquals(9.00, largeMargherita.cost(), 0.01)
    }

    @Test
    fun `test PlainPizza with Cheese`() {
        var pizza: Pizza = PlainPizza()
        pizza = Cheese(pizza)

        // Plain Pizza (4.00) + Cheese (0.50) = 4.50
        assertEquals("Plain Pizza, Cheese", pizza.description)
        assertEquals(4.50, pizza.cost(), 0.01)
    }

    @Test
    fun `test Large PlainPizza with Cheese`() {
        var pizza: Pizza = PlainPizza(Size.LARGE)
        pizza = Cheese(pizza)

        // Large Plain Pizza (5.00) + Cheese (0.50) = 5.50
        assertEquals("Plain Pizza, Cheese", pizza.description)
        assertEquals(5.50, pizza.cost(), 0.01)
    }

    @Test
    fun `test DiscountDecorator`() {
        var pizza: Pizza = PlainPizza(Size.MEDIUM) // 4.00
        pizza = Cheese(pizza) // 4.50
        pizza = DiscountDecorator(pizza, 0.10) // 10% off

        // 4.50 * 0.9 = 4.05
        assertEquals("Plain Pizza, Cheese, Discount", pizza.description)
        assertEquals(4.05, pizza.cost(), 0.01)
    }
    
    @Test
    fun `test Complex Order`() {
        // Large Margherita (9.00) + Double Cheese (0.50 * 2) + Pepperoni (1.50) = 11.50
        // Discount 20% = 11.50 * 0.8 = 9.20
        var pizza: Pizza = Margherita(Size.LARGE)
        pizza = Cheese(pizza)
        pizza = Cheese(pizza)
        pizza = Pepperoni(pizza)
        pizza = DiscountDecorator(pizza, 0.20)

        assertEquals("Margherita Pizza, Cheese, Cheese, Pepperoni, Discount", pizza.description)
        assertEquals(9.20, pizza.cost(), 0.01)
    }
}
