package com.vaibhav.designpattern.visitor.banking

class FraudDetectionVisitor : BankingVisitor {
    val flaggedTransactions = mutableListOf<Transaction>()

    override fun visit(cc: CreditCardTransaction) {
        if (cc.amount > 5000 || cc.country != "US") {
            flaggedTransactions.add(cc)
        }
    }

    override fun visit(transfer: BankTransfer) {
        if (transfer.amount > 10000 || transfer.isInternational) {
            flaggedTransactions.add(transfer)
        }
    }

    override fun visit(paypal: PayPalTransaction) {
        if (paypal.email.endsWith("@suspicious.com")) {
            flaggedTransactions.add(paypal)
        }
    }
}

class XmlReportVisitor : BankingVisitor {
    private val sb = StringBuilder()

    init {
        sb.append("<Transactions>\n")
    }

    override fun visit(cc: CreditCardTransaction) {
        sb.append("  <CreditCard amount='${cc.amount}' country='${cc.country}' />\n")
    }

    override fun visit(transfer: BankTransfer) {
        sb.append("  <BankTransfer amount='${transfer.amount}' international='${transfer.isInternational}' />\n")
    }

    override fun visit(paypal: PayPalTransaction) {
        sb.append("  <PayPal amount='${paypal.amount}' email='${paypal.email}' />\n")
    }

    fun getXml(): String {
        return sb.toString() + "</Transactions>"
    }
}
