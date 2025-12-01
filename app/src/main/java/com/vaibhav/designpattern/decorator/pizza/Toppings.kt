package com.vaibhav.designpattern.decorator.pizza

class Cheese(private val pizza: Pizza) : PizzaToppingDecorator() {
    override val size: Size get() = pizza.size
    override val description: String
        get() = pizza.description + ", Cheese"

    override fun getLineItems(): List<Pair<String, Double>> {
        return pizza.getLineItems() + ("Cheese" to 0.50)
    }
}

class Pepperoni(private val pizza: Pizza) : PizzaToppingDecorator() {
    override val size: Size get() = pizza.size
    override val description: String
        get() = pizza.description + ", Pepperoni"

    override fun getLineItems(): List<Pair<String, Double>> {
        return pizza.getLineItems() + ("Pepperoni" to 1.50)
    }
}

class Mushroom(private val pizza: Pizza) : PizzaToppingDecorator() {
    override val size: Size get() = pizza.size
    override val description: String
        get() = pizza.description + ", Mushroom"

    override fun getLineItems(): List<Pair<String, Double>> {
        return pizza.getLineItems() + ("Mushroom" to 0.75)
    }
}
