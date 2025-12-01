package com.vaibhav.designpattern.decorator.pizza

/**
 * Abstract decorator class for pizza toppings.
 * It must extend Pizza to be interchangeable.
 */
abstract class PizzaToppingDecorator : Pizza() {
    abstract override val description: String
}
