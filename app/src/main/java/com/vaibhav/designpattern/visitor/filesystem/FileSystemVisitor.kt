package com.vaibhav.designpattern.visitor.filesystem

/**
 * The Visitor interface.
 */
interface FileSystemVisitor {
    fun visit(file: File)
    fun visit(directory: Directory)
}
