package com.vaibhav.designpattern.visitor.shopping

class PriceVisitor : ShoppingCartVisitor {
    var totalPrice = 0.0

    override fun visit(book: Book) {
        totalPrice += book.price
    }

    override fun visit(fruit: Fruit) {
        totalPrice += fruit.pricePerKg * fruit.weightKg
    }
}

class WeightVisitor : ShoppingCartVisitor {
    var totalWeight = 0.0

    override fun visit(book: Book) {
        totalWeight += book.weightKg
    }

    override fun visit(fruit: Fruit) {
        totalWeight += fruit.weightKg
    }
}
