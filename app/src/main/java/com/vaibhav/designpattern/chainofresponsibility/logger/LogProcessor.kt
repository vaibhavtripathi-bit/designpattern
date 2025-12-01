package com.vaibhav.designpattern.chainofresponsibility.logger

enum class LogLevel {
    INFO, DEBUG, ERROR
}

abstract class Logger(protected val level: LogLevel) {
    protected var nextLogger: Logger? = null

    fun setNext(logger: Logger): Logger {
        this.nextLogger = logger
        return logger
    }

    fun logMessage(level: LogLevel, message: String) {
        if (this.level == level) {
            write(message)
        }
        nextLogger?.logMessage(level, message)
    }

    protected abstract fun write(message: String)
}

class InfoLogger : Logger(LogLevel.INFO) {
    override fun write(message: String) {
        println("INFO: $message")
    }
}

class DebugLogger : Logger(LogLevel.DEBUG) {
    override fun write(message: String) {
        println("DEBUG: $message")
    }
}

class ErrorLogger : Logger(LogLevel.ERROR) {
    override fun write(message: String) {
        println("ERROR: $message")
    }
}

object LoggerChain {
    fun log(level: LogLevel, message: String) {
        val info = InfoLogger()
        val debug = DebugLogger()
        val error = ErrorLogger()

        info.setNext(debug).setNext(error)

        info.logMessage(level, message)
    }
}

class LogProcessor {
    interface Logger {
        fun log(level: LogLevel, message: String)
    }

    class BaseLogger : Logger {
        override fun log(level: LogLevel, message: String) {
            // No-op, end of chain
        }
    }

    abstract class LoggerDecorator(private val next: Logger) : Logger {
        override fun log(level: LogLevel, message: String) {
            write(level, message)
            next.log(level, message)  // always call next → decorator behavior
        }
        protected abstract fun write(level: LogLevel, message: String)
    }

    class ConsoleLogger(next: Logger) : LoggerDecorator(next) {
        override fun write(level: LogLevel, message: String) {
            println("[Console] $level: $message")
        }
    }

    class FileLogger(next: Logger) : LoggerDecorator(next) {
        override fun write(level: LogLevel, message: String) {
            println("[File] $level: $message")
        }
    }

    class DbLogger(next: Logger) : LoggerDecorator(next) {
        override fun write(level: LogLevel, message: String) {
            println("[DB] $level: $message")
        }
    }

    object LoggerPipeline {
        private val logger: Logger

        init {
            logger = ConsoleLogger(
                FileLogger(
                    DbLogger(
                        BaseLogger()
                    )
                )
            )
        }

        fun log(level: LogLevel, message: String) {
            logger.log(level, message)
        }
    }

    fun processLogs() {
        LoggerPipeline.log(LogLevel.INFO, "This is an info message.")
        LoggerPipeline.log(LogLevel.DEBUG, "This is a debug message.")
        LoggerPipeline.log(LogLevel.ERROR, "This is an error message.")
    }
}