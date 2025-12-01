package com.vaibhav.designpattern.decorator.pizza

class DiscountDecorator(
    private val pizza: Pizza,
    private val discountPercent: Double
) : PizzaToppingDecorator() {

    override val description: String
        get() = pizza.description + ", Discount"

    override val size: Size
        get() = pizza.size

    override fun cost(): Double {
        return pizza.cost() * (1.0 - discountPercent)
    }
}
