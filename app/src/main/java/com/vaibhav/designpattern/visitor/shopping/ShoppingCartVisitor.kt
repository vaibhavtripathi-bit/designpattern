package com.vaibhav.designpattern.visitor.shopping

/**
 * The Visitor interface.
 */
interface ShoppingCartVisitor {
    fun visit(book: Book)
    fun visit(fruit: Fruit)
}
