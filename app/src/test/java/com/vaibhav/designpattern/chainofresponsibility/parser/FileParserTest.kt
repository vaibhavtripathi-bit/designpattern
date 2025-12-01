package com.vaibhav.designpattern.chainofresponsibility.parser

import org.junit.Test

class FileParserTest {
    @Test
    fun `test JSON File`() {
        FileParserChain.parseFile("data.json", "{ \"key\": \"value\" }")
    }

    @Test
    fun `test XML File`() {
        FileParserChain.parseFile("config.xml", "<root></root>")
    }

    @Test
    fun `test CSV File`() {
        FileParserChain.parseFile("users.csv", "id,name")
    }

    @Test
    fun `test Unknown File`() {
        FileParserChain.parseFile("image.png", "binary_data")
    }
}
