package com.vaibhav.designpattern.memento.editor

import java.util.Stack

/**
 * Caretaker: Manages the mementos.
 */
class History {
    private val states = Stack<EditorState>()

    fun push(state: EditorState) {
        states.push(state)
    }

    fun pop(): EditorState? {
        return if (states.isNotEmpty()) {
            states.pop()
        } else {
            null
        }
    }
}
