package com.vaibhav.designpattern.chainofresponsibility.payment

object PaymentChain {
    fun processPayment(request: PaymentRequest) {
        val wallet = WalletHandler()
        val card = CardHandler()
        val netBanking = NetBankingHandler()
        val upi = UPIHandler()
        val cod = CODHandler()

        // Chaining the handlers
        wallet.setNext(card)
            .setNext(netBanking)
            .setNext(upi)
            .setNext(cod)

        // Start processing
        wallet.handle(request)
    }
}
