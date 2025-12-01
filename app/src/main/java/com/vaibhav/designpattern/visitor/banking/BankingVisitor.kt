package com.vaibhav.designpattern.visitor.banking

/**
 * The Visitor interface.
 */
interface BankingVisitor {
    fun visit(cc: CreditCardTransaction)
    fun visit(transfer: BankTransfer)
    fun visit(paypal: PayPalTransaction)
}
