package com.vaibhav.designpattern.decorator.datasource

import org.junit.Test
import org.junit.Assert.assertEquals
import java.io.File

class DataSourceDecoratorTest {

    @Test
    fun `test Plain File Data Source`() {
        val filename = "test_plain.txt"
        val data = "Hello, World!"
        val source = FileDataSource(filename)

        source.writeData(data)
        assertEquals(data, source.readData())

        File(filename).delete()
    }

    @Test
    fun `test Encryption Decorator`() {
        val filename = "test_encrypted.txt"
        val data = "Secret Data"
        
        // Wrap FileDataSource with EncryptionDecorator
        val source: DataSource = EncryptionDecorator(FileDataSource(filename))

        source.writeData(data)
        
        // Verify we can read it back decrypted
        assertEquals(data, source.readData())
        
        // Verify the file content is actually encrypted (Base64 encoded)
        val fileContent = File(filename).readText()
        assertEquals("U2VjcmV0IERhdGE=", fileContent) // "Secret Data" in Base64

        File(filename).delete()
    }

    @Test
    fun `test Compression and Encryption`() {
        val filename = "test_comp_enc.txt"
        val data = "A very long string that should be compressed and then encrypted to ensure security and efficiency."
        
        // Wrap: Encryption(Compression(File))
        // Note: Order matters. Here we Compress first, then Encrypt the compressed bytes.
        val source: DataSource = EncryptionDecorator(
            CompressionDecorator(
                FileDataSource(filename)
            )
        )

        source.writeData(data)
        assertEquals(data, source.readData())

        File(filename).delete()
    }
}
