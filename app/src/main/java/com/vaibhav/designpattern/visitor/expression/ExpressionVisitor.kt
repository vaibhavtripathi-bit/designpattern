package com.vaibhav.designpattern.visitor.expression

/**
 * The Visitor interface.
 */
interface ExpressionVisitor {
    fun visit(number: Number)
    fun visit(addition: Addition)
    fun visit(multiplication: Multiplication)
}
