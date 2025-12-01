package com.vaibhav.designpattern.decorator.pizza

/**
 * Abstract base class for all pizzas.
 */
enum class Size {
    SMALL, MEDIUM, LARGE
}

enum class Crust {
    THIN, THICK, STUFFED
}

/**
 * Abstract base class for all pizzas.
 */
abstract class Pizza {
    open val description: String = "Unknown Pizza"
    open val size: Size = Size.MEDIUM

    abstract fun getLineItems(): List<Pair<String, Double>>

    open fun cost(): Double {
        return getLineItems().sumOf { it.second }
    }
}

class BasePizza(
    override val size: Size = Size.MEDIUM,
    val crust: Crust = Crust.THIN
) : Pizza() {
    override val description: String
        get() = "${size.name.lowercase().replaceFirstChar { it.uppercase() }} ${crust.name.lowercase().replaceFirstChar { it.uppercase() }} Crust Pizza"

    override fun getLineItems(): List<Pair<String, Double>> {
        val baseCost = when (size) {
            Size.SMALL -> 3.00
            Size.MEDIUM -> 4.00
            Size.LARGE -> 5.00
        }
        val crustCost = when (crust) {
            Crust.THIN -> 0.00
            Crust.THICK -> 1.00
            Crust.STUFFED -> 2.00
        }
        return listOf(description to (baseCost + crustCost))
    }
}
