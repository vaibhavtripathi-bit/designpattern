package com.vaibhav.designpattern.visitor.shape

/**
 * The Element interface.
 */
interface Shape {
    fun accept(visitor: ShapeVisitor)
}

data class Circle(val radius: Double) : Shape {
    override fun accept(visitor: ShapeVisitor) {
        visitor.visit(this)
    }
}

data class Rectangle(val width: Double, val height: Double) : Shape {
    override fun accept(visitor: ShapeVisitor) {
        visitor.visit(this)
    }
}

data class Triangle(val a: Double, val b: Double, val c: Double) : Shape {
    override fun accept(visitor: ShapeVisitor) {
        visitor.visit(this)
    }
}
