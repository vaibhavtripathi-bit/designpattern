package com.vaibhav.designpattern.memento.browser

import java.util.Stack

/**
 * Caretaker: Manages Back and Forward stacks.
 */
class NavigationHistory {
    private val backStack = Stack<BrowserState>()
    private val forwardStack = Stack<BrowserState>()

    /**
     * Called when visiting a new page.
     * Saves the previous state to backStack and clears forwardStack.
     */
    fun commit(state: BrowserState) {
        backStack.push(state)
        forwardStack.clear()
    }

    /**
     * Moves back.
     * Saves current state to forwardStack.
     * Returns the state from backStack.
     */
    fun undo(currentState: BrowserState): BrowserState? {
        if (backStack.isEmpty()) return null
        
        forwardStack.push(currentState)
        return backStack.pop()
    }

    /**
     * Moves forward.
     * Saves current state to backStack.
     * Returns the state from forwardStack.
     */
    fun redo(currentState: BrowserState): BrowserState? {
        if (forwardStack.isEmpty()) return null
        
        backStack.push(currentState)
        return forwardStack.pop()
    }
}
