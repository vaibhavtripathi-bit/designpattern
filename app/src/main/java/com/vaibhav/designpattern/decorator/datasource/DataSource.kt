package com.vaibhav.designpattern.decorator.datasource

import java.io.File
import java.io.IOException

interface DataSource {
    fun writeData(data: String)
    fun readData(): String
}

class FileDataSource(private val filename: String) : DataSource {
    override fun writeData(data: String) {
        val file = File(filename)
        file.writeText(data)
    }

    override fun readData(): String {
        val file = File(filename)
        return if (file.exists()) {
            file.readText()
        } else {
            ""
        }
    }
}
