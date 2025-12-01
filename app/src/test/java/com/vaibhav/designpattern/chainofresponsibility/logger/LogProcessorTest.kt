package com.vaibhav.designpattern.chainofresponsibility.logger

import org.junit.Test

class LogProcessorTest {
    @Test
    fun `test Info Log`() {
        LoggerChain.log(LogLevel.INFO, "System started.")
    }

    @Test
    fun `test Debug Log`() {
        LoggerChain.log(LogLevel.DEBUG, "Variable x = 10.")
    }

    @Test
    fun `test Error Log`() {
        LoggerChain.log(LogLevel.ERROR, "NullPointerException occurred.")
    }
}
