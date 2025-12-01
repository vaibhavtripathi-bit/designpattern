package com.vaibhav.designpattern.template

/**
 * Generic Memento class holding state.
 * @param T The type of state to save.
 */
data class Memento<T>(val state: T)

/**
 * Generic Originator that creates and restores state.
 */
class Originator<T>(var state: T) {
    fun save(): Memento<T> {
        return Memento(state)
    }

    fun restore(memento: Memento<T>) {
        state = memento.state
    }
}

/**
 * Generic Caretaker that manages history.
 */
class Caretaker<T> {
    private val history = mutableListOf<Memento<T>>()

    fun save(memento: Memento<T>) {
        history.add(memento)
    }

    fun undo(): Memento<T>? {
        if (history.isNotEmpty()) {
            return history.removeAt(history.lastIndex)
        }
        return null
    }
}
