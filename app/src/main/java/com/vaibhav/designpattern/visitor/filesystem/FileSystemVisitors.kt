package com.vaibhav.designpattern.visitor.filesystem

class SizeCalculatorVisitor : FileSystemVisitor {
    var totalSizeKb = 0

    override fun visit(file: File) {
        totalSizeKb += file.sizeKb
    }

    override fun visit(directory: Directory) {
        // For directory, we don't add size itself (assuming 0), 
        // but we must traverse children.
        // Note: In standard Visitor, the traversal is often controlled by the Object Structure (Directory),
        // but here we can also control it in the Visitor if we want specific logic.
        // However, usually 'accept' in Directory calls 'accept' on children OR Visitor iterates.
        // Let's have the Visitor iterate here to keep Directory simple data class.
        
        directory.children.forEach { it.accept(this) }
    }
}

class FileSearchVisitor(private val query: String) : FileSystemVisitor {
    val foundFiles = mutableListOf<File>()

    override fun visit(file: File) {
        if (file.name.contains(query, ignoreCase = true)) {
            foundFiles.add(file)
        }
    }

    override fun visit(directory: Directory) {
        directory.children.forEach { it.accept(this) }
    }
}

class StructurePrinterVisitor : FileSystemVisitor {
    private val sb = StringBuilder()
    private var depth = 0

    override fun visit(file: File) {
        sb.append("  ".repeat(depth)).append("- ${file.name} (${file.sizeKb}KB)\n")
    }

    override fun visit(directory: Directory) {
        sb.append("  ".repeat(depth)).append("+ ${directory.name}\n")
        depth++
        directory.children.forEach { it.accept(this) }
        depth--
    }

    fun getStructure(): String = sb.toString()
}

class ReportVisitor : FileSystemVisitor {
    private val extensionStats = mutableMapOf<String, Pair<Int, Int>>() // Ext -> (Count, Size)

    override fun visit(file: File) {
        val ext = file.name.substringAfterLast('.', "Unknown").uppercase()
        val current = extensionStats.getOrDefault(ext, 0 to 0)
        extensionStats[ext] = (current.first + 1) to (current.second + file.sizeKb)
    }

    override fun visit(directory: Directory) {
        directory.children.forEach { it.accept(this) }
    }

    fun getReport(): String {
        return extensionStats.entries.joinToString("\n") { (ext, stats) ->
            "$ext: ${stats.first} files (${stats.second}KB)"
        }
    }
}
