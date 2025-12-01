package com.vaibhav.designpattern.decorator.service

/**
 * Abstract decorator class for DataService.
 */
abstract class ServiceDecorator(protected val delegate: DataService) : DataService {
    override fun fetchData(key: String): String {
        return delegate.fetchData(key)
    }
}
