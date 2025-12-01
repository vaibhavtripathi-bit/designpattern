package com.vaibhav.designpattern.template

/**
 * Generic interface for a Command.
 * @param R The return type of the execution (can be Unit).
 */
interface Command<R> {
    fun execute(): R
    fun undo()
}

/**
 * Invoker class to execute commands.
 */
class Invoker {
    private val history = mutableListOf<Command<*>>()

    fun <R> executeCommand(command: Command<R>): R {
        history.add(command)
        return command.execute()
    }

    fun undoLast() {
        if (history.isNotEmpty()) {
            val command = history.removeAt(history.lastIndex)
            command.undo()
        }
    }
}
