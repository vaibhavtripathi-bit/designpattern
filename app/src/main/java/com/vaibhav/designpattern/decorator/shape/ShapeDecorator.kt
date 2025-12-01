package com.vaibhav.designpattern.decorator.shape

/**
 * Abstract decorator class for Shapes.
 */
abstract class ShapeDecorator(protected val decoratedShape: Shape) : Shape {
    override fun draw(log: MutableList<String>) {
        decoratedShape.draw(log)
    }
}
