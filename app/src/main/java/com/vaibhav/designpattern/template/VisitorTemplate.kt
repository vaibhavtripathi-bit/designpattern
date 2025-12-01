package com.vaibhav.designpattern.template

/**
 * Generic interface for a Visitor.
 * @param T The type of element being visited.
 */
interface Visitor<T> {
    fun visit(element: T)
}

/**
 * Generic interface for a Visitable element.
 * @param T The type of the element itself (usually 'this').
 */
interface Visitable<T> {
    fun accept(visitor: Visitor<T>)
}
