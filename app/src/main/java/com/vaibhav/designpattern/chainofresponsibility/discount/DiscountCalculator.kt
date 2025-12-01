package com.vaibhav.designpattern.chainofresponsibility.discount

abstract class DiscountHandler {
    protected var nextHandler: DiscountHandler? = null

    fun setNext(handler: DiscountHandler): DiscountHandler {
        this.nextHandler = handler
        return handler
    }

    fun calculate(price: Double): Double {
        val priceAfterDiscount = applyDiscount(price)
        return nextHandler?.calculate(priceAfterDiscount) ?: priceAfterDiscount
    }

    protected abstract fun applyDiscount(price: Double): Double
}

class SeasonalDiscount : DiscountHandler() {
    override fun applyDiscount(price: Double): Double {
        val discount = price * 0.10
        println("Seasonal Discount: Applied 10% ($discount)")
        return price - discount
    }
}

class MemberDiscount : DiscountHandler() {
    override fun applyDiscount(price: Double): Double {
        val discount = price * 0.05
        println("Member Discount: Applied 5% ($discount)")
        return price - discount
    }
}

class FirstTimeDiscount : DiscountHandler() {
    override fun applyDiscount(price: Double): Double {
        val discount = price * 0.20
        println("First Time Discount: Applied 20% ($discount)")
        return price - discount
    }
}

object DiscountChain {
    fun calculateFinalPrice(originalPrice: Double): Double {
        val seasonal = SeasonalDiscount()
        val member = MemberDiscount()
        val firstTime = FirstTimeDiscount()

        seasonal.setNext(member).setNext(firstTime)

        println("--- Calculating Discount for $$originalPrice ---")
        val finalPrice = seasonal.calculate(originalPrice)
        println("Final Price: $$finalPrice")
        return finalPrice
    }
}


class DecoratorDesignPattern {
    interface Discount {
        fun apply(price: Double): Double
    }

    class BasePrice : Discount {
        override fun apply(price: Double) = price
    }

    class SeasonalDiscount(private val next: Discount) : Discount {
        override fun apply(price: Double): Double {
            val discounted = price * 0.9
            println("Seasonal 10% -> $discounted")
            return next.apply(discounted)
        }
    }

    class MemberDiscount(private val next: Discount) : Discount {
        override fun apply(price: Double): Double {
            val discounted = price * 0.95
            println("Member 5% -> $discounted")
            return next.apply(discounted)
        }
    }

    class FirstTimeDiscount(private val next: Discount) : Discount {
        override fun apply(price: Double): Double {
            val discounted = price * 0.8
            println("First Time 20% -> $discounted")
            return next.apply(discounted)
        }
    }

    fun useDecoratorPattern(originalPrice: Double) {
        val discountChain = SeasonalDiscount(
            MemberDiscount(
                FirstTimeDiscount(
                    BasePrice()
                )
            )
        )
        val finalPrice = discountChain.apply(originalPrice)
        println("Final Price using Decorator: $$finalPrice")
    }
}
