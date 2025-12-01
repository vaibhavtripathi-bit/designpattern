package com.vaibhav.designpattern.template

/**
 * Abstract class defining the Template Method pattern.
 * @param T The context or data required for execution.
 */
abstract class AbstractTemplate<T> {

    /**
     * The Template Method defining the algorithm skeleton.
     */
    fun execute(context: T) {
        stepOne(context)
        stepTwo(context)
        if (hook(context)) {
            stepThree(context)
        }
    }

    protected abstract fun stepOne(context: T)
    protected abstract fun stepTwo(context: T)
    protected abstract fun stepThree(context: T)

    /**
     * A hook that can be overridden to control flow.
     */
    protected open fun hook(context: T): Boolean = true
}
