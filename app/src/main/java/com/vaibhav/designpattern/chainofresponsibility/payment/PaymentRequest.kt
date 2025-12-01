package com.vaibhav.designpattern.chainofresponsibility.payment

/**
 * Represents a payment request with necessary details for processing.
 */
data class PaymentRequest(
    val amount: Double,
    val walletBalance: Double,
    val isCardWorking: Boolean,
    val isNetBankingWorking: Boolean,
    val isUpiWorking: Boolean,
    val isCodAvailable: Boolean
)
