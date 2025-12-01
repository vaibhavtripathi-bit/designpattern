package com.vaibhav.designpattern.decorator.shape

import org.junit.Test
import org.junit.Assert.assertEquals

class ShapeDecoratorTest {

    @Test
    fun `test Circle with no decorators`() {
        val circle: Shape = Circle()
        val log = mutableListOf<String>()
        circle.draw(log)
        
        assertEquals(listOf("Circle"), log)
    }

    @Test
    fun `test Circle with Red Border`() {
        val circle: Shape = RedShapeDecorator(Circle())
        val log = mutableListOf<String>()
        circle.draw(log)

        // Order: Circle drawn first, then Red Border added
        assertEquals(listOf("Circle", "Red Border"), log)
    }

    @Test
    fun `test Rectangle with Transparency and Red Border`() {
        // Wrap Rectangle with RedShapeDecorator, then wrap that with TransparentShapeDecorator
        val rectangle: Shape = TransparentShapeDecorator(RedShapeDecorator(Rectangle()))
        val log = mutableListOf<String>()
        rectangle.draw(log)

        // Order:
        // 1. TransparentShapeDecorator.draw() called
        // 2. setTransparency() -> adds "Transparency set"
        // 3. super.draw() (RedShapeDecorator) called
        // 4. RedShapeDecorator.draw() called
        // 5. super.draw() (Rectangle) called -> adds "Rectangle"
        // 6. setRedBorder() -> adds "Red Border"
        
        assertEquals(listOf("Transparency set", "Rectangle", "Red Border"), log)
    }
}
