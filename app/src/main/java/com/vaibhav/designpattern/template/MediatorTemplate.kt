package com.vaibhav.designpattern.template

/**
 * Generic interface for a Mediator.
 * @param T The type of data passed in events.
 */
interface Mediator<T> {
    fun notify(sender: Colleague<T>, event: String, data: T? = null)
}

/**
 * Abstract base class for Colleagues.
 */
abstract class Colleague<T>(protected var mediator: Mediator<T>) {
    fun send(event: String, data: T? = null) {
        mediator.notify(this, event, data)
    }

    abstract fun receive(event: String, data: T?)
}
