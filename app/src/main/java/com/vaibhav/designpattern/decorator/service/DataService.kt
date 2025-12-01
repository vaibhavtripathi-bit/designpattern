package com.vaibhav.designpattern.decorator.service

/**
 * Interface for Data Service.
 */
interface DataService {
    fun fetchData(key: String): String
}

/**
 * Concrete implementation simulating a database.
 * Has a callCount to verify if it was called or skipped.
 */
class DatabaseService : DataService {
    var callCount = 0

    override fun fetchData(key: String): String {
        callCount++
        return "Data for $key"
    }
}
