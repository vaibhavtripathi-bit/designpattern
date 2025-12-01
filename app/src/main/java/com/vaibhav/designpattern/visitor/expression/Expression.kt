package com.vaibhav.designpattern.visitor.expression

/**
 * The Element interface.
 */
interface Expression {
    fun accept(visitor: ExpressionVisitor)
}

data class Number(val value: Int) : Expression {
    override fun accept(visitor: ExpressionVisitor) {
        visitor.visit(this)
    }
}

data class Addition(val left: Expression, val right: Expression) : Expression {
    override fun accept(visitor: ExpressionVisitor) {
        visitor.visit(this)
    }
}

data class Multiplication(val left: Expression, val right: Expression) : Expression {
    override fun accept(visitor: ExpressionVisitor) {
        visitor.visit(this)
    }
}
