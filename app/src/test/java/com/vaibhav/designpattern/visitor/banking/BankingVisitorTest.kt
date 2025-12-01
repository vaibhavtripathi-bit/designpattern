package com.vaibhav.designpattern.visitor.banking

import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

class BankingVisitorTest {

    @Test
    fun `test Fraud Detection`() {
        val transactions = listOf(
            CreditCardTransaction(6000.0, "US"), // Flagged: Amount > 5000
            CreditCardTransaction(100.0, "Russia"), // Flagged: Country != US
            BankTransfer(20000.0, false), // Flagged: Amount > 10000
            BankTransfer(500.0, true), // Flagged: International
            PayPalTransaction(100.0, "user@suspicious.com"), // Flagged: Suspicious domain
            PayPalTransaction(50.0, "user@gmail.com") // Safe
        )

        val fraudVisitor = FraudDetectionVisitor()
        transactions.forEach { it.accept(fraudVisitor) }

        assertEquals(5, fraudVisitor.flaggedTransactions.size)
        assertTrue(fraudVisitor.flaggedTransactions.contains(transactions[0]))
        assertTrue(fraudVisitor.flaggedTransactions.contains(transactions[1]))
        assertTrue(fraudVisitor.flaggedTransactions.contains(transactions[2]))
        assertTrue(fraudVisitor.flaggedTransactions.contains(transactions[3]))
        assertTrue(fraudVisitor.flaggedTransactions.contains(transactions[4]))
    }

    @Test
    fun `test XML Report`() {
        val transactions = listOf(
            CreditCardTransaction(100.0, "US"),
            PayPalTransaction(50.0, "user@example.com")
        )

        val xmlVisitor = XmlReportVisitor()
        transactions.forEach { it.accept(xmlVisitor) }

        val xml = xmlVisitor.getXml()
        println("XML Report:\n$xml")

        assertTrue(xml.contains("<CreditCard amount='100.0' country='US' />"))
        assertTrue(xml.contains("<PayPal amount='50.0' email='user@example.com' />"))
        assertTrue(xml.startsWith("<Transactions>"))
        assertTrue(xml.endsWith("</Transactions>"))
    }
}
