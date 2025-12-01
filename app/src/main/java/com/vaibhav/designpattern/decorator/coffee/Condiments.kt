package com.vaibhav.designpattern.decorator.coffee

class Mocha(private val beverage: Beverage) : CondimentDecorator() {
    override val description: String
        get() = beverage.description + ", Mocha"

    override fun cost(): Double {
        return 0.20 + beverage.cost()
    }
}

class Whip(private val beverage: Beverage) : CondimentDecorator() {
    override val description: String
        get() = beverage.description + ", Whip"

    override fun cost(): Double {
        return 0.10 + beverage.cost()
    }
}

class Soy(private val beverage: Beverage) : CondimentDecorator() {
    override val description: String
        get() = beverage.description + ", Soy"

    override fun cost(): Double {
        return 0.15 + beverage.cost()
    }
}
