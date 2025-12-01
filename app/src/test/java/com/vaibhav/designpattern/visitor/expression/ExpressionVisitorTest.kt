package com.vaibhav.designpattern.visitor.expression

import org.junit.Test
import org.junit.Assert.assertEquals

class ExpressionVisitorTest {

    @Test
    fun `test Evaluation`() {
        // Expression: (5 + 10) * 2 = 30
        val five = Number(5)
        val ten = Number(10)
        val two = Number(2)
        
        val addition = Addition(five, ten)
        val multiplication = Multiplication(addition, two)

        val evaluator = EvaluatorVisitor()
        multiplication.accept(evaluator)

        assertEquals(30, evaluator.getEvaluation())
    }

    @Test
    fun `test Printing`() {
        // Expression: (5 + (3 * 4))
        val five = Number(5)
        val three = Number(3)
        val four = Number(4)
        
        val mult = Multiplication(three, four)
        val add = Addition(five, mult)

        val printer = PrinterVisitor()
        add.accept(printer)

        val output = printer.getExpressionString()
        println("Expression: $output")
        
        assertEquals("(5 + (3 * 4))", output)
    }
    
    @Test
    fun `test Complex Expression`() {
        // ((2 * 3) + (4 * 5)) = 6 + 20 = 26
        val left = Multiplication(Number(2), Number(3))
        val right = Multiplication(Number(4), Number(5))
        val root = Addition(left, right)
        
        val evaluator = EvaluatorVisitor()
        root.accept(evaluator)
        assertEquals(26, evaluator.getEvaluation())
        
        val printer = PrinterVisitor()
        root.accept(printer)
        assertEquals("((2 * 3) + (4 * 5))", printer.getExpressionString())
    }
}
