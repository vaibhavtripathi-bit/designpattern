package com.vaibhav.designpattern.chainofresponsibility.payment

/**
 * Abstract handler in the Chain of Responsibility pattern.
 */
abstract class PaymentHandler {
    protected var nextHandler: PaymentHandler? = null

    /**
     * Sets the next handler in the chain.
     * Returns the next handler to allow fluent chaining.
     */
    fun setNext(handler: PaymentHandler): PaymentHandler {
        this.nextHandler = handler
        return handler
    }

    /**
     * Abstract method to handle the payment request.
     */
    abstract fun handle(request: PaymentRequest)
}
