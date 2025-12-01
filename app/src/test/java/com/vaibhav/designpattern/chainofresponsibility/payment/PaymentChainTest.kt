package com.vaibhav.designpattern.chainofresponsibility.payment

import org.junit.Test

class PaymentChainTest {

    @Test
    fun `test payment success via Wallet`() {
        println("--- Scenario: Wallet has sufficient balance ---")
        val request = PaymentRequest(
            amount = 100.0,
            walletBalance = 500.0,
            isCardWorking = false,
            isNetBankingWorking = false,
            isUpiWorking = false,
            isCodAvailable = false
        )
        PaymentChain.processPayment(request)
    }

    @Test
    fun `test payment success via NetBanking when Wallet and Card fail`() {
        println("\n--- Scenario: Wallet empty, Card down, NetBanking works ---")
        val request = PaymentRequest(
            amount = 100.0,
            walletBalance = 0.0,
            isCardWorking = false,
            isNetBankingWorking = true,
            isUpiWorking = false,
            isCodAvailable = false
        )
        PaymentChain.processPayment(request)
    }

    @Test
    fun `test payment failure when all methods fail`() {
        println("\n--- Scenario: All payment methods fail ---")
        val request = PaymentRequest(
            amount = 100.0,
            walletBalance = 0.0,
            isCardWorking = false,
            isNetBankingWorking = false,
            isUpiWorking = false,
            isCodAvailable = false
        )
        PaymentChain.processPayment(request)
    }
}
