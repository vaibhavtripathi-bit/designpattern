package com.vaibhav.designpattern.visitor.document

class HtmlExportVisitor : Visitor {
    val sb = StringBuilder()

    override fun visit(paragraph: Paragraph) {
        sb.append("<p>${paragraph.text}</p>\n")
    }

    override fun visit(image: Image) {
        sb.append("<img src='${image.src}' size='${image.sizeMb}MB' />\n")
    }

    override fun visit(table: Table) {
        sb.append("<table>Rows: ${table.rows}, Cols: ${table.cols}</table>\n")
    }

    fun getHtml(): String = sb.toString()
}

class TextExportVisitor : Visitor {
    val sb = StringBuilder()

    override fun visit(paragraph: Paragraph) {
        sb.append(paragraph.text).append("\n")
    }

    override fun visit(image: Image) {
        sb.append("[Image: ${image.src}]\n")
    }

    override fun visit(table: Table) {
        sb.append("[Table: ${table.rows}x${table.cols}]\n")
    }

    fun getText(): String = sb.toString()
}

class StatsVisitor : Visitor {
    var wordCount = 0
    var imageCount = 0
    var tableCellCount = 0

    override fun visit(paragraph: Paragraph) {
        wordCount += paragraph.text.split("\\s+".toRegex()).size
    }

    override fun visit(image: Image) {
        imageCount++
    }

    override fun visit(table: Table) {
        tableCellCount += table.rows * table.cols
    }

    fun printStats() {
        println("Stats: Words=$wordCount, Images=$imageCount, TableCells=$tableCellCount")
    }
}
