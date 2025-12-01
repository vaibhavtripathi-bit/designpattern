package com.vaibhav.designpattern.visitor.filesystem

import org.junit.Test
import org.junit.Assert.assertEquals

class FileSystemVisitorTest {

    @Test
    fun `test Size Calculation`() {
        // Structure:
        // root
        //  - file1 (100)
        //  - subDir
        //     - file2 (200)
        //     - file3 (300)
        // Total: 600

        val file1 = File("file1.txt", 100)
        val file2 = File("file2.jpg", 200)
        val file3 = File("file3.mp4", 300)
        val subDir = Directory("subDir", listOf(file2, file3))
        val root = Directory("root", listOf(file1, subDir))

        val sizeVisitor = SizeCalculatorVisitor()
        root.accept(sizeVisitor)

        assertEquals(600, sizeVisitor.totalSizeKb)
    }

    @Test
    fun `test File Search`() {
        val file1 = File("notes.txt", 10)
        val file2 = File("image.png", 200)
        val file3 = File("todo.txt", 5)
        val subDir = Directory("docs", listOf(file1, file2))
        val root = Directory("root", listOf(subDir, file3))

        val searchVisitor = FileSearchVisitor("txt")
        root.accept(searchVisitor)

        assertEquals(2, searchVisitor.foundFiles.size)
        assert(searchVisitor.foundFiles.any { it.name == "notes.txt" })
        assert(searchVisitor.foundFiles.any { it.name == "todo.txt" })
    }

    @Test
    fun `test Structure Printer`() {
        val file1 = File("file1.txt", 100)
        val file2 = File("file2.jpg", 200)
        val subDir = Directory("subDir", listOf(file2))
        val root = Directory("root", listOf(file1, subDir))

        val printer = StructurePrinterVisitor()
        root.accept(printer)

        val output = printer.getStructure()
        println("--- Structure ---\n$output")
        
        assert(output.contains("+ root"))
        assert(output.contains("- file1.txt (100KB)"))
        assert(output.contains("+ subDir"))
        assert(output.contains("- file2.jpg (200KB)"))
    }

    @Test
    fun `test Report Visitor`() {
        val files = listOf(
            File("a.txt", 10),
            File("b.txt", 20),
            File("c.jpg", 100),
            File("d.jpg", 200),
            File("e.mp4", 500)
        )
        val root = Directory("root", files)

        val reportVisitor = ReportVisitor()
        root.accept(reportVisitor)

        val report = reportVisitor.getReport()
        println("--- Report ---\n$report")

        assert(report.contains("TXT: 2 files (30KB)"))
        assert(report.contains("JPG: 2 files (300KB)"))
        assert(report.contains("MP4: 1 files (500KB)"))
    }
}
