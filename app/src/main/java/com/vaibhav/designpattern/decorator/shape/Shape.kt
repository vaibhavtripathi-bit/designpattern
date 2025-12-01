package com.vaibhav.designpattern.decorator.shape

/**
 * Interface for Shapes.
 * Uses a log list to demonstrate side effects and order of operations.
 */
interface Shape {
    fun draw(log: MutableList<String>)
}

class Circle : Shape {
    override fun draw(log: MutableList<String>) {
        log.add("Circle")
    }
}

class Rectangle : Shape {
    override fun draw(log: MutableList<String>) {
        log.add("Rectangle")
    }
}
