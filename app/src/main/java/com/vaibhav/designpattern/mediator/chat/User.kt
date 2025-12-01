package com.vaibhav.designpattern.mediator.chat

abstract class User(protected val mediator: ChatMediator, val name: String) {
    abstract fun send(msg: String)
    abstract fun receive(msg: String)
}

class ChatUser(mediator: ChatMediator, name: String) : User(mediator, name) {
    
    // For testing purposes, we can store received messages
    val receivedMessages = mutableListOf<String>()

    override fun send(msg: String) {
        println("$name sending: $msg")
        mediator.sendMessage(msg, this)
    }

    override fun receive(msg: String) {
        println("$name received: $msg")
        receivedMessages.add(msg)
    }
}
