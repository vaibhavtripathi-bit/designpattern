package com.vaibhav.designpattern.visitor

import com.vaibhav.designpattern.visitor.document.HtmlExportVisitor
import com.vaibhav.designpattern.visitor.document.Image
import com.vaibhav.designpattern.visitor.document.Paragraph
import com.vaibhav.designpattern.visitor.document.StatsVisitor
import com.vaibhav.designpattern.visitor.document.Table
import com.vaibhav.designpattern.visitor.document.TextExportVisitor
import org.junit.Test
import org.junit.Assert.assertEquals

class VisitorExampleTest {

    @Test
    fun `test Html Export`() {
        val document = listOf(
            Paragraph("Hello World"),
            Image("https://example.com/img.png", 1.5),
            Table(3, 4)
        )

        val htmlVisitor = HtmlExportVisitor()
        document.forEach { it.accept(htmlVisitor) }

        val html = htmlVisitor.getHtml()
        println("--- HTML Output ---\n$html")
        
        assert(html.contains("<p>Hello World</p>"))
        assert(html.contains("<img src='https://example.com/img.png' size='1.5MB' />"))
        assert(html.contains("<table>Rows: 3, Cols: 4</table>"))
    }

    @Test
    fun `test Text Export`() {
        val document = listOf(
            Paragraph("Introduction"),
            Paragraph("Conclusion")
        )

        val textVisitor = TextExportVisitor()
        document.forEach { it.accept(textVisitor) }

        val text = textVisitor.getText()
        println("--- Text Output ---\n$text")

        assert(text.contains("Introduction"))
        assert(text.contains("Conclusion"))
    }

    @Test
    fun `test Stats Calculation`() {
        val document = listOf(
            Paragraph("This is a test paragraph with eight words."),
            Image("img1.jpg", 2.0),
            Image("img2.jpg", 3.0),
            Table(5, 2) // 10 cells
        )

        val statsVisitor = StatsVisitor()
        document.forEach { it.accept(statsVisitor) }

        statsVisitor.printStats()

        assertEquals(8, statsVisitor.wordCount)
        assertEquals(2, statsVisitor.imageCount)
        assertEquals(10, statsVisitor.tableCellCount)
    }
}
