package com.vaibhav.designpattern.chainofresponsibility.support

import org.junit.Test

class SupportChainTest {

    @Test
    fun `test Bot auto-resolution`() {
        println("--- Scenario: Low Priority General Query ---")
        val ticket = Ticket("T1", "How do I reset password?", Priority.LOW, TicketType.GENERAL)
        SupportChain.processTicket(ticket)
    }

    @Test
    fun `test Escalation to Tech Support`() {
        println("\n--- Scenario: High Priority Bug ---")
        val ticket = Ticket("T2", "App crashes on login", Priority.HIGH, TicketType.BUG)
        SupportChain.processTicket(ticket)
    }

    @Test
    fun `test Escalation to Manager`() {
        println("\n--- Scenario: Critical Legal Issue ---")
        val ticket = Ticket("T3", "Data breach allegation", Priority.CRITICAL, TicketType.LEGAL)
        SupportChain.processTicket(ticket)
    }

    @Test
    fun `test Unhandled Ticket`() {
        println("\n--- Scenario: Unknown Issue Type ---")
        // Medium priority but General type (HelpDesk handles Billing, Bot handles Low/General)
        // Bot (Low!=Medium) -> HelpDesk (Billing!=General) -> TechSupport (High!=Medium) -> Manager (Critical!=Medium)
        val ticket = Ticket("T4", "Feature request", Priority.MEDIUM, TicketType.GENERAL)
        SupportChain.processTicket(ticket)
    }
}
