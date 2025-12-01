package com.vaibhav.designpattern.visitor.shape

/**
 * The Visitor interface.
 */
interface ShapeVisitor {
    fun visit(circle: Circle)
    fun visit(rectangle: Rectangle)
    fun visit(triangle: Triangle)
}
