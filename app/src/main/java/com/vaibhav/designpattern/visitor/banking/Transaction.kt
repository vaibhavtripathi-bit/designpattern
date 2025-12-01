package com.vaibhav.designpattern.visitor.banking

/**
 * The Element interface.
 */
interface Transaction {
    fun accept(visitor: BankingVisitor)
}

data class CreditCardTransaction(val amount: Double, val country: String) : Transaction {
    override fun accept(visitor: BankingVisitor) {
        visitor.visit(this)
    }
}

data class BankTransfer(val amount: Double, val isInternational: Boolean) : Transaction {
    override fun accept(visitor: BankingVisitor) {
        visitor.visit(this)
    }
}

data class PayPalTransaction(val amount: Double, val email: String) : Transaction {
    override fun accept(visitor: BankingVisitor) {
        visitor.visit(this)
    }
}
