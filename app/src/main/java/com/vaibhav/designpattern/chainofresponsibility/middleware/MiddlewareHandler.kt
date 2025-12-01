package com.vaibhav.designpattern.chainofresponsibility.middleware

abstract class MiddlewareHandler {
    protected var nextHandler: MiddlewareHandler? = null

    fun setNext(handler: MiddlewareHandler): MiddlewareHandler {
        this.nextHandler = handler
        return handler
    }

    /**
     * Handles the request.
     * @return true if the request was processed successfully or passed down the chain.
     * @return false if the request was rejected/blocked by this handler.
     */
    abstract fun handle(request: ApiRequest): Boolean
}
