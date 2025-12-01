package com.vaibhav.designpattern.chainofresponsibility.parser

abstract class Parser {
    protected var nextParser: Parser? = null

    fun setNext(parser: Parser): Parser {
        this.nextParser = parser
        return parser
    }

    abstract fun parse(fileName: String, content: String)
}

class JsonParser : Parser() {
    override fun parse(fileName: String, content: String) {
        if (fileName.endsWith(".json")) {
            println("JSON Parser: Parsing $fileName")
        } else {
            println("JSON Parser: Cannot parse $fileName. Passing to next...")
            nextParser?.parse(fileName, content) ?: println("Error: No parser found for $fileName")
        }
    }
}

class XmlParser : Parser() {
    override fun parse(fileName: String, content: String) {
        if (fileName.endsWith(".xml")) {
            println("XML Parser: Parsing $fileName")
        } else {
            println("XML Parser: Cannot parse $fileName. Passing to next...")
            nextParser?.parse(fileName, content) ?: println("Error: No parser found for $fileName")
        }
    }
}

class CsvParser : Parser() {
    override fun parse(fileName: String, content: String) {
        if (fileName.endsWith(".csv")) {
            println("CSV Parser: Parsing $fileName")
        } else {
            println("CSV Parser: Cannot parse $fileName. Passing to next...")
            nextParser?.parse(fileName, content) ?: println("Error: No parser found for $fileName")
        }
    }
}

object FileParserChain {
    fun parseFile(fileName: String, content: String) {
        val json = JsonParser()
        val xml = XmlParser()
        val csv = CsvParser()

        json.setNext(xml).setNext(csv)

        println("--- Parsing File: $fileName ---")
        json.parse(fileName, content)
    }
}
