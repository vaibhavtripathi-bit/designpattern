package com.vaibhav.designpattern.visitor.document

/**
 * The Visitor interface defining visit methods for each element type.
 */
interface Visitor {
    fun visit(paragraph: Paragraph)
    fun visit(image: Image)
    fun visit(table: Table)
}
