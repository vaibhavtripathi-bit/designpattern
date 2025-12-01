package com.vaibhav.designpattern.chainofresponsibility.atm

data class Currency(val amount: Int)

abstract class AtmHandler {
    protected var nextHandler: AtmHandler? = null

    fun setNext(handler: AtmHandler): AtmHandler {
        this.nextHandler = handler
        return handler
    }

    abstract fun dispense(currency: Currency)
}

class Rupees2000Handler : AtmHandler() {
    override fun dispense(currency: Currency) {
        val amount = currency.amount
        if (amount >= 2000) {
            val count = amount / 2000
            val remainder = amount % 2000
            println("Dispensing $count x 2000 Note")
            if (remainder != 0) nextHandler?.dispense(Currency(remainder))
        } else {
            nextHandler?.dispense(currency)
        }
    }
}

class Rupees500Handler : AtmHandler() {
    override fun dispense(currency: Currency) {
        val amount = currency.amount
        if (amount >= 500) {
            val count = amount / 500
            val remainder = amount % 500
            println("Dispensing $count x 500 Note")
            if (remainder != 0) nextHandler?.dispense(Currency(remainder))
        } else {
            nextHandler?.dispense(currency)
        }
    }
}

class Rupees200Handler : AtmHandler() {
    override fun dispense(currency: Currency) {
        val amount = currency.amount
        if (amount >= 200) {
            val count = amount / 200
            val remainder = amount % 200
            println("Dispensing $count x 200 Note")
            if (remainder != 0) nextHandler?.dispense(Currency(remainder))
        } else {
            nextHandler?.dispense(currency)
        }
    }
}

class Rupees100Handler : AtmHandler() {
    override fun dispense(currency: Currency) {
        val amount = currency.amount
        if (amount >= 100) {
            val count = amount / 100
            val remainder = amount % 100
            println("Dispensing $count x 100 Note")
            if (remainder != 0) println("Error: Cannot dispense remaining $remainder")
        } else {
            println("Error: Cannot dispense amount $amount")
        }
    }
}

object AtmDispenserChain {
    fun withdraw(amount: Int) {
        if (amount % 100 != 0) {
            println("Error: Amount should be in multiples of 100.")
            return
        }
        
        val h2000 = Rupees2000Handler()
        val h500 = Rupees500Handler()
        val h200 = Rupees200Handler()
        val h100 = Rupees100Handler()

        h2000.setNext(h500).setNext(h200).setNext(h100)

        println("--- Withdrawing $amount ---")
        h2000.dispense(Currency(amount))
    }
}
