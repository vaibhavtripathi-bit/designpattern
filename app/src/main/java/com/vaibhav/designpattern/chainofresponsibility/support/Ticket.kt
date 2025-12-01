package com.vaibhav.designpattern.chainofresponsibility.support

enum class Priority {
    LOW, MEDIUM, HIGH, CRITICAL
}

enum class TicketType {
    GENERAL, BILLING, BUG, LEGAL
}

data class Ticket(
    val id: String,
    val message: String,
    val priority: Priority,
    val type: TicketType
)
