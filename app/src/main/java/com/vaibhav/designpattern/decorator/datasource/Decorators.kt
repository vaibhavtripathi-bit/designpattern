package com.vaibhav.designpattern.decorator.datasource

import java.util.Base64
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream

class EncryptionDecorator(source: DataSource) : DataSourceDecorator(source) {
    override fun writeData(data: String) {
        super.writeData(encode(data))
    }

    override fun readData(): String {
        return decode(super.readData())
    }

    private fun encode(data: String): String {
        return Base64.getEncoder().encodeToString(data.toByteArray())
    }

    private fun decode(data: String): String {
        return String(Base64.getDecoder().decode(data))
    }
}

class CompressionDecorator(source: DataSource) : DataSourceDecorator(source) {
    override fun writeData(data: String) {
        super.writeData(compress(data))
    }

    override fun readData(): String {
        return decompress(super.readData())
    }

    private fun compress(stringData: String): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val gzipOutputStream = GZIPOutputStream(byteArrayOutputStream)
        gzipOutputStream.write(stringData.toByteArray())
        gzipOutputStream.close()
        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray())
    }

    private fun decompress(stringData: String): String {
        val byteArrayInputStream = ByteArrayInputStream(Base64.getDecoder().decode(stringData))
        val gzipInputStream = GZIPInputStream(byteArrayInputStream)
        return String(gzipInputStream.readBytes())
    }
}
