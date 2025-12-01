package com.vaibhav.designpattern.mediator.stock

interface StockMediator {
    fun buyOffer(stock: String, shares: Int, price: Int, trader: Trader)
    fun sellOffer(stock: String, shares: Int, price: Int, trader: Trader)
}

data class Order(val stock: String, var shares: Int, val price: Int, val trader: Trader)

class StockExchange : StockMediator {
    private val buyOrders = mutableListOf<Order>()
    private val sellOrders = mutableListOf<Order>()

    override fun buyOffer(stock: String, shares: Int, price: Int, trader: Trader) {
        val buyOrder = Order(stock, shares, price, trader)
        processBuyOrder(buyOrder)
    }

    override fun sellOffer(stock: String, shares: Int, price: Int, trader: Trader) {
        val sellOrder = Order(stock, shares, price, trader)
        processSellOrder(sellOrder)
    }

    private fun processBuyOrder(buyOrder: Order) {
        // Try to match with existing sell orders
        val iterator = sellOrders.iterator()
        while (iterator.hasNext() && buyOrder.shares > 0) {
            val sellOrder = iterator.next()
            if (sellOrder.stock == buyOrder.stock && sellOrder.price <= buyOrder.price) {
                val tradedShares = minOf(buyOrder.shares, sellOrder.shares)
                
                println("Trade Executed: ${tradedShares} shares of ${buyOrder.stock} at $${sellOrder.price}")
                buyOrder.trader.notify("Bought $tradedShares shares of ${buyOrder.stock}")
                sellOrder.trader.notify("Sold $tradedShares shares of ${sellOrder.stock}")

                buyOrder.shares -= tradedShares
                sellOrder.shares -= tradedShares

                if (sellOrder.shares == 0) {
                    iterator.remove()
                }
            }
        }
        if (buyOrder.shares > 0) {
            buyOrders.add(buyOrder)
        }
    }

    private fun processSellOrder(sellOrder: Order) {
        // Try to match with existing buy orders
        val iterator = buyOrders.iterator()
        while (iterator.hasNext() && sellOrder.shares > 0) {
            val buyOrder = iterator.next()
            if (buyOrder.stock == sellOrder.stock && buyOrder.price >= sellOrder.price) {
                val tradedShares = minOf(sellOrder.shares, buyOrder.shares)

                println("Trade Executed: ${tradedShares} shares of ${sellOrder.stock} at $${buyOrder.price}")
                sellOrder.trader.notify("Sold $tradedShares shares of ${sellOrder.stock}")
                buyOrder.trader.notify("Bought $tradedShares shares of ${buyOrder.stock}")

                sellOrder.shares -= tradedShares
                buyOrder.shares -= tradedShares

                if (buyOrder.shares == 0) {
                    iterator.remove()
                }
            }
        }
        if (sellOrder.shares > 0) {
            sellOrders.add(sellOrder)
        }
    }
}
