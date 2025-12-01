package com.vaibhav.designpattern.chainofresponsibility.support

object SupportChain {
    fun processTicket(ticket: Ticket) {
        val bot = BotHandler()
        val helpDesk = HelpDeskHandler()
        val techSupport = TechSupportHandler()
        val manager = ManagerHandler()

        // Chain: Bot -> HelpDesk -> TechSupport -> Manager
        bot.setNext(helpDesk)
            .setNext(techSupport)
            .setNext(manager)

        bot.handle(ticket)
    }
}
