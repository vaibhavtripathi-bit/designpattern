package com.vaibhav.designpattern.decorator.notification

import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

class NotificationDecoratorTest {

    @Before
    fun setup() {
        NotificationLog.clear()
    }

    @Test
    fun `test Email Notification`() {
        val notifier = EmailNotifier("admin@example.com")
        notifier.send("System Down!")

        val logs = NotificationLog.getLogs()
        assertEquals(1, logs.size)
        assertEquals("Email to admin@example.com: System Down!", logs[0])
    }

    @Test
    fun `test Email and SMS Notification`() {
        // Wrap Email with SMS
        val notifier = SMSDecorator(
            EmailNotifier("user@example.com"),
            "+1234567890"
        )
        notifier.send("OTP: 1234")

        val logs = NotificationLog.getLogs()
        assertEquals(2, logs.size)
        // Order depends on implementation (SMS calls super.send after logging)
        assertTrue(logs.contains("SMS to +1234567890: OTP: 1234"))
        assertTrue(logs.contains("Email to user@example.com: OTP: 1234"))
    }

    @Test
    fun `test All Channels (Slack, SMS, Email)`() {
        // Wrap: Slack(SMS(Email))
        val notifier = SlackDecorator(
            SMSDecorator(
                EmailNotifier("dev@example.com"),
                "+9876543210"
            ),
            "@dev_team"
        )
        notifier.send("Deployment Successful")

        val logs = NotificationLog.getLogs()
        assertEquals(3, logs.size)
        assertTrue(logs.contains("Slack to @dev_team: Deployment Successful"))
        assertTrue(logs.contains("SMS to +9876543210: Deployment Successful"))
        assertTrue(logs.contains("Email to dev@example.com: Deployment Successful"))
    }
}
