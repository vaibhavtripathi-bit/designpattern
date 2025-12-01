package com.vaibhav.designpattern.visitor.shape

import org.junit.Test
import org.junit.Assert.assertEquals

class ShapeVisitorTest {

    @Test
    fun `test Area Calculation`() {
        val shapes = listOf(
            Circle(10.0), // Area = 314.159...
            Rectangle(10.0, 5.0), // Area = 50.0
            Triangle(3.0, 4.0, 5.0) // Area = 6.0 (3-4-5 triangle)
        )

        val areaVisitor = AreaVisitor()
        shapes.forEach { it.accept(areaVisitor) }

        // Total Area = 314.159 + 50 + 6 = 370.159
        assertEquals(370.159, areaVisitor.totalArea, 0.01)
    }

    @Test
    fun `test Perimeter Calculation`() {
        val shapes = listOf(
            Circle(10.0), // Perimeter = 2 * PI * 10 = 62.83...
            Rectangle(10.0, 5.0), // Perimeter = 2 * (10 + 5) = 30.0
            Triangle(3.0, 4.0, 5.0) // Perimeter = 3 + 4 + 5 = 12.0
        )

        val perimeterVisitor = PerimeterVisitor()
        shapes.forEach { it.accept(perimeterVisitor) }

        // Total Perimeter = 62.83 + 30 + 12 = 104.83
        assertEquals(104.83, perimeterVisitor.totalPerimeter, 0.01)
    }

    @Test
    fun `test JSON Export`() {
        val shapes = listOf(
            Circle(5.0),
            Rectangle(2.0, 3.0)
        )

        val jsonVisitor = JsonExportVisitor()
        shapes.forEach { it.accept(jsonVisitor) }

        val json = jsonVisitor.getJson()
        println("JSON: $json")
        
        assert(json.contains(""""type": "circle", "radius": 5.0"""))
        assert(json.contains(""""type": "rectangle", "width": 2.0, "height": 3.0"""))
    }
}
