package com.vaibhav.designpattern.decorator.pizza

import org.junit.Test
import org.junit.Assert.assertEquals

class PizzaDecoratorTest {

    @Test
    fun `test BasePizza with no toppings`() {
        val pizza: Pizza = BasePizza()
        assertEquals("Medium Thin Crust Pizza", pizza.description)
        assertEquals(4.00, pizza.cost(), 0.01)
        
        val items = pizza.getLineItems()
        assertEquals(1, items.size)
        assertEquals("Medium Thin Crust Pizza" to 4.00, items[0])
    }

    @Test
    fun `test BasePizza sizes and crusts`() {
        val smallThickPizza = BasePizza(Size.SMALL, Crust.THICK)
        // Small (3.00) + Thick (1.00) = 4.00
        assertEquals("Small Thick Crust Pizza", smallThickPizza.description)
        assertEquals(4.00, smallThickPizza.cost(), 0.01)

        val largeStuffedPizza = BasePizza(Size.LARGE, Crust.STUFFED)
        // Large (5.00) + Stuffed (2.00) = 7.00
        assertEquals("Large Stuffed Crust Pizza", largeStuffedPizza.description)
        assertEquals(7.00, largeStuffedPizza.cost(), 0.01)
    }

    @Test
    fun `test Margherita sizes`() {
        val smallMargherita = Margherita(Size.SMALL)
        assertEquals("Margherita Pizza", smallMargherita.description)
        assertEquals(5.00, smallMargherita.cost(), 0.01)
    }

    @Test
    fun `test BasePizza with Cheese and Sauce`() {
        var pizza: Pizza = BasePizza()
        pizza = Cheese(pizza)
        pizza = TomatoSauce(pizza)

        // Medium Thin (4.00) + Cheese (0.50) + Tomato Sauce (0.00) = 4.50
        assertEquals("Medium Thin Crust Pizza, Cheese, Tomato Sauce", pizza.description)
        assertEquals(4.50, pizza.cost(), 0.01)
        
        val items = pizza.getLineItems()
        assertEquals(3, items.size)
        assertEquals("Medium Thin Crust Pizza" to 4.00, items[0])
        assertEquals("Cheese" to 0.50, items[1])
        assertEquals("Tomato Sauce" to 0.00, items[2])
    }

    @Test
    fun `test DiscountDecorator with Line Items`() {
        var pizza: Pizza = BasePizza(Size.MEDIUM, Crust.THIN) // 4.00
        pizza = Cheese(pizza) // 4.50
        pizza = DiscountDecorator(pizza, 0.10) // 10% off

        // 4.50 * 0.9 = 4.05
        assertEquals("Medium Thin Crust Pizza, Cheese, Discount", pizza.description)
        assertEquals(4.05, pizza.cost(), 0.01)
        
        val items = pizza.getLineItems()
        assertEquals(3, items.size)
        assertEquals("Discount (10%)" to -0.45, items[2])
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

    @Test
    fun `test Real World Customization`() {
        // Scenario: User builds a "Meat Lover's Delight" with a coupon
        // 1. Start with a Large Stuffed Crust Pizza (Base: 5.00 + 2.00 = 7.00)
        var myPizza: Pizza = BasePizza(Size.LARGE, Crust.STUFFED)

        // 2. Add Tomato Sauce (Free)
        myPizza = TomatoSauce(myPizza)

        // 3. Add Extra Cheese (0.50)
        myPizza = Cheese(myPizza)

        // 4. Add Pepperoni (1.50)
        myPizza = Pepperoni(myPizza)

        // 5. Add BBQ Sauce on top (0.50)
        myPizza = BBQSauce(myPizza)

        // 6. Apply "Summer Sale" 10% Discount
        // Current Total: 7.00 + 0.00 + 0.50 + 1.50 + 0.50 = 9.50
        // Discount: 9.50 * 0.10 = 0.95
        // Final Cost: 9.50 - 0.95 = 8.55
        myPizza = DiscountDecorator(myPizza, 0.10)

        // Verify Description
        val expectedDescription = "Large Stuffed Crust Pizza, Tomato Sauce, Cheese, Pepperoni, BBQ Sauce, Discount"
        assertEquals(expectedDescription, myPizza.description)

        // Verify Cost
        assertEquals(8.55, myPizza.cost(), 0.01)

        // Print Receipt for User Demo
        println("=== CUSTOMER RECEIPT ===")
        val lineItems = myPizza.getLineItems()
        lineItems.forEach { (item, price) ->
            println("${item.padEnd(40)} : $${String.format("%.2f", price)}")
        }
        println("------------------------------------------")
        println("${"TOTAL".padEnd(40)} : $${String.format("%.2f", myPizza.cost())}")
        println("========================")
    }
}
