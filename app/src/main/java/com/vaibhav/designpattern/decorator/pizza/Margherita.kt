package com.vaibhav.designpattern.decorator.pizza

class Margherita(override val size: Size = Size.MEDIUM) : Pizza() {
    override val description: String = "Margherita Pizza"

    override fun cost(): Double {
        return when (size) {
            Size.SMALL -> 5.00
            Size.MEDIUM -> 7.00
            Size.LARGE -> 9.00
        }
    }
}
