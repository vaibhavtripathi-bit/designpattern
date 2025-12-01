package com.vaibhav.designpattern.template

/**
 * Generic interface for a State.
 * @param T The context type.
 */
interface State<T> {
    fun handle(context: T)
}

/**
 * Context class for State pattern.
 * @param T The type of the context itself (usually 'this').
 */
open class StateContext<T>(initialState: State<T>) {
    var state: State<T> = initialState
        set(value) {
            field = value
            println("State changed to ${value::class.simpleName}")
        }

    fun request(context: T) {
        state.handle(context)
    }
}
