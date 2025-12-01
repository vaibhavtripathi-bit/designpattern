package com.vaibhav.designpattern.visitor.shape

import kotlin.math.sqrt

class AreaVisitor : ShapeVisitor {
    var totalArea = 0.0

    override fun visit(circle: Circle) {
        totalArea += Math.PI * circle.radius * circle.radius
    }

    override fun visit(rectangle: Rectangle) {
        totalArea += rectangle.width * rectangle.height
    }

    override fun visit(triangle: Triangle) {
        // Heron's formula
        val s = (triangle.a + triangle.b + triangle.c) / 2
        totalArea += sqrt(s * (s - triangle.a) * (s - triangle.b) * (s - triangle.c))
    }
}

class PerimeterVisitor : ShapeVisitor {
    var totalPerimeter = 0.0

    override fun visit(circle: Circle) {
        totalPerimeter += 2 * Math.PI * circle.radius
    }

    override fun visit(rectangle: Rectangle) {
        totalPerimeter += 2 * (rectangle.width + rectangle.height)
    }

    override fun visit(triangle: Triangle) {
        totalPerimeter += triangle.a + triangle.b + triangle.c
    }
}

class JsonExportVisitor : ShapeVisitor {
    private val sb = StringBuilder()

    override fun visit(circle: Circle) {
        sb.append("""{"type": "circle", "radius": ${circle.radius}}""")
    }

    override fun visit(rectangle: Rectangle) {
        sb.append("""{"type": "rectangle", "width": ${rectangle.width}, "height": ${rectangle.height}}""")
    }

    override fun visit(triangle: Triangle) {
        sb.append("""{"type": "triangle", "sides": [${triangle.a}, ${triangle.b}, ${triangle.c}]}""")
    }

    fun getJson(): String = sb.toString()
}
