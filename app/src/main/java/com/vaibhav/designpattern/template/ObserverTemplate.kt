package com.vaibhav.designpattern.template

/**
 * Generic interface for an Observer.
 * @param T The type of data passed to the observer.
 */
interface Observer<T> {
    fun update(data: T)
}

/**
 * Generic Subject (Observable) class.
 * @param T The type of data passed to observers.
 */
open class Subject<T> {
    private val observers = mutableListOf<Observer<T>>()

    fun addObserver(observer: Observer<T>) {
        observers.add(observer)
    }

    fun removeObserver(observer: Observer<T>) {
        observers.remove(observer)
    }

    fun notifyObservers(data: T) {
        for (observer in observers) {
            observer.update(data)
        }
    }
}
