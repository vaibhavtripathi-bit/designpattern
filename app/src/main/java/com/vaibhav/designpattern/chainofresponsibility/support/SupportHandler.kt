package com.vaibhav.designpattern.chainofresponsibility.support

abstract class SupportHandler {
    protected var nextHandler: SupportHandler? = null

    fun setNext(handler: SupportHandler): SupportHandler {
        this.nextHandler = handler
        return handler
    }

    abstract fun handle(ticket: Ticket)
}
