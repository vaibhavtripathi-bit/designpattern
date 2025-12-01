package com.vaibhav.designpattern.decorator.pizza

class Cheese(private val pizza: Pizza) : PizzaToppingDecorator() {
    override val size: Size get() = pizza.size
    override val description: String
        get() = pizza.description + ", Cheese"

    override fun cost(): Double {
        return 0.50 + pizza.cost()
    }
}

class Pepperoni(private val pizza: Pizza) : PizzaToppingDecorator() {
    override val size: Size get() = pizza.size
    override val description: String
        get() = pizza.description + ", Pepperoni"

    override fun cost(): Double {
        return 1.50 + pizza.cost()
    }
}

class Mushroom(private val pizza: Pizza) : PizzaToppingDecorator() {
    override val size: Size get() = pizza.size
    override val description: String
        get() = pizza.description + ", Mushroom"

    override fun cost(): Double {
        return 0.75 + pizza.cost()
    }
}
