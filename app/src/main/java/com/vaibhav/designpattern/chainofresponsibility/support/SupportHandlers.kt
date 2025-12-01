package com.vaibhav.designpattern.chainofresponsibility.support

class BotHandler : SupportHandler() {
    override fun handle(ticket: Ticket) {
        if (ticket.priority == Priority.LOW && ticket.type == TicketType.GENERAL) {
            println("Bot: Auto-resolved ticket ${ticket.id} (FAQ match).")
        } else {
            println("Bot: Cannot resolve. Escalating...")
            nextHandler?.handle(ticket) ?: println("Ticket ${ticket.id} unhandled.")
        }
    }
}

class HelpDeskHandler : SupportHandler() {
    override fun handle(ticket: Ticket) {
        if (ticket.priority == Priority.MEDIUM && ticket.type == TicketType.BILLING) {
            println("Help Desk: Resolved billing issue ${ticket.id}.")
        } else {
            println("Help Desk: Cannot resolve. Escalating...")
            nextHandler?.handle(ticket) ?: println("Ticket ${ticket.id} unhandled.")
        }
    }
}

class TechSupportHandler : SupportHandler() {
    override fun handle(ticket: Ticket) {
        if (ticket.priority == Priority.HIGH && ticket.type == TicketType.BUG) {
            println("Tech Support: Fixed bug for ticket ${ticket.id}.")
        } else {
            println("Tech Support: Cannot resolve. Escalating...")
            nextHandler?.handle(ticket) ?: println("Ticket ${ticket.id} unhandled.")
        }
    }
}

class ManagerHandler : SupportHandler() {
    override fun handle(ticket: Ticket) {
        if (ticket.priority == Priority.CRITICAL && ticket.type == TicketType.LEGAL) {
            println("Manager: Handling critical legal issue ${ticket.id}.")
        } else {
            println("Manager: Cannot resolve. Ticket ${ticket.id} remains open.")
        }
    }
}
