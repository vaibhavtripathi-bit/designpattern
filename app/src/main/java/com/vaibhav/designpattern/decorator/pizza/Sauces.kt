package com.vaibhav.designpattern.decorator.pizza

class TomatoSauce(private val pizza: Pizza) : PizzaToppingDecorator() {
    override val size: Size get() = pizza.size
    override val description: String
        get() = pizza.description + ", Tomato Sauce"

    override fun getLineItems(): List<Pair<String, Double>> {
        return pizza.getLineItems() + ("Tomato Sauce" to 0.00) // Free sauce
    }
}

class BBQSauce(private val pizza: Pizza) : PizzaToppingDecorator() {
    override val size: Size get() = pizza.size
    override val description: String
        get() = pizza.description + ", BBQ Sauce"

    override fun getLineItems(): List<Pair<String, Double>> {
        return pizza.getLineItems() + ("BBQ Sauce" to 0.50)
    }
}
