package com.vaibhav.designpattern.mediator.stock

class Trader(private val mediator: StockMediator, val name: String) {
    val notifications = mutableListOf<String>()

    fun buy(stock: String, shares: Int, price: Int) {
        mediator.buyOffer(stock, shares, price, this)
    }

    fun sell(stock: String, shares: Int, price: Int) {
        mediator.sellOffer(stock, shares, price, this)
    }

    fun notify(message: String) {
        notifications.add(message)
        println("$name received notification: $message")
    }
}
