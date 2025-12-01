package com.vaibhav.designpattern.template

/**
 * Generic interface for a Component in the Decorator pattern.
 * @param T The return type of the operation.
 * @param R The input type of the operation (optional, can be Unit).
 */
interface Component<R, T> {
    fun operation(input: R): T
}

/**
 * Abstract Decorator class that wraps a Component.
 */
abstract class Decorator<R, T>(protected val component: Component<R, T>) : Component<R, T> {
    override fun operation(input: R): T {
        return component.operation(input)
    }
}
