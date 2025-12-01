package com.vaibhav.designpattern.mediator.chat

interface ChatMediator {
    fun sendMessage(msg: String, user: User)
    fun addUser(user: User)
}

class ChatRoom : ChatMediator {
    private val users = mutableListOf<User>()

    override fun addUser(user: User) {
        users.add(user)
    }

    override fun sendMessage(msg: String, user: User) {
        for (u in users) {
            // Message should not be received by the user sending it
            if (u != user) {
                u.receive(msg)
            }
        }
    }
}
