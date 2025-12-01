package com.vaibhav.designpattern.decorator.pizza

class DiscountDecorator(
    private val pizza: Pizza,
    private val discountPercent: Double
) : PizzaToppingDecorator() {

    override val description: String
        get() = pizza.description + ", Discount"

    override val size: Size
        get() = pizza.size

    override fun getLineItems(): List<Pair<String, Double>> {
        val items = pizza.getLineItems()
        val totalCost = items.sumOf { it.second }
        val discountAmount = totalCost * discountPercent
        return items + ("Discount (${(discountPercent * 100).toInt()}%)" to -discountAmount)
    }
}
