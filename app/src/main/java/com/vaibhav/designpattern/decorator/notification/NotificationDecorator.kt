package com.vaibhav.designpattern.decorator.notification

abstract class NotificationDecorator(private val wrappee: Notifier) : Notifier {
    override fun send(message: String) {
        wrappee.send(message)
    }
}
