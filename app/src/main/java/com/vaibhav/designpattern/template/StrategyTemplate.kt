package com.vaibhav.designpattern.template

/**
 * Generic interface for a Strategy.
 * @param T The input type.
 * @param R The return type.
 */
interface Strategy<T, R> {
    fun execute(input: T): R
}

/**
 * Context class that uses a Strategy.
 */
class Context<T, R>(var strategy: Strategy<T, R>) {
    fun executeStrategy(input: T): R {
        return strategy.execute(input)
    }
}
