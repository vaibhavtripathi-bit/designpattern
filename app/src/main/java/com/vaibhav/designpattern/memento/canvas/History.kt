package com.vaibhav.designpattern.memento.canvas

import java.util.Stack

/**
 * Caretaker: Manages the history of canvas states.
 */
class History {
    private val states = Stack<CanvasState>()

    fun push(state: CanvasState) {
        states.push(state)
    }

    fun pop(): CanvasState? {
        return if (states.isNotEmpty()) {
            states.pop()
        } else {
            null
        }
    }
}
