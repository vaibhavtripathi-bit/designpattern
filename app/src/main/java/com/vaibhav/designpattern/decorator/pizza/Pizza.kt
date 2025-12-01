package com.vaibhav.designpattern.decorator.pizza

/**
 * Abstract base class for all pizzas.
 */
enum class Size {
    SMALL, MEDIUM, LARGE
}

/**
 * Abstract base class for all pizzas.
 */
abstract class Pizza {
    open val description: String = "Unknown Pizza"
    open val size: Size = Size.MEDIUM

    abstract fun cost(): Double
}

class PlainPizza(override val size: Size = Size.MEDIUM) : Pizza() {
    override val description: String = "Plain Pizza"

    override fun cost(): Double {
        return when (size) {
            Size.SMALL -> 3.00
            Size.MEDIUM -> 4.00
            Size.LARGE -> 5.00
        }
    }
}
