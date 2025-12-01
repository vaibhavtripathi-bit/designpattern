package com.vaibhav.designpattern.memento.canvas

import org.junit.Test
import org.junit.Assert.assertEquals

class CanvasTest {

    @Test
    fun `test Undo Shape Addition`() {
        val canvas = Canvas()
        val history = History()

        // Add Circle
        canvas.addShape(Shape("Circle", "Red", 10, 10))
        history.push(canvas.save())

        // Add Rectangle
        canvas.addShape(Shape("Rectangle", "Blue", 20, 20))
        history.push(canvas.save())

        // Add Triangle
        canvas.addShape(Shape("Triangle", "Green", 30, 30))

        // Current state: 3 shapes
        assertEquals(3, canvas.getShapes().size)

        // Undo Triangle -> should have 2 shapes (Circle, Rectangle)
        val state1 = history.pop()
        if (state1 != null) {
            canvas.restore(state1)
        }
        assertEquals(2, canvas.getShapes().size)
        assertEquals("Rectangle", canvas.getShapes().last().type)

        // Undo Rectangle -> should have 1 shape (Circle)
        val state2 = history.pop()
        if (state2 != null) {
            canvas.restore(state2)
        }
        assertEquals(1, canvas.getShapes().size)
        assertEquals("Circle", canvas.getShapes().last().type)
    }
}
