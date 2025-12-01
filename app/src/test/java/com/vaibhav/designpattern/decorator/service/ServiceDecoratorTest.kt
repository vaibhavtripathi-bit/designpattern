package com.vaibhav.designpattern.decorator.service

import org.junit.Test
import org.junit.Assert.assertEquals

class ServiceDecoratorTest {

    @Test
    fun `test LoggingDecorator logs calls`() {
        val log = mutableListOf<String>()
        val service: DataService = LoggingDecorator(DatabaseService(), log)
        
        val result = service.fetchData("User1")
        
        assertEquals("Data for User1", result)
        assertEquals(listOf("Fetching data for User1"), log)
    }

    @Test
    fun `test CachingDecorator caches results and skips delegate`() {
        val dbService = DatabaseService()
        val service: DataService = CachingDecorator(dbService)

        // First call: Should hit the database
        val result1 = service.fetchData("User1")
        assertEquals("Data for User1", result1)
        assertEquals(1, dbService.callCount)

        // Second call: Should return cached result and NOT hit the database
        val result2 = service.fetchData("User1")
        assertEquals("Data for User1", result2)
        assertEquals(1, dbService.callCount) // Count remains 1
    }

    @Test
    fun `test Caching and Logging together`() {
        val log = mutableListOf<String>()
        val dbService = DatabaseService()
        // Wrap: Caching(Logging(Database))
        // Note: If Caching hits, Logging (which is inside) won't be called!
        val service: DataService = CachingDecorator(LoggingDecorator(dbService, log))

        // First call
        service.fetchData("User1")
        assertEquals(1, dbService.callCount)
        assertEquals(listOf("Fetching data for User1"), log)

        // Second call
        service.fetchData("User1")
        assertEquals(1, dbService.callCount) // DB not called
        assertEquals(listOf("Fetching data for User1"), log) // Logging not called again because it's wrapped BY caching
    }
}
