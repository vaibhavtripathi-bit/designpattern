package com.vaibhav.designpattern.memento.canvas

/**
 * Memento: Immutable snapshot of the canvas state.
 * Important: It must store a COPY of the list, not the reference.
 */
data class CanvasState(val shapes: List<Shape>)
