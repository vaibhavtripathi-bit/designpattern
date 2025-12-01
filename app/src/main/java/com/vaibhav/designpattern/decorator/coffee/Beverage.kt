package com.vaibhav.designpattern.decorator.coffee

/**
 * Abstract base class for all beverages.
 */
abstract class Beverage {
    open val description: String = "Unknown Beverage"

    abstract fun cost(): Double
}

class Espresso : Beverage() {
    override val description: String = "Espresso"

    override fun cost(): Double {
        return 1.99
    }
}

class HouseBlend : Beverage() {
    override val description: String = "House Blend Coffee"

    override fun cost(): Double {
        return 0.89
    }
}
