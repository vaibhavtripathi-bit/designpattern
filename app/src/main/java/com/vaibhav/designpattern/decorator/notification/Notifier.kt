package com.vaibhav.designpattern.decorator.notification

interface Notifier {
    fun send(message: String)
}

class EmailNotifier(private val email: String) : Notifier {
    override fun send(message: String) {
        // In a real app, this would send an email.
        // For demonstration, we'll log it to a global list or just print.
        // To make it testable without mocking frameworks for this simple example, 
        // let's use a companion object to store logs, or just return a list?
        // The interface returns void, so side-effect is expected.
        // Let's print to stdout for now, and tests can maybe intercept or we use a helper.
        // Better: Let's have a global 'NotificationLog' singleton for this example to verify tests.
        NotificationLog.add("Email to $email: $message")
    }
}

object NotificationLog {
    private val logs = mutableListOf<String>()
    
    fun add(log: String) {
        logs.add(log)
    }
    
    fun getLogs(): List<String> = logs.toList()
    
    fun clear() {
        logs.clear()
    }
}
