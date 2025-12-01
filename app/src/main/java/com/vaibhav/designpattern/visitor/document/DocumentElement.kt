package com.vaibhav.designpattern.visitor.document

/**
 * The Element interface that accepts a Visitor.
 */
interface DocumentElement {
    fun accept(visitor: Visitor)
}

data class Paragraph(val text: String) : DocumentElement {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}

data class Image(val src: String, val sizeMb: Double) : DocumentElement {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}

data class Table(val rows: Int, val cols: Int) : DocumentElement {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}
