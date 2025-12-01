package com.vaibhav.designpattern.decorator.service

class LoggingDecorator(delegate: DataService, private val log: MutableList<String>) : ServiceDecorator(delegate) {
    override fun fetchData(key: String): String {
        log.add("Fetching data for $key")
        return super.fetchData(key)
    }
}

class CachingDecorator(delegate: DataService) : ServiceDecorator(delegate) {
    private val cache = mutableMapOf<String, String>()

    override fun fetchData(key: String): String {
        if (cache.containsKey(key)) {
            return cache[key]!!
        }
        val data = super.fetchData(key)
        cache[key] = data
        return data
    }
}
