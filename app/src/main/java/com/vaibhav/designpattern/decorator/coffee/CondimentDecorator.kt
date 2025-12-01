package com.vaibhav.designpattern.decorator.coffee

/**
 * Abstract decorator class.
 * It must extend Beverage to be interchangeable.
 */
abstract class CondimentDecorator : Beverage() {
    abstract override val description: String
}
