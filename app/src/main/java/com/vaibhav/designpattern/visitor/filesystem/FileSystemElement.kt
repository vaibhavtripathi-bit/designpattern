package com.vaibhav.designpattern.visitor.filesystem

/**
 * The Element interface.
 */
interface FileSystemElement {
    fun accept(visitor: FileSystemVisitor)
}

data class File(val name: String, val sizeKb: Int) : FileSystemElement {
    override fun accept(visitor: FileSystemVisitor) {
        visitor.visit(this)
    }
}

data class Directory(val name: String, val children: List<FileSystemElement>) : FileSystemElement {
    override fun accept(visitor: FileSystemVisitor) {
        visitor.visit(this)
    }
}
