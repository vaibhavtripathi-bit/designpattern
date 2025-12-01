package com.vaibhav.designpattern.template

/**
 * Generic interface for a handler in the Chain of Responsibility pattern.
 * @param T The type of request object to handle.
 */
interface Handler<T> {
    fun setNext(handler: Handler<T>): Handler<T>
    fun handle(request: T)
}

/**
 * Abstract base class implementing the chaining logic.
 */
abstract class BaseHandler<T> : Handler<T> {
    protected var nextHandler: Handler<T>? = null

    override fun setNext(handler: Handler<T>): Handler<T> {
        this.nextHandler = handler
        return handler
    }

    override fun handle(request: T) {
        if (canHandle(request)) {
            process(request)
        } else {
            nextHandler?.handle(request) ?: onUncaught(request)
        }
    }

    /**
     * Determine if this handler should process the request.
     * Default implementation returns true (always process).
     * Override to implement conditional handling (e.g., if (amount < 100)).
     */
    protected open fun canHandle(request: T): Boolean = true

    /**
     * The actual processing logic.
     */
    protected abstract fun process(request: T)

    /**
     * Called when no handler in the chain processes the request.
     */
    protected open fun onUncaught(request: T) {
        println("Request was not handled by any handler.")
    }
}
