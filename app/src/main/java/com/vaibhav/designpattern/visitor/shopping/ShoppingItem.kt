package com.vaibhav.designpattern.visitor.shopping

/**
 * The Element interface.
 */
interface ShoppingItem {
    fun accept(visitor: ShoppingCartVisitor)
}

data class Book(val price: Int, val weightKg: Double) : ShoppingItem {
    override fun accept(visitor: ShoppingCartVisitor) {
        visitor.visit(this)
    }
}

data class Fruit(val pricePerKg: Int, val weightKg: Double) : ShoppingItem {
    override fun accept(visitor: ShoppingCartVisitor) {
        visitor.visit(this)
    }
}
