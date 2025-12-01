package com.vaibhav.designpattern.mediator.chat

import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

class ChatMediatorTest {

    @Test
    fun `test Chat Room Broadcast`() {
        val mediator = ChatRoom()

        val user1 = ChatUser(mediator, "User1")
        val user2 = ChatUser(mediator, "User2")
        val user3 = ChatUser(mediator, "User3")
        val user4 = ChatUser(mediator, "User4")

        mediator.addUser(user1)
        mediator.addUser(user2)
        mediator.addUser(user3)
        mediator.addUser(user4)

        user1.send("Hello Everyone!")

        // User1 should NOT receive their own message
        assertEquals(0, user1.receivedMessages.size)

        // Others should receive it
        assertEquals(1, user2.receivedMessages.size)
        assertEquals("Hello Everyone!", user2.receivedMessages[0])

        assertEquals(1, user3.receivedMessages.size)
        assertEquals("Hello Everyone!", user3.receivedMessages[0])

        assertEquals(1, user4.receivedMessages.size)
        assertEquals("Hello Everyone!", user4.receivedMessages[0])
    }
}
