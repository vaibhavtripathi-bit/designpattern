package com.vaibhav.designpattern.mediator.stock

import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

class StockExchangeTest {

    @Test
    fun `test Order Matching`() {
        val exchange = StockExchange()
        val traderA = Trader(exchange, "TraderA")
        val traderB = Trader(exchange, "TraderB")

        // 1. Trader A wants to buy 100 AAPL at $150
        traderA.buy("AAPL", 100, 150)
        // No match yet, order pending

        // 2. Trader B wants to sell 50 AAPL at $150
        traderB.sell("AAPL", 50, 150)
        // Should match 50 shares

        assertEquals(1, traderA.notifications.size)
        assertEquals("Bought 50 shares of AAPL", traderA.notifications[0])

        assertEquals(1, traderB.notifications.size)
        assertEquals("Sold 50 shares of AAPL", traderB.notifications[0])

        // 3. Trader B sells another 50 AAPL at $150
        traderB.sell("AAPL", 50, 150)
        // Should match remaining 50 shares

        assertEquals(2, traderA.notifications.size)
        assertEquals("Bought 50 shares of AAPL", traderA.notifications[1])
    }
}
