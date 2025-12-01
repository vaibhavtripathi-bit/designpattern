package com.vaibhav.designpattern.decorator.notification

class SMSDecorator(wrappee: Notifier, private val phone: String) : NotificationDecorator(wrappee) {
    override fun send(message: String) {
        // Send SMS first (or after, doesn't matter much, but usually decorators add behavior)
        NotificationLog.add("SMS to $phone: $message")
        super.send(message)
    }
}

class SlackDecorator(wrappee: Notifier, private val username: String) : NotificationDecorator(wrappee) {
    override fun send(message: String) {
        NotificationLog.add("Slack to $username: $message")
        super.send(message)
    }
}
