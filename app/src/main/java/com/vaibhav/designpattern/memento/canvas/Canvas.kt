package com.vaibhav.designpattern.memento.canvas

/**
 * Originator: The drawing canvas.
 */
class Canvas {
    private var shapes = mutableListOf<Shape>()

    fun addShape(shape: Shape) {
        shapes.add(shape)
    }

    fun getShapes(): List<Shape> {
        return shapes.toList()
    }

    fun save(): CanvasState {
        // Create a copy of the list for the state
        return CanvasState(shapes.toList())
    }

    fun restore(state: CanvasState) {
        // Restore by copying the list from the state
        shapes = state.shapes.toMutableList()
    }
}
