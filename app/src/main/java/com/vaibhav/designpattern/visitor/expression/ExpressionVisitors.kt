package com.vaibhav.designpattern.visitor.expression

class EvaluatorVisitor : ExpressionVisitor {
    var result: Int = 0
    // We use a stack or just recursion return values? 
    // Since the visitor returns void, we need to store state.
    // For a simple evaluator, a stack is robust, but for this simple example, 
    // we can use a temporary variable if we visit children first (post-order).
    // Actually, a stack is safer for nested expressions.
    
    private val stack = java.util.Stack<Int>()

    override fun visit(number: Number) {
        stack.push(number.value)
    }

    override fun visit(addition: Addition) {
        addition.left.accept(this)
        addition.right.accept(this)
        val r = stack.pop()
        val l = stack.pop()
        stack.push(l + r)
    }

    override fun visit(multiplication: Multiplication) {
        multiplication.left.accept(this)
        multiplication.right.accept(this)
        val r = stack.pop()
        val l = stack.pop()
        stack.push(l * r)
    }
    
    fun getEvaluation(): Int {
        return if (stack.isNotEmpty()) stack.peek() else 0
    }
}

class PrinterVisitor : ExpressionVisitor {
    private val sb = StringBuilder()

    override fun visit(number: Number) {
        sb.append(number.value)
    }

    override fun visit(addition: Addition) {
        sb.append("(")
        addition.left.accept(this)
        sb.append(" + ")
        addition.right.accept(this)
        sb.append(")")
    }

    override fun visit(multiplication: Multiplication) {
        sb.append("(")
        multiplication.left.accept(this)
        sb.append(" * ")
        multiplication.right.accept(this)
        sb.append(")")
    }

    fun getExpressionString(): String = sb.toString()
}
