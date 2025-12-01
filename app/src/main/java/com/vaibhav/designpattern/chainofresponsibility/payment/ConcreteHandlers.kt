package com.vaibhav.designpattern.chainofresponsibility.payment

import android.util.Log

private const val TAG = "PaymentChain"

class WalletHandler : PaymentHandler() {
    override fun handle(request: PaymentRequest) {
        if (request.walletBalance >= request.amount) {
            println("Payment successful via Wallet. Balance used: ${request.amount}")
        } else {
            println("Wallet insufficient. Passing to next handler.")
            nextHandler?.handle(request) ?: println("Payment Failed: No more handlers.")
        }
    }
}

class CardHandler : PaymentHandler() {
    override fun handle(request: PaymentRequest) {
        if (request.isCardWorking) {
            println("Payment successful via Card.")
        } else {
            println("Card unavailable. Passing to next handler.")
            nextHandler?.handle(request) ?: println("Payment Failed: No more handlers.")
        }
    }
}

class NetBankingHandler : PaymentHandler() {
    override fun handle(request: PaymentRequest) {
        if (request.isNetBankingWorking) {
            println("Payment successful via NetBanking.")
        } else {
            println("NetBanking unavailable. Passing to next handler.")
            nextHandler?.handle(request) ?: println("Payment Failed: No more handlers.")
        }
    }
}

class UPIHandler : PaymentHandler() {
    override fun handle(request: PaymentRequest) {
        if (request.isUpiWorking) {
            println("Payment successful via UPI.")
        } else {
            println("UPI unavailable. Passing to next handler.")
            nextHandler?.handle(request) ?: println("Payment Failed: No more handlers.")
        }
    }
}

class CODHandler : PaymentHandler() {
    override fun handle(request: PaymentRequest) {
        if (request.isCodAvailable) {
            println("Payment successful via COD.")
        } else {
            println("COD unavailable. Passing to next handler.")
            nextHandler?.handle(request) ?: println("Payment Failed: No more handlers.")
        }
    }
}
